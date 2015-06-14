package clases.pantallas;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;

import util.DimensionPantalla;
import util.RecursoUtil;
import clases.pantallas.flotantes.AltoPuntaje;
import clases.pantallas.flotantes.Cheats;
import clases.pantallas.flotantes.Opciones;
import clases.pantallas.flotantes.Salir;
import clases.sonidos.SonidoFondo;

public class Principal extends JFrame {

	private ImageIcon imgSal1, imgSal2, imgIni1, imgIni2, imgOp1, imgOp2;
    private ImageIcon imgChe1, imgChe2, imgPun1, imgPun2, imgFondo;
	
	private JLabel lblOpcion, lblInicio, lblPuntos, lblCheat;
    private JLabel lblSalir, lblFondo;

    private Opciones mOpciones;
    private AltoPuntaje mPuntajes;
    private Salir mSalir;
    private Cheats mCheats;
        
    private final SonidoFondo sRepro = new SonidoFondo();
    
	private boolean bEfecto=true;
    private boolean bClave=false;
    

    Principal() {
        init();
    }
    
    private void init(){

    	DimensionPantalla.setFullSizeScreen(this, false);
        
    	addMenusFlotantes();
    	addBotones();
        addFondo();
        
        setVisible(true);
                
        initMusic();
        
    }
    
    private void initMusic(){
        File song;
        try {

            song = RecursoUtil.getRandomSound();
            sRepro.open(song);
            sRepro.play();
            sRepro.setGain(0.5);
           
        } catch (Exception ex) {
        	   ex.printStackTrace();
       }
    	
    }
    

    private void addFondo(){
    	imgFondo = RecursoUtil.imageFullSize("menu.png");
        
    	lblFondo = new JLabel(imgFondo);
        
        DimensionPantalla.setFullSizeLabel(lblFondo);
        this.add(lblFondo);
    }
    
    private void addMenusFlotantes(){
    	mOpciones = new Opciones(this);
        this.add(mOpciones);
        
        mPuntajes = new AltoPuntaje(this);
        this.add(mPuntajes);
        
        mSalir = new Salir(this);
        this.add(mSalir);
        
        mCheats = new Cheats(this);
        this.add(mCheats);
    }
    
    public void apagarMenu() {
        lblInicio.setEnabled(false);
        lblCheat.setEnabled(false);
        lblOpcion.setEnabled(false);
        lblPuntos.setEnabled(false);
        lblSalir.setEnabled(false);
    }

    public void prenderMenu() {
        lblInicio.setEnabled(true);
        lblInicio.setIcon(imgIni1);
        lblCheat.setEnabled(true);
        lblCheat.setIcon(imgChe1);
        lblOpcion.setEnabled(true);
        lblOpcion.setIcon(imgOp1);
        lblPuntos.setEnabled(true);
        lblPuntos.setIcon(imgPun1);
        lblSalir.setEnabled(true);
        lblSalir.setIcon(imgSal1);
    }

    public void setEfecto(boolean e){
        bEfecto=e;
    }

    public void setClave(boolean e){
        bClave=e;
    }

    public boolean getClave(){
        return bClave;
    }
    
    public boolean getEfecto(){
        return bEfecto;
    }

    private void addBtnInicio(){
    	
    	imgIni1 = RecursoUtil.getImagen("ini1.png");
    	imgIni2 = RecursoUtil.getImagen("ini2.png");
        
        lblInicio = new JLabel(imgIni1);
        
        DimensionPantalla.setScaledOption(
    		lblInicio, 0.03f, 0.65f, imgIni1.getIconWidth(), imgIni1.getIconHeight()
		);
        
        lblInicio.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {new MenuSeleccion(Principal.this);}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblInicio.isEnabled()) lblInicio.setIcon(imgIni2);
            }

            public void mouseExited(MouseEvent e) {
                if (lblInicio.isEnabled()) lblInicio.setIcon(imgIni1);
            }
            
        });
        this.add(lblInicio);   	
    }
    
    private void addBtnOpcion(){
        
    	imgOp1 = RecursoUtil.getImagen("op1.png");
        imgOp2 = RecursoUtil.getImagen("op2.png");
        
        lblOpcion = new JLabel(imgOp1);
        
        DimensionPantalla.setScaledOption(
    		lblOpcion, 0.03f, 0.55f, imgOp1.getIconWidth(), imgOp1.getIconHeight()
		);
        
        lblOpcion.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (lblOpcion.isEnabled()) {
                    apagarMenu();
                	mOpciones.setVisible(true);
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                if (lblOpcion.isEnabled()) {
                    lblOpcion.setIcon(imgOp2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblOpcion.isEnabled()) {
                    lblOpcion.setIcon(imgOp1);
                }
            }
        });
        this.add(lblOpcion);
    }

    private void addBtnPuntos(){

    	imgPun1 = RecursoUtil.getImagen("pun1.png");
        imgPun2 = RecursoUtil.getImagen("pun2.png");
        lblPuntos = new JLabel(imgPun1);
        
        DimensionPantalla.setScaledOption(
    		lblPuntos, 0.03f, 0.45f, imgPun1.getIconWidth(), imgPun1.getIconHeight()
		);
        
        lblPuntos.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                apagarMenu();
                mPuntajes.setVisible(true);                
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblPuntos.isEnabled()) {
                    lblPuntos.setIcon(imgPun2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblPuntos.isEnabled()) {
                    lblPuntos.setIcon(imgPun1);
                }
            }
        });
        this.add(lblPuntos);

    }
 
    private void addBtnCheat(){
    	
    	imgChe1 = RecursoUtil.getImagen("che1.png");
        imgChe2 = RecursoUtil.getImagen("che2.png");
        
        lblCheat = new JLabel(imgChe1);
        
        DimensionPantalla.setScaledOption(
    		lblCheat, 0.03f, 0.35f, imgChe1.getIconWidth(), imgChe1.getIconHeight()
		);
                
        lblCheat.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                if (mCheats.isEnabled()) {
                    apagarMenu();
                    mCheats.setVisible(true);
                }
            }

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblCheat.isEnabled()) {
                    lblCheat.setIcon(imgChe2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblCheat.isEnabled()) {
                	lblCheat.setIcon(imgChe1);
                }
            }
        });
        this.add(lblCheat);
    }
    
    private void addBtnSalir(){
    	
    	imgSal1 = RecursoUtil.getImagen("sal1.png");
        imgSal2 = RecursoUtil.getImagen("sal2.png");
        
        lblSalir = new JLabel(imgSal1);
        
        DimensionPantalla.setScaledOption(
    		lblSalir, 0.03f, 0.25f, imgSal1.getIconWidth(), imgSal1.getIconHeight()
		);
        
        lblSalir.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	if (lblSalir.isEnabled()) {
                    mSalir.setVisible(true);
                    apagarMenu();
                }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSalir.isEnabled()) {
                    lblSalir.setIcon(imgSal2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSalir.isEnabled()) {
                	lblSalir.setIcon(imgSal1);
                }
            }
        });
        this.add(lblSalir);
    }
 
    private void addBotones(){
        addBtnInicio();
        addBtnOpcion();
        addBtnCheat();
        addBtnSalir();
        addBtnPuntos();

    }
 
    public SonidoFondo getRepro() {
		return sRepro;
	}

	public static void main(String[] a) {
        new Principal();
    }
}
 