package me.wavelength.baseclient.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;	
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class FullBright extends Module {

	public FullBright() {
		super("FullBright", "No More darkness", Keyboard.KEY_O, Category.RENDER, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}

	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		
	}

}