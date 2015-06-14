package clases.pantallas.flotantes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.DimensionPantalla;
import util.RecursoUtil;
import clases.BaseDatos;
import clases.pantallas.Principal;

public class AltoPuntaje extends JLabel {

    private JLabel lblOk;
    private JLabel[] lblNombres, lblPuntos;
    private ImageIcon imgFondo, imgOk1, imgOk2;
    private Principal mPrincipal;
    private BaseDatos BD;

    public AltoPuntaje(Principal principal) {
    	this.mPrincipal = principal;
    	init();
    }
    
    private void init(){
    	try {
        	BD = new BaseDatos();
            lblNombres = new JLabel[10];
            lblPuntos = new JLabel[10];
            int h = 110;
            for (int i = 0; i < lblNombres.length; i++) {
            	lblNombres[i] = new JLabel("......");
            	lblNombres[i].setBounds(30, h, 100, 25);
            	lblNombres[i].setForeground(Color.white);
                lblPuntos[i] = new JLabel("0");
                lblPuntos[i].setBounds(200, h, 100, 25);
                lblPuntos[i].setForeground(Color.white);
                add(lblNombres[i]);
                add(lblPuntos[i]);
                h = h + 25;
            }
            BD.cargarLabels(lblNombres, lblPuntos);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
            
        addFondo();
        addBtnOk();
       
        this.setVisible(false);

    }
    
    private void addFondo(){
        imgFondo = RecursoUtil.getImagen("altos_puntajes.png");
        this.setIcon(imgFondo);
        DimensionPantalla.setCenterLabel(this);

    }
    
    private void addBtnOk(){
    	imgOk1 = RecursoUtil.getImagen("ok1.png");
    	imgOk2 = RecursoUtil.getImagen("ok2.png");
        lblOk = new JLabel(imgOk1);
        lblOk.setBounds(200, 350, imgOk1.getIconWidth(), imgOk1.getIconWidth());
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
