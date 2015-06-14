package clases.pantallas.flotantes;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import clases.pantallas.Principal;
import util.DimensionPantalla;
import util.RecursoUtil;

public class Salir extends JLabel {

    private ImageIcon imgFondo, imgSi1, imgSi2, imgNo1, imgNo2;
    private JLabel lblSi, lblNo;
    private Principal mPrincipal;

    public Salir(Principal principal) {
    	this.mPrincipal = principal;
    	init();
    }
    
    private void init(){

    	imgFondo = RecursoUtil.getImagen("salirop.png");
    	this.setIcon(imgFondo);
        
    	DimensionPantalla.setCenterLabel(this);
        this.setVisible(false);
        
        addBtnSi();
        addBtnNo();
    }
    
    
    private void addBtnNo(){
        imgNo1 = RecursoUtil.getImagen("no1.PNG");
        imgNo2 = RecursoUtil.getImagen("no2.PNG");

        lblNo = new JLabel(imgNo1);
        lblNo.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                setVisible(false);
                mPrincipal.prenderMenu();
            }

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
            	lblNo.setIcon(imgNo2);
            }

            public void mouseExited(MouseEvent e) {
            	lblNo.setIcon(imgNo1);
            }
        });
        lblNo.setBounds(250, 30, imgNo1.getIconWidth(), imgNo1.getIconHeight());
        add(lblNo);    	
    }
    
    private void addBtnSi(){
        imgSi1 = RecursoUtil.getImagen("si1.PNG");
        imgSi2 = RecursoUtil.getImagen("si2.PNG");
        lblSi = new JLabel(imgSi1);


        lblSi.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
            	mPrincipal.dispose();
                System.exit(0);
            }

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
            	lblSi.setIcon(imgSi2);
            }

            public void mouseExited(MouseEvent e) {
            	lblSi.setIcon(imgSi1);
            }
        });
        lblSi.setBounds(100, 30, imgSi1.getIconWidth(), imgSi1.getIconHeight());
        add(lblSi);    	
    }
}
