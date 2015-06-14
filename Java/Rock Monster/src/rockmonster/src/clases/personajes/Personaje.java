package clases.personajes;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

import clases.Terreno;
import clases.guitarras.Guitarra;
import panimador.Animable;
import util.RecursoUtil;

public class Personaje extends Animable{

    private ImageIcon imagen,fantasma;
    private ImageIcon vect = RecursoUtil.getImagen("vector.png");
    private ImageIcon guia = RecursoUtil.getImagen("guia.png");
    private ImageIcon ataud = RecursoUtil.getImagen("ataud.png");
    private Terreno t;
    private Rectangle2D pj,p1,p2,p3,p4,p5;
    private double angulo=0;
    private double anguloMira=0;
    private double x,y,movimiento,fuerza=0, vida;
    private boolean auxBool=false; //para controlar fuerza
    private boolean auxBol=false; //controla el fantasmas
    private boolean auxBol2=false; //controla el fantasma, tambien identifica cuando este muerto
    private Random ran=new Random();
    private Guitarra guitarra;
    private boolean izq=false;
    private boolean der=false;
    private boolean arriba=false;
    private boolean abajo=false;
    private boolean fuerzab=false;
    private String nombre;
    private double puntos;


    public Personaje(Terreno t, String nombre, Guitarra guit){
        this.t=t;
        puntos=0;
        this.nombre=nombre;
        setImagenes(nombre);
        guitarra= guit;
        setX(ran.nextInt(t.screenSize.width - imagen.getIconWidth()));
        movimiento=0;
        vida=200;
    }

    @Override
    public void simular() {

     pj= new Rectangle2D.Double(x()+(imagen.getIconWidth()*0.25),y(),(int)imagen.getIconWidth()*0.275,imagen.getIconHeight());
     p1= new Rectangle2D.Double(x() +(imagen.getIconWidth()*0.5),y()+(imagen.getIconHeight()*0.95),imagen.getIconWidth()*0.05,imagen.getIconHeight()*0.05);
     p2= new Rectangle2D.Double(x()+(imagen.getIconWidth()*0.2),y()+(imagen.getIconHeight()*0.95),imagen.getIconWidth()*0.05,imagen.getIconHeight()*0.05);
     p3= new Rectangle2D.Double(x()+(imagen.getIconWidth()*0.45),y()+(imagen.getIconHeight()*0.8),imagen.getIconWidth()*0.05,imagen.getIconHeight()*0.05);
     p4= new Rectangle2D.Double(x()+(imagen.getIconWidth()*0.25),y()+(imagen.getIconHeight()*0.8),imagen.getIconWidth()*0.05,imagen.getIconHeight()*0.05);
     p5= new Rectangle2D.Double(x()+(imagen.getIconWidth()*0.35),y()+(imagen.getIconHeight()*0.95),imagen.getIconWidth()*0.05,imagen.getIconHeight()*0.05);
     if(arriba){
        if(anguloMira<270){
        ++anguloMira;
        }
     }

     if(abajo){
        if(-90<anguloMira){
        --anguloMira;
        }
     }
     this.alinear();

     if(der && x() < t.screenSize.width-imagen.getIconWidth() && 0<movimiento){
            if(!t.are.intersects(p3)){
                setX(x()+2);
                setY(y()-1);
            }
     }

     if(izq && 0<x() && 0<movimiento){
         if(!t.are.intersects(p4)){
             setX(x()-2);
             setY(y()-1);
         }
     }

     if(t.screenSize.height<pj.getMaxY())
         vida=0;

     if(y()+pj.getHeight()<=0)
        auxBol2=true;

     if(fuerzab){
     if(fuerza<100 && !auxBool){
            fuerza=fuerza+5;
        }else if(fuerza==100)
            auxBool=true;
        if(auxBool && 0<fuerza)
            fuerza=fuerza-5;
        else auxBool=false;
     }


    }





    @Override
    public void renderizar(Graphics2D g) {

     if(0<vida){
        g.setPaint(Color.red);
        g.fillRect(x(),y()-45, 100, 5);
        g.setPaint(Color.green);
        g.fillRect(x(),y()-45, (int)vida/2, 5);
        g.drawImage(guia.getImage(), x()-80,(int) pj.getCenterY()-120, guia.getIconWidth(),guia.getIconHeight(),null);

        if(anguloMira<91){
            g.drawImage(imagen.getImage(), x(),y(), imagen.getIconWidth(),imagen.getIconHeight(),null);
            g.drawImage(guitarra.getFormaGuitarra().getImage(), (int)(pj.getCenterX()-guitarra.getFormaGuitarra().getIconWidth()*0.4),(int)(pj.getCenterY()-30),guitarra.getFormaGuitarra().getIconWidth(), guitarra.getFormaGuitarra().getIconHeight(),null);
        }
        else{
            g.drawImage(imagen.getImage(),(int) (x()+imagen.getIconWidth()-pj.getWidth()),y(), -imagen.getIconWidth(),imagen.getIconHeight(),null);
            g.drawImage(guitarra.getFormaGuitarra().getImage(), (int)(pj.getCenterX()-guitarra.getFormaGuitarra().getIconWidth()*0.6)+guitarra.getFormaGuitarra().getIconWidth(),(int)(pj.getCenterY()-30),-guitarra.getFormaGuitarra().getIconWidth(), guitarra.getFormaGuitarra().getIconHeight(),null);
        }
            
        g.rotate(-Math.toRadians(anguloMira) , pj.getCenterX(),pj.getCenterY());
        g.drawImage(vect.getImage(), (int) pj.getCenterX(),(int) (pj.getCenterY()-vect.getIconHeight()*0.5), vect.getIconWidth(),vect.getIconHeight(),null);
        g.rotate(Math.toRadians(anguloMira), pj.getCenterX(),pj.getCenterY());

        }else{
            if(!auxBol2){
                g.drawImage(fantasma.getImage(), x(),y(), fantasma.getIconWidth(),fantasma.getIconHeight(),null);
            }else if(auxBol2){
                     g.drawImage(ataud.getImage(), x(),y(), ataud.getIconWidth(),ataud.getIconHeight(),null);
                   }
            }
    }

    @Override
    public void inicializarAnimable() {
        setInteractivo(true);
    }

    public Shape rectanguloContenedor() {
         return pj;
    }

   
    void alinear(){

        if(!t.are.intersects(p2)  && t.are.intersects(p1)){
            x=p5.getCenterX();
            y=p5.getCenterY();
            if(angulo<0 || (!t.are.intersects(p1) && t.are.intersects(p5)))
                angulo=0;
            if(angulo<=35)
                angulo=(angulo +1);
        }else if(t.are.intersects(p2)  && !t.are.intersects(p1)){
                 x=p5.getCenterX();
                 y=p5.getCenterY();
                 if(0<angulo || (!t.are.intersects(p2) && t.are.intersects(p5)))
                    angulo=0;
                if(-35<=angulo)
                angulo=(angulo - 1);
                 }else if((!t.are.intersects(p2)  && !t.are.intersects(p1)) ){
                    angulo=0;
             }

    if(!auxBol){
        if(!t.are.intersects(pj) && (pj.getMaxY()<t.screenSize.height))
           setY(y()+15);
    }

    if(vida<=0 && !auxBol2){
       if(0<pj.getMaxY())
          setY(y()-30);
       else {
        auxBol=true;
        auxBol2=true;
       }
    }
}

    public void setIzq(boolean a){
        this.izq=a;
       movimiento=movimiento-2;
    }

    public void setDer(boolean a){
        this.der=a;
        movimiento=movimiento-2;
    }

    double anguloRadian(double auxD){
           return auxD*Math.PI/180;
    }

    double radianAngulo(double auxD){
           return auxD*180/Math.PI;
    }

    public void incrementarAngulo(boolean a){
        this.arriba=a;
    }

    public void decrementarAngulo(boolean a){
        this.abajo=a;
    }

    public void incrementarFuerza(boolean a){
        fuerzab=a;
    }

    public double getFuerza(){
        return fuerza;
    }
    public double getMovimiento(){
        return movimiento;
    }

    public void renovar(){
        movimiento=200;
        fuerza=0;
    }
    public double getAngulo(){
         return anguloMira;
    }

    public void incrementarVida(double a){
        vida+=a;
    }

    public void decrementarVida(double a){
        vida-=a;
    }

    public double getVida(){
        return vida;
    }

    public Rectangle2D getPj(){
           return pj;
    }

    public double getCentroY(){
        return (pj.getCenterY());
    }

    public String getNombre(){
        return nombre;
    }

     public double getCentroX(){
        return (pj.getCenterX());
    }

    public Guitarra getGuitarra(){
        return guitarra;
    }

    public void setGuitarra(Guitarra g){
        this.guitarra=g;
    }

    public void setImagenes(String nombre){
        imagen = RecursoUtil.getImagen(nombre+".png");
        fantasma = RecursoUtil.getImagen("f"+nombre+".png");
    }

    public void incPuntos(double a){
        puntos+=a;
    }

    public double getPuntos(){
        return puntos;
    }
}


