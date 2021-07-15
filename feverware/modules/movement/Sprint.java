package feverware.modules.movement;

import org.lwjgl.input.Keyboard;

import feverware.event.Event;
import feverware.event.listener.EventUpdate;
import feverware.modules.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("Sprint", Keyboard.KEY_H, Category.MOVEMENT);
	}
	
	
	public void onEnable() {
		
	}

	public void onDisable() {
		mc.gameSettings.keyBindSprint.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				mc.gameSettings.keyBindSprint.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
			}
		}
	}
	
}

