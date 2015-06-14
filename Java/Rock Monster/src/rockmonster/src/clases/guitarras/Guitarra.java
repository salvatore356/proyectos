package clases.guitarras;

import java.awt.Graphics2D;

import javax.swing.*;

import panimador.Animable;
import util.RecursoUtil;

public class Guitarra extends Animable{

    private String nombre;
    private ImageIcon formaGuitarra;
    private int ataque;
    private int radio;

    public Guitarra(String nombre, int dano,int radio){
        this.nombre=nombre;
        ataque=dano;
        this.radio=radio;
        formaGuitarra= RecursoUtil.getImagen(nombre+".png");
    }

    //getters y setters
    public int getAtaque(){
        return this.ataque;
    }
    
    public void setAtaque(int ataque){
        this.ataque=ataque;
    }
    
    public ImageIcon getFormaGuitarra(){
        return this.formaGuitarra;
    }
    public void setFormaGuitarra(String formaGuitarra){
        this.formaGuitarra=RecursoUtil.getImagen(formaGuitarra+".png");
    }

    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public int getRadio(){
        return this.radio;
    }
    public void setRadio(int radio){
        this.radio=radio;
    }

    @Override
    public void simular() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void renderizar(Graphics2D arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
