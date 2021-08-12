package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.potion.Potion;

public class Step extends Module {

	public Step() {
		super("Step", "Step", Keyboard.KEY_NONE, Category.EXPLOIT, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {
		 mc.thePlayer.stepHeight = .5F;
	}

	@Override
	public void onUpdate(UpdateEvent event) {

		mc.thePlayer.stepHeight = 2F;

	}
}