package clases.pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.*;

import clases.Terreno;
import panimador.Animable;
import util.RecursoUtil;

public class Barra extends Animable{
    
    ImageIcon barra = RecursoUtil.getImagen("barra.png");
    double ancho, xFuerza, xMovimiento, angulo;
    
    Barra(Terreno t){
    	setX(0);
        setY(t.screenSize.height-barra.getIconHeight());
        ancho=t.screenSize.width;
        xFuerza=0;
        xMovimiento=572;
        angulo=0;
    }


    @Override
    public void simular() {
        
    }

    @Override
    public void renderizar(Graphics2D g) {
         g.drawImage(barra.getImage(), x(),y(),(int) ancho,barra.getIconHeight(),null);
         g.setColor(Color.red);
         g.fillRect((int)(ancho*0.386), y()+54,(int) xFuerza, 37);
         g.setColor(Color.blue);
         g.fillRect((int)(ancho*0.386), y()+99,(int)xMovimiento, 11);
         g.setColor(Color.white);
         g.setFont(new Font("Arial", Font.BOLD, 35));

         if(angulo<91)
          g.drawString((int)angulo+"º", (int)(ancho*0.145), y()+90);
        else
          g.drawString((180-(int)angulo+"º"), (int)(ancho*0.145), y()+90);


         
    }

    @Override
    public void inicializarAnimable() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    //@Override
    public Shape rectanguloContenedor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setXFuerza(double a){
        xFuerza=a;
    }
    void setXMovimiento(double a){
        xMovimiento=a;
    }

    void setAngulo(double a){
        angulo=a;
    }

}
