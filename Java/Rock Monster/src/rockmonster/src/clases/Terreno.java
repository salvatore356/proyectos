package clases;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

import panimador.*;
import util.RecursoUtil;

public class Terreno extends Animable{

  GeneralPath _contorno= new GeneralPath(GeneralPath.WIND_EVEN_ODD);
  private Point2D puntos[];
  public Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
  private int amplitud =60;
  private int amplitud2 = 500;
  private Random ramdom = new Random();
  ImageIcon imagen;
  ImageIcon imagen2;
  public Area are;
  int a = ramdom.nextInt(5)+10;

  public Terreno(String ter){
      imagen = RecursoUtil.getImagen("fondo"+ter+".png");
      imagen2 = RecursoUtil.getImagen("terreno"+ter+".png");
        setX(0);
        setY(0);
  }

  public void setAmbiente(String ter){
      imagen = RecursoUtil.getImagen("fondo"+ter+".png");
      imagen2 = RecursoUtil.getImagen("terreno"+ter+".png");
  }

  public void inicializarAnimable(){

    puntos = new Point2D[a];
        int c = 0;
   for (int i=0;i<puntos.length;i++){
      if((i+1)==puntos.length && c<screenSize.height){
       c=screenSize.height;
       }else
      c+= ramdom.nextInt((int)(screenSize.width*0.25));

       if(i==0){
       puntos[0] = new Point2D.Float(0,ramdom.nextInt(amplitud2+200)+100);

       }else{

       puntos[i] = new Point2D.Float(c,ramdom.nextInt(amplitud2)+200);
   }
 }
    double x=0,y=0;

    _contorno.moveTo(puntos[0].getX(), puntos[0].getY());
    for (int i=1;i<puntos.length;i++){
      x = puntos[i].getX();
      y = puntos[i].getY();
      _contorno.curveTo(puntos[i-1].getX()+amplitud, puntos[i-1].getY(), x-amplitud, y, x, y);
    }

    _contorno.lineTo(screenSize.width, screenSize.height);
    _contorno.lineTo(0, screenSize.height);
    _contorno.closePath();

  }

  public void simular() {
      are = new Area(_contorno);
      _contorno=new GeneralPath(are);
   }

  @Override
  public void renderizar(Graphics2D g) {

    g.drawImage(imagen.getImage(), 0, 0,screenSize.width,screenSize.height,null);
    g.setClip(are);
    g.drawImage(imagen2.getImage(), 0, 0,screenSize.width,screenSize.height,null);
    g.setStroke(new BasicStroke(8.0f));
    g.draw(are);
    g.setClip(null);
  }

    
    public Shape rectanguloContenedor() {
        return null;
    }

    public void setArea(Area a){
      are.subtract(a);
      _contorno=new GeneralPath(are);
    }

    public Area getArea(){
        return are;
    }

}