package clases.pantallas.flotantes;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.pantallas.Principal;
import util.DimensionPantalla;
import util.RecursoUtil;

public class Cheats extends JLabel {

    private ImageIcon imgFondo, imgOk1, imgOk2;
    private JLabel lblOk;
    private Principal mPrincipal;
    private JTextField txtCheat;

    public Cheats(Principal principal) {
    	this.mPrincipal = principal;
        init();
    }
    
    private void addTextCheat(){
    	txtCheat = new JTextField("INGRESE SU CONTRASEÑA AQUI, DESACTIVADO");
        txtCheat.setEditable(true);
        txtCheat.setBounds(100,190,355,40);
        add(txtCheat);
    }

    private void init(){
        
        addFondo();
        DimensionPantalla.setCenterLabel(this);
        
        addTextCheat();
        addBtnOk();
       
        this.setVisible(false);
    }
    
    private void addFondo(){
    	imgFondo = RecursoUtil.getImagen("menucheats.png");
        this.setIcon(imgFondo);
    }
    
    private void addBtnOk(){
        
    	imgOk1 = RecursoUtil.getImagen("ok1.png");
        imgOk2 = RecursoUtil.getImagen("ok2.png");
        
        lblOk = new JLabel(imgOk1);

        
        lblOk.addMouseListener(new java.awt.event.MouseListener() {

            public void mouseClicked(MouseEvent e) {
                
            	if(txtCheat.getText().equalsIgnoreCase("skullbockz")){
                    mPrincipal.setClave(true);
                    JOptionPane.showMessageDialog(new JDialog(), "FELICITACIONES CONTRASEÃ‘AS ACTIVADAS");
                }

                txtCheat.setText("");
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
        
        lblOk.setBounds(235, 240, imgOk1.getIconWidth(), imgOk1.getIconHeight());
        add(lblOk);
    	
    }
    
}
