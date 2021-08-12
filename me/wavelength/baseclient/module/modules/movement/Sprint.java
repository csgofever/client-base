package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", "Keep Sprinting", Keyboard.KEY_NONE, Category.MOVEMENT, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {

		mc.thePlayer.capabilities.allowFlying = true;
	}

	@Override
	public void onDisable() {
		mc.gameSettings.keyBindSprint.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		mc.gameSettings.keyBindSprint.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
	}

}