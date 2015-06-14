package clases.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import util.DimensionPantalla;
import util.RecursoUtil;
import clases.Terreno;
import clases.guitarras.*;
import clases.personajes.*;

public class MenuSeleccion extends JFrame{

    private JLabel lblFondo, lblAtras, lblJugar, lblLennonp1, lblGenep1, lblSlashp1;
    private JLabel lblWesp1, lblLennonp2, lblGenep2, lblSlashp2, lblWesp2, lblFlyvp1;
    private JLabel lblRazorp1, lblViejap1, lblLespaulp1, lblFlyvp2, lblRazorp2, lblViejap2; 
    private JLabel lblLespaulp2, lblP1, lblP2, lblG1, lblG2, lblDanno1, lblDanno2;
    private JLabel lblRadio1, lblRadio2, lblVida1, lblVida2, lblMorochop1, lblMorochop2; 
    private JLabel lblSaulp1, lblSaulp2, lblSalvatorep1, lblSalvatorep2 , lblTerreno; 
    private JLabel lblSiguiente, lblAnterior;

    private ImageIcon imgFondo, imgAtras1, imgAtras2, imgJugar1, imgJugar2;
    private ImageIcon imgLennon1, imgLennon2, imgGene1, imgGene2, imgWes1, imgWes2;
    private ImageIcon imgSlash1, imgSlash2, imgFlyv1, imgFlyv2, imgFlyv, imgRazor;
    private ImageIcon imgVieja, imgLespaul, imgRazor1, imgRazor2, imgVieja1, imgVieja2; 
    private ImageIcon imgLespaul1, imgLespaul2, imgSlashc, imgGenec, imgLennonc, imgWesc;
    private ImageIcon imgMorocho1, imgMorocho2, imgMorocho, imgSaul1, imgSaul2, imgSaul;
    private ImageIcon imgSalvatore1, imgSalvatore2, imgSalvatore, imgFlechaA, imgFlechaS;
    
    private ImageIcon[]  imgMapas;

    private Vector<Personaje> per;
    private Personaje per1,per2;
    private Guitarra guit1, guit2;
    private Terreno ter = new Terreno("4");
    private int seleccion=0;
    private boolean juega1=false;//verifica si se ha seleccionado el jugador 1
    private boolean juega2=false;//verifica si se ha seleccionado el jugador 2
    private boolean guitarra1=false;//verifica si se ha seleccionado la guitarra del jugador 1
    private boolean guitarra2=false;//verifica si se ha seleccionado la guitarra del jugador 2
    private Principal mPrincipal;
    
    
    MenuSeleccion(final Principal principal){

    	mPrincipal=principal;
    	
    	DimensionPantalla.setFullSizeScreen(this, false);
        per = new Vector<Personaje>();
        
        initMapas();

        activarPersonajesOcultos();
        initPersonajes();
        addConfiguracion();
        
        addPersonajes();
        addGuitarras();
        
        addJugadores();
        
        addFondo();

        setVisible(true);
    }
    
    private void addFondo(){
    	imgFondo = RecursoUtil.imageFullSize("menupartida.PNG");
        lblFondo = new JLabel(imgFondo);
        DimensionPantalla.setFullSizeLabel(lblFondo);
        this.add(lblFondo);
    }


    private void initMapas(){
    	imgMapas= new ImageIcon[4];
        
        for(int i=0;i<4;i++){
        	imgMapas[i]=RecursoUtil.getImagen("mini"+i+".png");
        }
    }
    
    
    private void addLblAnterior(){

    	imgFlechaA = RecursoUtil.getImagen("aflecha.png");
    	
    	lblAnterior = new JLabel(imgFlechaA);
    	DimensionPantalla.setScaledOption(lblAnterior,0.35f,0.9f);
        lblAnterior.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if(seleccion>0){
                    seleccion--;
                }else seleccion=3;
                lblTerreno.setIcon(imgMapas[seleccion]);

            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(lblAnterior);

    }
    
    private void addLblAtras(){
    	imgAtras1 = RecursoUtil.getImagen("atrasp1.png");
        imgAtras2 = RecursoUtil.getImagen("atrasp2.png");
        lblAtras = new JLabel(imgAtras1);

        lblAtras.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	dispose();
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblAtras.isEnabled()) {
                    lblAtras.setIcon(imgAtras2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblAtras.isEnabled()) {
                    lblAtras.setIcon(imgAtras1);
                }
            }
        });
        
        DimensionPantalla.setScaledOption2(lblAtras,0.55f,0.37f);
        this.add(lblAtras);
    }
    
    private void addLblJugar(){
    	imgJugar1 =RecursoUtil.getImagen("jugar1.png");
        imgJugar2 =RecursoUtil.getImagen("jugar2.png");

        lblJugar = new JLabel(imgJugar1);
        lblJugar.setEnabled(false);
        lblJugar.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if(lblJugar.isEnabled()){
                per1.setGuitarra(guit1);
                per2.setGuitarra(guit2);
                per.add(per1);
                per.add(per2);
                ++seleccion;
                ter.setAmbiente(""+seleccion);
                new Partida(per, ter, mPrincipal);
                    dispose();
                }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblJugar.isEnabled()) {
                	lblJugar.setIcon(imgJugar2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblJugar.isEnabled()) {
                	lblJugar.setIcon(imgJugar1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblJugar, 0.57f, 0.47f);
        this.add(lblJugar);

    }
    
    private void addLblLennon(){

        lblLennonp1 = new JLabel(imgLennon1);

        lblLennonp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
               if (lblLennonp1.isEnabled()) {
        	    lblP1.setIcon(imgLennonc);
        	    desactivarPersonajesP2(true, false,true,true, true, true, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PLennon(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblLennonp1.isEnabled()) {
                	lblLennonp1.setIcon(imgLennon2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblLennonp1.isEnabled()) {
                	lblLennonp1.setIcon(imgLennon1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblLennonp1, 0.77f, 0.77f);
        this.add(lblLennonp1);

    }
    
    private void addLblGene(){

        lblGenep1 = new JLabel(imgGene1);

        lblGenep1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblGenep1.isEnabled()) {
            	lblP1.setIcon(imgGenec);
            	desactivarPersonajesP2(false, true,true,true, true, true, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PGene(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblGenep1.isEnabled()) {
                	lblGenep1.setIcon(imgGene2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblGenep1.isEnabled()) {
                	lblGenep1.setIcon(imgGene1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblGenep1, 0.93f, 0.77f);
        this.add(lblGenep1);
    }
    
    private void addLblSlash1(){
    	
        lblSlashp1 = new JLabel(imgSlash1);

        lblSlashp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
             if (lblSlashp1.isEnabled()) {
            	 lblP1.setIcon(imgSlashc);
            	 desactivarPersonajesP2(true, true,false,true, true, true, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PSlash(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSlashp1.isEnabled()) {
                	lblSlashp1.setIcon(imgSlash2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSlashp1.isEnabled()) {
                	lblSlashp1.setIcon(imgSlash1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblSlashp1, 0.69f, 0.77f);
        this.add(lblSlashp1);

    }
    
    
    private void addLblLennon2(){
        
    	lblLennonp2 = new JLabel(imgLennon1);
        
        lblLennonp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if (lblLennonp2.isEnabled()) {
                    lblP2.setIcon(imgLennonc);
                    desactivarPersonajesP1(true, false,true,true, true, true, true);
                    juega2=true;
                    lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                    per2=new PLennon(ter);
                    lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblLennonp2.isEnabled()) {
                	lblLennonp2.setIcon(imgLennon2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblLennonp2.isEnabled()) {
                	lblLennonp2.setIcon(imgLennon1);
                }
            }
        });
        
        DimensionPantalla.setScaledOption2(lblLennonp2, 0.22f, 0.77f);
        this.add(lblLennonp2);
    }
    
    private void addLblGene2(){
    	lblGenep2 = new JLabel(imgGene1);

        lblGenep2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblGenep2.isEnabled()) {
                lblP2.setIcon(imgGenec);
                desactivarPersonajesP1(false, true,true,true, true, true, true);
                juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PGene(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblGenep2.isEnabled()) {
                	lblGenep2.setIcon(imgGene2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblGenep2.isEnabled()) {
                	lblGenep2.setIcon(imgGene1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblGenep2, 0.38f, 0.77f);
        this.add(lblGenep2);
    }
    
    private void addLblSlash2(){
        
    	lblSlashp2 = new JLabel(imgSlash1);
        
        lblSlashp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblSlashp2.isEnabled()) {
                lblP2.setIcon(imgSlashc);
                desactivarPersonajesP1(true, true,false,true, true, true, true);
                juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PSlash(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSlashp2.isEnabled()) {
                	lblSlashp2.setIcon(imgSlash2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSlashp2.isEnabled()) {
                	lblSlashp2.setIcon(imgSlash1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblSlashp2, 0.14f, 0.77f);
        this.add(lblSlashp2);
    }
    
    private void addLblWes1(){

        lblWesp1 = new JLabel(imgWes1);

        lblWesp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
               if (lblWesp1.isEnabled()) {
                lblP1.setIcon(imgWesc);
                desactivarPersonajesP2(true, true,true,false, true, true, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PWes(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblWesp1.isEnabled()) {
                	lblWesp1.setIcon(imgWes2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblWesp1.isEnabled()) {
                	lblWesp1.setIcon(imgWes1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblWesp1, 0.85f, 0.77f);
        this.add(lblWesp1);

    }

    
    private void addLblWes2(){
        lblWesp2 = new JLabel(imgWes1);

        lblWesp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
               if (lblWesp2.isEnabled()) {
        	    lblP2.setIcon(imgWesc);
        	    desactivarPersonajesP1(true, true,true,false, true, true, true);
                juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PWes(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblWesp2.isEnabled()) {
                	lblWesp2.setIcon(imgWes2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblWesp2.isEnabled()) {
                	lblWesp2.setIcon(imgWes1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblWesp2, 0.3f, 0.77f);
        this.add(lblWesp2);
    }
    
    private void addLblFlyV1(){
        lblFlyvp1 = new JLabel(imgFlyv1);

        lblFlyvp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                guitarra1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                lblG1.setIcon(imgFlyv);
                guit1= new GFlyV();
                lblDanno1.setText(""+guit1.getAtaque());
                lblRadio1.setText(""+guit1.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblFlyvp1.isEnabled()) {
                	lblFlyvp1.setIcon(imgFlyv2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblFlyvp1.isEnabled()) {
                	lblFlyvp1.setIcon(imgFlyv1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblFlyvp1, 0.94f, 0.5f);
        this.add(lblFlyvp1);
    }
    
    private void addLblRazor1(){
        lblRazorp1 = new JLabel(imgRazor1);

        lblRazorp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	lblG1.setIcon(imgRazor);
                guitarra1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                guit1= new GRazor();
                lblDanno1.setText(""+guit1.getAtaque());
                lblRadio1.setText(""+guit1.getRadio());
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblRazorp1.isEnabled()) {
                	lblRazorp1.setIcon(imgRazor2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblRazorp1.isEnabled()) {
                	lblRazorp1.setIcon(imgRazor1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblRazorp1, 0.73f, 0.51f);
        this.add(lblRazorp1);
    }
    
    private void addLblVieja1(){
    	lblViejap1 = new JLabel(imgVieja1);

    	lblViejap1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                guitarra1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                lblG1.setIcon(imgVieja);
                guit1= new GVieja();
                lblDanno1.setText(""+guit1.getAtaque());
                lblRadio1.setText(""+guit1.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblViejap1.isEnabled()) {
                	lblViejap1.setIcon(imgVieja2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblViejap1.isEnabled()) {
                	lblViejap1.setIcon(imgVieja1);
                }
            }
        });
    	DimensionPantalla.setScaledOption2(lblViejap1, 0.87f, 0.5f);
        this.add(lblViejap1);
    }
    
    private void addLblLespaul1(){
        lblLespaulp1 = new JLabel(imgLespaul1);

        lblLespaulp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	lblG1.setIcon(imgLespaul);
                guitarra1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                guit1= new GLesPaul();
                lblDanno1.setText(""+guit1.getAtaque());
                lblRadio1.setText(""+guit1.getRadio());
           }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblLespaulp1.isEnabled()) {
                	lblLespaulp1.setIcon(imgLespaul2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblLespaulp1.isEnabled()) {
                	lblLespaulp1.setIcon(imgLespaul1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblLespaulp1, 0.8f, 0.5f);
        this.add(lblLespaulp1);
    }
    
    private void addLblFlyV2(){
    	imgFlyv1 = RecursoUtil.getImagen("flyv1.png");
    	imgFlyv2 = RecursoUtil.getImagen("flyv2.png");

        lblFlyvp2 = new JLabel(imgFlyv1);
        lblFlyvp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	lblG2.setIcon(imgFlyv);
                guitarra2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                guit2= new GFlyV();
                lblDanno2.setText(""+guit2.getAtaque());
                lblRadio2.setText(""+guit2.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblFlyvp2.isEnabled()) {
                	lblFlyvp2.setIcon(imgFlyv2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblFlyvp2.isEnabled()) {
                	lblFlyvp2.setIcon(imgFlyv1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblFlyvp2, 0.39f, 0.5f);
        this.add(lblFlyvp2);
    }
    
    private void addLblRazor2(){
    	imgRazor1 = RecursoUtil.getImagen("razor1.PNG");
    	imgRazor2 = RecursoUtil.getImagen("razor2.PNG");

        lblRazorp2 = new JLabel(imgRazor1);

        lblRazorp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                guitarra2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                lblG2.setIcon(imgRazor);
                guit2= new GRazor();
                lblDanno2.setText(""+guit2.getAtaque());
                lblRadio2.setText(""+guit2.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblRazorp2.isEnabled()) {
                	lblRazorp2.setIcon(imgRazor2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblRazorp2.isEnabled()) {
                    lblRazorp2.setIcon(imgRazor1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblRazorp2, 0.18f, 0.51f);
        this.add(lblRazorp2);
    }
    
    private void addLblVieja2(){
    	imgVieja1 = RecursoUtil.getImagen("vieja1.PNG");
    	imgVieja2 = RecursoUtil.getImagen("vieja2.PNG");

        lblViejap2 = new JLabel(imgVieja1);

        lblViejap2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                guitarra2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                lblG2.setIcon(imgVieja);
                guit2= new GVieja();
                lblDanno2.setText(""+guit2.getAtaque());
                lblRadio2.setText(""+guit2.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblViejap2.isEnabled()) {
                	lblViejap2.setIcon(imgVieja2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblViejap2.isEnabled()) {
                	lblViejap2.setIcon(imgVieja1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblViejap2, 0.32f, 0.5f);
        this.add(lblViejap2);
    }
    
    private void addLblLespaul2(){
    	imgLespaul1 = RecursoUtil.getImagen("lespaul1.PNG");
    	imgLespaul2 = RecursoUtil.getImagen("lespaul2.PNG");

        lblLespaulp2 = new JLabel(imgLespaul1);

        lblLespaulp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                guitarra2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                lblG2.setIcon(imgLespaul);
                guit2= new GLesPaul();
                lblDanno2.setText(""+guit2.getAtaque());
                lblRadio2.setText(""+guit2.getRadio());
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblLespaulp2.isEnabled()) {
                	lblLespaulp2.setIcon(imgLespaul2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblLespaulp2.isEnabled()) {
                	lblLespaulp2.setIcon(imgLespaul1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblLespaulp2, 0.25f, 0.5f);
        this.add(lblLespaulp2);
    }
    
    private void addLblP1(){
    	lblP1 = new JLabel();
    	DimensionPantalla.setScaledOption2(lblP1, 0.9f, 0.25f,imgLennonc.getIconWidth(), imgLennonc.getIconHeight());
        this.add(lblP1);
    }
    
    private void addLblP2(){
    	lblP2 = new JLabel();
    	DimensionPantalla.setScaledOption2(lblP2, 0.27f, 0.25f, imgLennonc.getIconWidth(), imgLennonc.getIconHeight());
    	this.add(lblP2);
    }
    
    private void addLblG1(){
    	lblG1 = new JLabel();
        DimensionPantalla.setScaledOption2(lblG1, 0.9f, 0.185f, imgLespaul.getIconWidth(), imgLespaul.getIconHeight());
        this.add(lblG1);
    }
    
    private void addLblG2(){
    	lblG2 = new JLabel();
        DimensionPantalla.setScaledOption2(lblG2, 0.275f, 0.185f, imgLespaul.getIconWidth(), imgLespaul.getIconHeight());
        
        this.add(lblG2);
        
    }
    
    private void addLblDanno1(){
        lblDanno1 = new JLabel();
        lblDanno1.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
        lblDanno1.setForeground(Color.black);
        DimensionPantalla.setScaledOption3(lblDanno1, 0.29f, 0.79f, 30, 30);
        this.add(lblDanno1);
    }
    
    private void addLblDanno2(){
    	lblDanno2 = new JLabel();
    	lblDanno2.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
    	lblDanno2.setForeground(Color.black);
    	DimensionPantalla.setScaledOption3(lblDanno2, 0.92f, 0.79f, 30, 30);
    	this.add(lblDanno2);
    }
    
    private void addLblRadio1(){
    	lblRadio1= new JLabel();
    	lblRadio1.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
    	lblRadio1.setForeground(Color.black);
    	DimensionPantalla.setScaledOption3(lblRadio1, 0.29f, 0.85f, 50, 30);
    	this.add(lblRadio1);
    }
    
    private void addLblRadio2(){
    	lblRadio2= new JLabel();
    	lblRadio2.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
    	lblRadio2.setForeground(Color.black);
    	DimensionPantalla.setScaledOption3(lblRadio2, 0.92f, 0.85f, 50, 30);
    	this.add(lblRadio2);
    }
    
    private void addLblVida2(){
    	lblVida2 = new JLabel();
    	lblVida2.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
    	lblVida2.setForeground(Color.black);
    	DimensionPantalla.setScaledOption3(lblVida2, 0.9f, 0.9f, 50, 30);
        this.add(lblVida2);
    }
    
    private void addLblVida1(){
    	lblVida1 = new JLabel();
    	lblVida1.setFont(new Font("Arial", Font.HANGING_BASELINE, 22));
    	lblVida1.setForeground(Color.black);
    	DimensionPantalla.setScaledOption3(lblVida1, 0.29f, 0.9f, 50, 30);
    	this.add(lblVida1);
    }
    
    private void addLblMorocho1(){
    	
        lblMorochop1 = new JLabel(imgMorocho1);

        lblMorochop1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblMorochop1.isEnabled() && mPrincipal.getClave()) {
            	lblP1.setIcon(imgMorocho);
                desactivarPersonajesP2(true, true,true,true, false, true, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PMorocho(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblMorochop1.isEnabled() && mPrincipal.getClave()) {
                	lblMorochop1.setIcon(imgMorocho2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblMorochop1.isEnabled() && mPrincipal.getClave()) {
                	lblMorochop1.setIcon(imgMorocho1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblMorochop1, 0.93f, 0.67f);
        this.add(lblMorochop1);
    }
    
    private void addLblMorocho2(){
    	lblMorochop2 = new JLabel(imgMorocho1);

    	lblMorochop2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblMorochop2.isEnabled() && mPrincipal.getClave()) {
            	lblP2.setIcon(imgMorocho);
            	desactivarPersonajesP1(true, true,true,true, false, true, true);
            	juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PMorocho(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblMorochop2.isEnabled() && mPrincipal.getClave()) {
                	lblMorochop2.setIcon(imgMorocho2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblMorochop2.isEnabled() && mPrincipal.getClave()) {
                	lblMorochop2.setIcon(imgMorocho1);
                }
            }
        });
    	DimensionPantalla.setScaledOption2(lblMorochop2, 0.38f, 0.67f);
        this.add(lblMorochop2);

    }
    
    private void addLblSaul1(){

        lblSaulp1 = new JLabel(imgSaul1);

        lblSaulp1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblSaulp1.isEnabled() && mPrincipal.getClave()) {
            	lblP1.setIcon(imgSaul);
            	desactivarPersonajesP2(true, true,true,true, true, false, true);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PSaul(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSaulp1.isEnabled() && mPrincipal.getClave()) {
                	lblSaulp1.setIcon(imgSaul2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSaulp1.isEnabled()) {
                	lblSaulp1.setIcon(imgSaul1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblSaulp1, 0.85f, 0.67f);
        this.add(lblSaulp1);
    }
    
    private void addLblSalvatore2(){
    	lblSalvatorep2 = new JLabel(imgSalvatore1);

    	lblSalvatorep2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblSalvatorep2.isEnabled() && mPrincipal.getClave()) {
            	lblP2.setIcon(imgSalvatore);
            	desactivarPersonajesP1(true, true,true,true, true, true, false);
                juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PSalvatore(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSalvatorep2.isEnabled() && mPrincipal.getClave()) {
                	lblSalvatorep2.setIcon(imgSalvatore2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSalvatorep2.isEnabled() && mPrincipal.getClave()) {
                	lblSalvatorep2.setIcon(imgSalvatore1);
                }
            }
        });
    	DimensionPantalla.setScaledOption2(lblSalvatorep2, 0.22f, 0.67f);
        this.add(lblSalvatorep2);
    }
    
    private void addLblSaul2(){
    	lblSaulp2 = new JLabel(imgSaul1);

        lblSaulp2.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblSaulp2.isEnabled() && mPrincipal.getClave()) {
            	lblP2.setIcon(imgSaul);
            	desactivarPersonajesP1(true, true,true,true, true, false, true);
                juega2=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per2=new PSaul(ter);
                lblVida2.setText(""+(int)per2.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSaulp2.isEnabled() && mPrincipal.getClave()) {
                	lblSaulp2.setIcon(imgSaul2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSaulp2.isEnabled() && mPrincipal.getClave()) {
                    lblSaulp2.setIcon(imgSaul1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblSaulp2, 0.3f, 0.67f);
        this.add(lblSaulp2);
    }
    
    private void addLblSalvatore1(){

    	lblSalvatorep1 = new JLabel(imgSalvatore1);

        lblSalvatorep1.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            if (lblSalvatorep1.isEnabled() && mPrincipal.getClave()) {
            	lblP1.setIcon(imgSalvatore);
            	desactivarPersonajesP2(true, true,true,true, true, true, false);
                juega1=true;
                lblJugar.setEnabled(juega1 && juega2 && guitarra1 && guitarra2);
                per1=new PSalvatore(ter);
                lblVida1.setText(""+(int)per1.getVida());
              }
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                if (lblSalvatorep1.isEnabled() && mPrincipal.getClave()) {
                	lblSalvatorep1.setIcon(imgSalvatore2);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (lblSalvatorep1.isEnabled() && mPrincipal.getClave()) {
                	lblSalvatorep1.setIcon(imgSalvatore1);
                }
            }
        });
        DimensionPantalla.setScaledOption2(lblSalvatorep1, 0.77f, 0.67f);
        this.add(lblSalvatorep1);
    }
    
    private void addLblSiguiente(){
        imgFlechaS = RecursoUtil.getImagen("sflecha.png");
        
        lblSiguiente = new JLabel(imgFlechaS);
        DimensionPantalla.setScaledOption(lblSiguiente, 0.6f, 0.9f);
        lblSiguiente.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if(seleccion<3){
                    seleccion++;
                }else seleccion=0;
                lblTerreno.setIcon(imgMapas[seleccion]);
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(lblSiguiente);
    }
    
    private void addLblTerreno(){
    	lblTerreno = new JLabel(imgMapas[0]);
    	DimensionPantalla.setScaledOption2(lblTerreno, 0.55f, 0.2f);
        this.add(lblTerreno);
    }
    
    private void addConfiguracion(){
        addLblTerreno();
        addLblSiguiente();
        addLblAnterior();

        addLblDanno1();
        addLblVida1();
        addLblRadio1();

        addLblDanno2();
        addLblVida2();
        addLblRadio2();

        addLblAtras();
        addLblJugar();
    }
    
    private void addGuitarras(){
        addLblFlyV2();
        addLblFlyV1();
        addLblVieja2();
        addLblVieja1();
        addLblLespaul2();
        addLblLespaul1();
        addLblRazor2();
        addLblRazor1();

    }
    
    private void addPersonajes(){
        addLblGene();
        addLblMorocho1();
        addLblSaul1();
        addLblSalvatore1();
        addLblWes1();
        addLblLennon();
        addLblSlash1();
        
        addLblGene2();
        addLblMorocho2();
        addLblSaul2();
        addLblSalvatore2();
        addLblWes2();
        addLblLennon2();
        addLblSlash2();
    }
    
    private void addJugadores(){
    	
    	addLblG1();
    	addLblP1();
    	addLblG2();
    	addLblP2();
        
    }
    
    private void activarPersonajesOcultos(){
    	if(mPrincipal.getClave()){

        	imgMorocho1 =RecursoUtil.getImagen("morocho1.png");
            imgSaul1 =RecursoUtil.getImagen("saul1.png");
            imgSalvatore1 =RecursoUtil.getImagen("salva1.png");
        }else{
        	imgMorocho1 =RecursoUtil.getImagen("morocho3.png");
            imgSaul1 =RecursoUtil.getImagen("saul3.png");
            imgSalvatore1 =RecursoUtil.getImagen("salva3.png");

        }
    }
    
    private void initPersonajes(){
    	imgSlashc = RecursoUtil.getImagen("slashc.png");
        imgGenec = RecursoUtil.getImagen("genec.png");
        imgLennonc =RecursoUtil.getImagen("lennon.png");
        imgWesc =RecursoUtil.getImagen("wes.png");
    	
    	imgGene1 =RecursoUtil.getImagen("gene1.png");
        imgGene2 =RecursoUtil.getImagen("gene2.png");
    	
    	imgSlash1 =RecursoUtil.getImagen("slash1.png");
    	imgSlash2 =RecursoUtil.getImagen("slash2.png");

    	imgWes1 =RecursoUtil.getImagen("wes1.png");
        imgWes2 =RecursoUtil.getImagen("wes2.png");
    	
    	imgMorocho =RecursoUtil.getImagen("morocho.png");
    	imgMorocho2 =RecursoUtil.getImagen("morocho2.png");

    	imgSaul =RecursoUtil.getImagen("saul.png");
        imgSaul2 =RecursoUtil.getImagen("saul2.png");
    	
    	imgSalvatore =RecursoUtil.getImagen("salvatore.png");
    	imgSalvatore2 =RecursoUtil.getImagen("salva2.png");
        
        imgFlyv = RecursoUtil.getImagen("flyvmini.png");
        imgLespaul = RecursoUtil.getImagen("lespaulmini.png");
        imgVieja = RecursoUtil.getImagen("viejamini.png");
        imgRazor = RecursoUtil.getImagen("razormini.png");
        
        imgLennon1 =RecursoUtil.getImagen("lennon1.PNG");
    	imgLennon2 =RecursoUtil.getImagen("lennon2.PNG");
    	
    }
    
    private void desactivarPersonajesP2(boolean gene, boolean lennon,boolean slash,
    		boolean wes, boolean morocho, boolean saul, boolean salva ){
    		lblGenep2.setEnabled(gene);
    		lblSlashp2.setEnabled(slash);
    		lblWesp2.setEnabled(wes);
    		lblLennonp2.setEnabled(lennon);
    		lblSaulp2.setEnabled(saul);
    		lblMorochop2.setEnabled(morocho);
    		lblSalvatorep2.setEnabled(salva);    	
    }
    
    private void desactivarPersonajesP1(boolean gene, boolean lennon,boolean slash,
    		boolean wes, boolean morocho, boolean saul, boolean salva ){
    		lblGenep1.setEnabled(gene);
    		lblSlashp1.setEnabled(slash);
    		lblWesp1.setEnabled(wes);
    		lblLennonp1.setEnabled(lennon);
    		lblSaulp1.setEnabled(saul);
    		lblMorochop1.setEnabled(morocho);
    		lblSalvatorep1.setEnabled(salva);    	
    }
}
