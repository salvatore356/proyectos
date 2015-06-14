package util;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class RecursoUtil {
	public static String RECURSOS="res/";
	public static String IMAGENES=RECURSOS+"imagenes/";
	public static String SONIDOS=RECURSOS+"sonidos/";
	public static String TXT=RECURSOS+"txt/";
	public static String BD= TXT+"bd.txt";
			
	public static Random random= new Random();
	
	public static ImageIcon getImagen(String imagen){
		return new ImageIcon(IMAGENES+imagen);
	}
	
	public static ImageIcon imageFullSize(String imagen){
		return imageResize( 
			getImagen(imagen), DimensionPantalla.WIDTH, DimensionPantalla.HEIGHT
		);
	}
	
	public static ImageIcon imageResize(ImageIcon image, int width, int height){
		return new ImageIcon(
			image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
		);
	}
	
	public static File getRandomSound(){
		return new File(SONIDOS+random.nextInt(10)+".mp3");
	}
	
	public static File getCancion(String cancion){
		return new File(SONIDOS+cancion);
	}
}
