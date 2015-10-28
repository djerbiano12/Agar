package agar.main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import agar.controls.Engine;
import agar.controls.Window;

public class Main {
	public static boolean _APPLET = true;

	public static void main(String[] args){
		_APPLET = false;
		
		File f = new File("natives");
		if(f.exists()) System.setProperty("org.lwjgl.librarypath", f.getAbsolutePath());
		
		try{
			AppGameContainer game = new AppGameContainer(new Engine("Agar pour les nuls"));
			game.setDisplayMode(Window.WIDTH, Window.HEIGHT, false);
			game.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
