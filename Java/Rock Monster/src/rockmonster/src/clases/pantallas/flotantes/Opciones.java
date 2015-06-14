package clases.pantallas.flotantes;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.*;

import clases.pantallas.Principal;
import util.DimensionPantalla;
import util.RecursoUtil;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Opciones extends JLabel {

    private JLabel lblOk, lblCalidad, lblMedia, lblAlta, lblSonido, lblSonOp;
    private boolean isSonidoInactivo = false;
    private ImageIcon imgFondo, imgOk1, imgOk2, imgCal, imgMed1, imgMed2;  
    private ImageIcon imgAlt1, imgAlt2, imgCh1, imgCh2, imgSon;
    private Principal mPrincipal;

    public Opciones(Principal principal) {
    	this.mPrincipal = principal;
    	init();
    }
    
    private void init(){
    	addFondo();
        addLblCalidad();
        addMediaOp();
        addAltaOp();
        addLblSonOp();
        addCheckSonido();
        addBtnOk();
    }
    
    private void addFondo(){
    	imgFondo = RecursoUtil.getImagen("menuop.png");
        this.setIcon(imgFondo);
        DimensionPantalla.setCenterLabel(this);
        this.setVisible(false);
    }
    
    private void addLblCalidad(){
    	imgCal = new ImageIcon("res/imagenes/calidad.png");
        lblCalidad = new JLabel(imgCal);
        lblCalidad.setBounds(60, 80, imgCal.getIconWidth(), imgCal.getIconHeight());
        this.add(lblCalidad);
    }
    
    private void addMediaOp(){
        imgMed1 = RecursoUtil.getImagen("media1.png");
        imgMed2 = RecursoUtil.getImagen("media2.png");

        lblMedia = new JLabel(imgMed1);
        lblMedia.setBounds(100, 135, imgMed1.getIconWidth(), imgMed1.getIconHeight());

        lblMedia.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	lblMedia.setIcon(imgMed2);
            	lblAlta.setIcon(imgAlt1);
                mPrincipal.setEfecto(false);

            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(lblMedia);
    }
    
    private void addAltaOp(){
        imgAlt1 = RecursoUtil.getImagen("alta1.png");
        imgAlt2 = RecursoUtil.getImagen("alta2.png");
    	
        lblAlta = new JLabel(imgAlt2);
        lblAlta.setBounds(250, 135, imgAlt2.getIconWidth(), imgAlt2.getIconHeight());

        lblAlta.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                lblMedia.setIcon(imgMed1);
                lblAlta.setIcon(imgAlt2);
                mPrincipal.setEfecto(true);
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(lblAlta);
    }
    
    private void addLblSonOp(){
    	imgSon = RecursoUtil.getImagen("sonido.png");
        lblSonOp= new JLabel(imgSon);
        lblSonOp.setBounds(55, 200, imgSon.getIconWidth(), imgSon.getIconHeight());
        this.add(lblSonOp);
    }
    
    private void addCheckSonido(){
    	
    	imgCh2 = RecursoUtil.getImagen("check2.PNG");
    	imgCh1 = RecursoUtil.getImagen("check1.PNG");
        
        lblSonido = new JLabel(imgCh2);
        lblSonido.setBounds(310, 195, imgCh2.getIconWidth(), imgCh2.getIconHeight());
        lblSonido.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {

            	isSonidoInactivo=!isSonidoInactivo;

            	try{
            		if (isSonidoInactivo) {
                		lblSonido.setIcon(imgCh2);
                        mPrincipal.getRepro().play();
                    
            		} else {
            			lblSonido.setIcon(imgCh1);
                        mPrincipal.getRepro().stop();
            		}
            	} catch (BasicPlayerException ex) {
            		ex.printStackTrace();
            	}
            	
            }

            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        this.add(lblSonido);
    }
    
    private void addBtnOk(){
    	
    	imgOk1 = RecursoUtil.getImagen("ok1.png");
        imgOk2 = RecursoUtil.getImagen("ok2.png");
        lblOk = new JLabel(imgOk1);
        lblOk.setBounds(235, 240, imgOk1.getIconWidth(), imgOk1.getIconWidth());

        lblOk.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
            	setVisible(false);
                mPrincipal.prenderMenu();
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
            	lblOk.setIcon(imgOk2);
            }

            public void mouseExited(MouseEvent e) {
                lblOk.setIcon(imgOk1);
            }
        });
        this.add(lblOk);
    }
    
}
