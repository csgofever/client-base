package feverware;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import feverware.event.Event;
import feverware.modules.Module;
import feverware.modules.movement.*;
import feverware.modules.movement.render.*;
import feverware.modules.player.*;
import feverware.ui.HUD;

public class Client {
	
	public static String name = "FeverWare";

	public static String version = "1";

	public static String creator = "csgofever";
	
	public static Client instance = new Client();
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	

	public static void startup() {
		
		System.out.println("[" + name + "] Starting client, b" + version + ", created by " + creator);
		Display.setTitle(name + " b" + version);
		
		modules.add(new Fly());
		modules.add(new NoFall());
		modules.add(new FullBright());
		modules.add(new Sprint());
		
	}
	
	public static void onEvent(Event e) {
		for(Module m : modules) {
			if(!m.toggled)
				continue;
				
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggle();
			}
		}
	}

}
