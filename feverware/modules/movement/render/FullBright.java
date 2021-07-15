package feverware.modules.movement.render;

import org.lwjgl.input.Keyboard;

import feverware.event.Event;
import feverware.event.listener.EventUpdate;
import feverware.modules.Module;

public class FullBright extends Module {
	
	
	public FullBright() {
		super("FullBright", Keyboard.KEY_O, Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}

	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
		
		
		
		}

	}


