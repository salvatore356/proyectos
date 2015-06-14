package clases.pantallas;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import clases.BaseDatos;
import clases.Proyectil;
import clases.Terreno;
import clases.personajes.Personaje;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import panimador.Animable;
import panimador.PAnimador;
import util.DimensionPantalla;
import util.RecursoUtil;

public class Partida extends Animable {

  private BaseDatos BD;
  private PAnimador _animador;
  private JPanel lienzo;
  private JFrame pFrame=new JFrame();
  private final Vector<Personaje> pPersonajes;
  private Personaje pPersonajeActivo;
  private Terreno pTerreno;
  private Barra barra;
  private Proyectil pBala;
  private boolean isJuegoFinalizado=false;
  private boolean isJuegoActivo=true;
  private static int TIEMPO_PAUSA=60;
  private int pausa_actual=0;
  private int jugando=0;
  
  private int fViento, vDireccion;
  private Ellipse2D pVientoVisual;
    
  private int muerto, vivo;
  private ImageIcon vient = RecursoUtil.getImagen("viento.png");
  private ImageIcon flecha = RecursoUtil.getImagen("sflecha.png");
  private ImageIcon activo = RecursoUtil.getImagen("activo.gif");
  private int guardo1=0;
  
  private Principal mPrincipal; 

    public Partida(Vector<Personaje> personajes, Terreno terreno, Principal principal) {
        
    	mPrincipal=principal;
    	pPersonajes=personajes;
    	pTerreno=terreno;
    	init();
    	
    }
    
    private void init(){
    	
    	BD=new BaseDatos();
        fViento=0;
        vDireccion=RecursoUtil.random.nextInt(360);
        
        pPersonajeActivo=pPersonajes.elementAt(jugando);
        
        addFrame();
        addLienzo();
        addAnimator();
    }


    @Override
    public void simular() {
    	pVientoVisual =new Ellipse2D.Double(DimensionPantalla.WIDTH/2-50, 10, 100, 100);
        if(!isJuegoActivo){
          personajesVivos();
        }else{
          
        	cambiarDireccionViento();

            if(TIEMPO_PAUSA>pausa_actual){
                ++pausa_actual;
            }else{
            	pausa_actual=0;
                isJuegoActivo=false;
                pBala.setPersonaje(pPersonajes.elementAt(jugando));
                pPersonajeActivo=pPersonajes.elementAt(jugando);
                pPersonajeActivo.renovar();
                }
            }

        if(isJuegoFinalizado && guardo1<1){
            for(int i=0; i<pPersonajes.size();i++)
            try {
                BD.guardarJugador(pPersonajes.elementAt(i).getNombre(), (long) pPersonajes.elementAt(i).getPuntos());
            } catch (IOException ex) {}
            guardo1++;

        }

        barra.setAngulo(pPersonajeActivo.getAngulo());
        barra.setXFuerza((DimensionPantalla.WIDTH*0.0045)*pPersonajeActivo.getFuerza());
        barra.setXMovimiento(pPersonajeActivo.getMovimiento()*(DimensionPantalla.WIDTH*0.00225));
      }
    
    private void cambiarDireccionViento(){
    	if(RecursoUtil.random.nextInt(100)>98){
      	  fViento=RecursoUtil.random.nextInt(2);
          vDireccion=RecursoUtil.random.nextInt(360);
        }
    }
    
    private void personajesVivos(){
    	 muerto=0;
         for(int i=0; i<pPersonajes.size();i++){
            if(pPersonajes.elementAt(i).getVida()<=0)
                ++muerto;
         }
         for(int j=0; j<pPersonajes.size();j++){
             if(0<pPersonajes.elementAt(j).getVida())
                 vivo=j;
         }
    }
   
    public void renderizar(Graphics2D g) {
    	drawFinPartida(g);
    	drawVientoDireccion(g);
        pausa(g);
    }
    
    private void drawFinPartida(Graphics2D g){
    	if(muerto==pPersonajes.size()-1){
            g.setColor(Color.BLACK);
            
            g.drawString(
       		 "GANO EL JUGADOR "+pPersonajes.elementAt(vivo).getNombre().toUpperCase(),
       		 (int)(DimensionPantalla.WIDTH*0.3),
       		 (int)(DimensionPantalla.HEIGHT*0.4)
   		 );
            
            g.setColor(Color.white);
            g.drawString(
       		 "Presione el raton para salir ",
       		 (int)(DimensionPantalla.WIDTH*0.3),
       		 (int)(DimensionPantalla.HEIGHT*0.7)
   		 );
            isJuegoFinalizado=true;

        }
    }
    
    private void drawVientoDireccion(Graphics2D g){
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawImage(vient.getImage(),(int)pVientoVisual.getCenterX()-vient.getIconWidth()/2,(int)pVientoVisual.getCenterY()-vient.getIconHeight()/2, vient.getIconWidth(),vient.getIconHeight(),null);
        g.setColor(Color.black);
        g.drawString(fViento+"", (int)pVientoVisual.getCenterX()-5, (int)pVientoVisual.getCenterY());

        g.rotate(-Math.toRadians(vDireccion), pVientoVisual.getCenterX(),pVientoVisual.getCenterY());
        
        g.drawImage(flecha.getImage(), (int)pVientoVisual.getCenterX(),(int)pVientoVisual.getCenterY()-(flecha.getIconHeight()/2), flecha.getIconWidth(), flecha.getIconHeight(),null);

        g.rotate(Math.toRadians(vDireccion), pVientoVisual.getCenterX(),pVientoVisual.getCenterY());    	
    }
    
    private void pausa(Graphics2D g){
    	if(isJuegoActivo){
            g.drawString("Pausa "+ pausa_actual,100, 100);
        }else {
                g.drawImage(activo.getImage(),(int)pPersonajeActivo.getPj().getMinX(),(int)(pPersonajeActivo.getPj().getCenterY()-2*activo.getIconHeight()), activo.getIconWidth(),activo.getIconHeight(),null);
        }
    }

    @Override
    public void inicializarAnimable() {}

    public Shape rectanguloContenedor() {
        return null;
    }
    public int getTiempo(){
        return pausa_actual;
    }
    
    private void addLienzo(){
    	
    	lienzo = new JPanel();
        lienzo.setBounds(0,0,DimensionPantalla.WIDTH,DimensionPantalla.HEIGHT);
        lienzo.setFocusable(true);
        pFrame.add(lienzo);
        
    	addMouseListenerLienzo();
    	addKeyListenerLienzo();
    }
    
    private void addMouseListenerLienzo(){
    	
        lienzo.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e){
                if(!isJuegoActivo && !isJuegoFinalizado){
                    if(e.getButton()==MouseEvent.BUTTON1){
                    	pPersonajeActivo.incrementarFuerza(true);
                        
                    }
                }else if(isJuegoFinalizado){
                    if(e.getButton()==MouseEvent.BUTTON1)
                        Animador().detener();
                        Animador().clear();
                    try {
                        mPrincipal.getRepro().setGain(1);
                        
                    } catch (BasicPlayerException ex) {}

                    pFrame.dispose();
                } 
            }

            public void mouseReleased(MouseEvent e){
                if(!isJuegoFinalizado && !isJuegoActivo){
                    if(e.getButton()==MouseEvent.BUTTON1){
                    	pPersonajeActivo.incrementarFuerza(false);
                        isJuegoActivo=true;
                        pBala.iniciarTrayectoria(Math.cos(vDireccion)*fViento,-Math.sin(vDireccion)*fViento, pPersonajeActivo.getFuerza(), pPersonajeActivo.getAngulo(), 0.3);
                        if(jugando<pPersonajes.size()-1)
                        ++jugando;
                    else jugando=0;
                   }
               }
            }

        });
    }
    
    private void addKeyListenerLienzo(){
    	lienzo.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
              if(!isJuegoActivo && !isJuegoFinalizado){
                  if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                	  pPersonajeActivo.setDer(true);
                  }else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                	  pPersonajeActivo.setIzq(true);
                  } if (e.getKeyCode()==KeyEvent.VK_UP) {
                	  pPersonajeActivo.incrementarAngulo(true);
                  }else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
                	  pPersonajeActivo.decrementarAngulo(true);
                  }else if (e.getKeyCode()==KeyEvent.VK_P) {
                       int a = JOptionPane.showConfirmDialog(new JFrame(), "¿Esta seguro de que desea volver al menu principal?", "Salir", JOptionPane.YES_NO_OPTION);
                       if (a != 1) {
                          Animador().detener();
                          Animador().clear();
                          pFrame.dispose();
                      }
                 }else e.consume();
              }
            }

           public void keyReleased(KeyEvent e) {
              if(!isJuegoActivo && !isJuegoFinalizado){
                  if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                	  pPersonajeActivo.setDer(false);
                  }else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                	  pPersonajeActivo.setIzq(false);
                  }if (e.getKeyCode()==KeyEvent.VK_UP) {
                	  pPersonajeActivo.incrementarAngulo(false);
                  }else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
                	  pPersonajeActivo.decrementarAngulo(false);
                  }else e.consume();
              }
           }
          });
    }
    
    private void addAnimator(){
    	_animador = new PAnimador(lienzo);
        _animador.antialiasing(mPrincipal.getEfecto());
        
        _animador.agregarAnimable(pTerreno);

        barra = new Barra(pTerreno);
        _animador.agregarAnimable(barra);

        for(int i=0; i<pPersonajes.size();i++){
            _animador.agregarAnimable(pPersonajes.elementAt(i));
        }

        pBala = new Proyectil(_animador,pTerreno,pPersonajeActivo,pPersonajes);
        _animador.agregarAnimable(pBala);
        _animador.agregarAnimable(this);
        _animador.iniciar();
    }
    
	private void addFrame(){
		DimensionPantalla.setFullSizeScreen(pFrame, false);
		pFrame.setResizable(false);
		pFrame.setVisible(true);
	   
	}
}
