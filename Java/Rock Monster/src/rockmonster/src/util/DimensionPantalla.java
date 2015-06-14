package util;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public final class DimensionPantalla {
	public static Dimension SCREEN_DIMENSION=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static int WIDTH =SCREEN_DIMENSION.width;
	public static int HEIGHT =SCREEN_DIMENSION.height;
	
	public static void setFullSizeScreen(JFrame screen, boolean decorate){
		screen.setUndecorated(!decorate);
		screen.setBounds(0, 0, WIDTH, HEIGHT);
		screen.setLayout(null);
	}
	
	public static void setFullSizeLabel(JLabel lbl){
		lbl.setBounds(0, 0, WIDTH, HEIGHT);
	}
	
	public static void setScaledOption(JLabel lbl, float x, float y, int width, int heigth){
		int scaledWidth=(int)(WIDTH * x);
		int scaledHeight=(int) (HEIGHT - (HEIGHT * y));
		lbl.setBounds(scaledWidth, scaledHeight , width, heigth);
	}
	
	public static void setScaledOption2(JLabel lbl, float x, float y){
		int scaledWidth=(int)(WIDTH -(WIDTH * x));
		int scaledHeight=(int) (HEIGHT - (HEIGHT * y));
		lbl.setBounds(scaledWidth, scaledHeight , lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight());
	}
	
	public static void setScaledOption2(JLabel lbl, float x, float y, int width, int height){
		int scaledWidth=(int)(WIDTH -(WIDTH * x));
		int scaledHeight=(int) (HEIGHT - (HEIGHT * y));
		lbl.setBounds(scaledWidth, scaledHeight , width, height);
	}
	
	public static void setScaledOption(JLabel lbl, float x, float y){
		int scaledWidth=(int)(WIDTH * x);
		int scaledHeight=(int) (HEIGHT * y);
		lbl.setBounds(
			scaledWidth, scaledHeight , lbl.getIcon().getIconWidth(), lbl.getIcon().getIconHeight()
		);
	}
	
	public static void setScaledOption3(JLabel lbl, float x, float y, int width, int height){
		int scaledWidth=(int)(WIDTH * x);
		int scaledHeight=(int) (HEIGHT * y);
		lbl.setBounds(scaledWidth, scaledHeight , width, height);
	}
	
	public static void setCenterLabel(JLabel lbl){
		int ancho=lbl.getIcon().getIconWidth();
		int alto=lbl.getIcon().getIconHeight();
		
		lbl.setBounds(
			(WIDTH / 2 - (ancho / 2)),
			(HEIGHT / 2 - (alto / 2)), 
    		ancho, alto
		);
	}
	
	public static void setScaledSize(JLabel label){
		//label.setBounds(0, 0, WIDTH, HEIGHT);
	}
}
