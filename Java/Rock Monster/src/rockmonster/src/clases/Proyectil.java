package clases;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import javax.swing.ImageIcon;

import clases.personajes.Personaje;
import panimador.Animable;
import panimador.PAnimador;
import util.RecursoUtil;

 public  class  Proyectil extends Animable {

    private double Vx, Vy, Vo, Vtiempo, g, x, y, angulo, puntoy, puntox, viento, viento2,tiempo;
    private int tBala;
    private Terreno bTerreno;
    private PAnimador animador;
    private Ellipse2D explosion;
    private Area area;
    private Personaje per;
    private int muevoTerreno,muevoPegue;
    private Vector<Personaje> p2;
    private boolean moverP=true;
    private ImageIcon proy;

    public Proyectil(PAnimador animador, Terreno t,Personaje per,Vector<Personaje> p2) {
        this.animador = animador;
        proy = RecursoUtil.getImagen("p"+per.getGuitarra().getNombre()+".png");
        this.bTerreno=t;
        this.p2=p2;
        this.per=per;
    }

    public void iniciarTrayectoria(double viento2, double viento, double Vo, double angulo, double Vtiempo){
        this.viento = viento;
        this.viento2 = viento2;
        this.Vo = Vo;
        this.angulo = Math.toRadians(angulo);
        this.puntoy = animador.alto()-per.getCentroY();
        this.puntox = per.getCentroX();
        this.Vtiempo = Vtiempo;
        g = 9.8;
        tiempo = 1;
        Vx = (Vo * Math.cos(this.angulo));
        Vy = (Vo * Math.sin(this.angulo));
        tBala = per.getGuitarra().getRadio();/////modificable
        muevoTerreno=0;
        muevoPegue=0;
        moverP=true;
     }

    
    /**
     * Ecuaciones:
     *      Velocidad X: Vx=Vo*cos(ang)
     *      Velocidad Y: Vy=Vo*sin(ang)
     *      Altura Maxima: ymax=((Vy)^2)/(2*g)     *      
     *      Posicion Y: y=Vo*sen(ang)*t - 1/2 *(g*t^2)     *
     *  Si se hace la Velocidad inicial (Vo) más grande el proyectil alcanzara mas altura
     *  para aumentar la rapidez de desplazaiento aumentamos el contador x
     */
    
    @Override
    public void simular() {

    if(moverP){
        y = ((Vy * tiempo) - (0.5 * (g + viento2) * Math.pow(tiempo, 2)));
        x = ((Vx * tiempo) + (0.5 * viento * Math.pow(tiempo, 2)));        
        tiempo = tiempo + Vtiempo;
        sY(y);
        sX(x);
    
        explosion= new Ellipse2D.Double(x(), y(), per.getGuitarra().getRadio(), per.getGuitarra().getRadio()*2/3);
                   
            if(bTerreno.getArea().intersects(explosion.getBounds())){
                area =new Area(explosion);
                bTerreno.setArea(area);
                ++muevoTerreno;
                per.incPuntos(50);
                if(muevoTerreno>20)
                    moverP=false;
            }

            for( int i=0;i<p2.size();i++){
               if(explosion.intersects(p2.elementAt(i).getPj())){
                if (!p2.elementAt(i).equals(per)){
                    per.incPuntos(100);
                   if(muevoPegue<1)
                	   p2.elementAt(i).decrementarVida(per.getGuitarra().getAtaque());
                   ++muevoPegue;
                   if(muevoTerreno>20)
                	   moverP=false;
                }
               }
            }
        }
    }

    @Override
    public void renderizar(Graphics2D g) {
        if(moverP){
            g.drawImage(proy.getImage(), x(),y(), per.getGuitarra().getRadio(), per.getGuitarra().getRadio()*2/3,null);
        }
    }
////////////////////////////////

    public void sY(double yi) {
        setY(animador.alto() - tBala - yi - puntoy);//tBala OJO modificable
    }

    public void sX(double xi) {
        setX(xi + puntox);
    }
///////////////////////////////

    public double getViento() {
        return viento;
    }

    public void setViento(double viento) {
        this.viento = viento;
    }

    public double getVo() {
        return Vo;
    }

    public void setVo(double Vo) {
        this.Vo = Vo;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public double getPuntox() {
        return puntox;
    }

    public void setPuntox(double puntox) {
        this.puntox = puntox;
    }

    public double getPuntoy() {
        return puntoy;
    }

    public void setPuntoy(double puntoy) {
        this.puntoy = puntoy;
    }

    public double getVtiempo() {
        return Vtiempo;
    }

    public void setVtiempo(double Vtiempo) {
        this.Vtiempo = Vtiempo;
    }

    @Override
    public void inicializarAnimable() {}

    public void setPersonaje(Personaje p){
        this.per=p;
        proy = RecursoUtil.getImagen("p"+p.getGuitarra().getNombre()+".png");
    }

    //@Override
    public Shape rectanguloContenedor() {
        return null;
    }

    public void moverP(boolean a){
        moverP=a;
    }


}
