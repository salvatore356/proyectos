package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JLabel;

import util.RecursoUtil;
import clases.pantallas.Puntuacion;

public class BaseDatos {

    private static File f = new File(RecursoUtil.BD);

    public void cargarLabels(JLabel[] labelsN, JLabel[] labelsP) throws FileNotFoundException, IOException {

        try {
            BufferedReader fileIn = new BufferedReader(new FileReader(f));
            String line = "";
            int i = 0, j = 0;

            while ((line = fileIn.readLine()) != null && i < labelsN.length) {
                for (; j < line.length();) {

                    if (line.charAt(j) == ' ') {
                        labelsN[i].setText(line.substring(0, j));
                        labelsP[i].setText(line.substring(j, line.length()));
                    }

                    j++;
                }
                j = 0;
                i++;
            }

            fileIn.close();
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void guardarJugador(String nombre, long puntuacion) throws IOException {
        Puntuacion[] puntuaciones = new Puntuacion[11];
        Puntuacion aux;        
        

        for(int q=0;q<puntuaciones.length;q++){
            puntuaciones[q]=new Puntuacion("--------", "0");
        }
        
        try {
//cargar arreglos
            BufferedReader fileIn = new BufferedReader(new FileReader(f));

            String line = "";
            int i = 0, j = 0;

            while ((line = fileIn.readLine()) != null && (i < 10)) {
                for (; j < line.length();) {

                    if (line.charAt(j) == ' ') {
                        puntuaciones[i]=new Puntuacion(line.substring(0, j), line.substring(j, line.length()));

                    }

                    j++;
                }
                j = 0;
                i++;
                
            }
            fileIn.close();
//ordenar

            puntuaciones[10].setNombre(nombre);
             puntuaciones[10].setPuntos(""+puntuacion);
          
            for(int y=0;y<puntuaciones.length-1;y++){
                for(int k=0;k<puntuaciones.length-y-1;k++){
                    if(Long.parseLong(puntuaciones[k].getPuntos().trim())<Long.parseLong(puntuaciones[k+1].getPuntos().trim())){
                        aux=puntuaciones[k];
                        puntuaciones[k]=puntuaciones[k+1];
                        puntuaciones[k+1]=aux;
                    }
                }
            }

            for(int k=0;k<10;k++){
                    System.out.println(puntuaciones[k].getNombre()+" "+puntuaciones[k].getPuntos());
            }


//guardar en la base de datos
           
            
            PrintWriter fileOut = new PrintWriter(new FileWriter(f));
            for (int t = 0; t < puntuaciones.length; t++) {

                fileOut.println(puntuaciones[t].getNombre().trim() + " " + puntuaciones[t].getPuntos().trim());
            }

            fileOut.close();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
