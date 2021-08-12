package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class Bhop extends Module {

	public Bhop() {
		super("Bhop", "bunnyhop", Keyboard.KEY_P, Category.MOVEMENT, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	private boolean isFlying;
	private boolean allowFlying;

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {
		mc.gameSettings.keyBindJump.setPressed(true);
	}

	@Override
	public void onDisable() {
		mc.gameSettings.keyBindJump.setPressed(false);
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		if (this.isToggled()) {

			if (mc.thePlayer.onGround) {
				mc.thePlayer.motionX *= 1.5f;
				mc.thePlayer.motionZ *= 1.5f;
				mc.gameSettings.keyBindJump.setPressed(true);
			}
		}

	}
}