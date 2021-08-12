package me.wavelength.baseclient.module.modules.semi_hidden;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class AutoRespawn extends Module {

	public AutoRespawn() {
		super("AutoRespawn", "Automatically respawns you when you die", Keyboard.KEY_NONE, Category.EXPLOIT,
				AntiCheat.VANILLA, AntiCheat.AAC);
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
		if (!this.mc.thePlayer.isEntityAlive()) {
			this.mc.thePlayer.respawnPlayer();
		}
	}

}