package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class JetPack extends Module {

	public JetPack() {
		super("JetPack", "its a jetpack...", Keyboard.KEY_J, Category.MOVEMENT, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onUpdate(UpdateEvent event) {
		if(this.isToggled()) {
			if(mc.gameSettings.keyBindJump.pressed) {
				mc.thePlayer.jump();
			}
		}
	}

}