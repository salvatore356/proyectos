package clases.sonidos;

import java.io.File;
import java.util.*;

import util.RecursoUtil;
import clases.personajes.Personaje;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class SonidoJuego extends SonidoFondo{
    File[] canc;
    Vector<Personaje> per;

    public SonidoJuego(Vector<Personaje> per){
        this.per=per;
        try {
            super.setGain(0.5);
        } catch (BasicPlayerException ex) {}
        canc= new File[per.size()];

        for(int i=0; i<per.size();i++){
        	RecursoUtil.getCancion(per.elementAt(i).getNombre()+".mp3");
        }
    }

    public void play(int i) throws BasicPlayerException{
            open(canc[i]);
            play();
    }
}
