package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class Speed extends Module {

	public Speed() {
		super("Speed", "read the name", Keyboard.KEY_U, Category.MOVEMENT, AntiCheat.VANILLA, AntiCheat.AAC);
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
			if(mc.thePlayer.onGround) {
				mc.thePlayer.motionX *= 1.3f;
				mc.thePlayer.motionZ *= 1.3f;
				
			}
		}
	}

}