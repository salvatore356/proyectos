package clases.sonidos;

import java.io.File;
import java.util.*;

import util.RecursoUtil;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class SonidoFondo implements BasicPlayerListener{

    private double bytesLength;
    private BasicPlayer bp;
    File[] can= new File[10];
    Random random = new Random();

    public SonidoFondo() {
        for(int i=0; i<10; i++)
            can[i]=RecursoUtil.getCancion(i+".mp3");
        bp = new BasicPlayer();
        bp.addBasicPlayerListener(this);
    }


    public void stop() throws BasicPlayerException {
        bp.stop();
    }


    public void play() throws BasicPlayerException {
        bp.play();
    }


    public void open(File a) throws BasicPlayerException {
        bp.open(a);
    }
    
    public void setGain(double gain) throws BasicPlayerException{
    	bp.setGain(gain);
    }

      public void opened(Object arg0, Map arg1) {
       if (arg1.containsKey("audio.length.bytes")) {
            bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
        }
    }

    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
        // Descomentando la siguiente línea se mosrtaría el progreso
       // System.out.println("bytesLength "+bytesLength);
        //System.out.println(" -> " + progressNow);
        if (progressNow==bytesLength){
            System.out.println("TERMINO LA CANCION ");
            int n=random.nextInt(10);
            try {
                System.in.close();
                System.out.close();
                open(can[n]);
                        stop();
                       Thread hilo =Thread.currentThread();
                        play();
                        hilo.join();
            } catch (Exception ex) {}
        }
    }

    public void stateUpdated(BasicPlayerEvent arg0) {}
    public void setController(BasicController arg0) {}
     
}
