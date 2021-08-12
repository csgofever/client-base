package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.eventintent.listener.invoker;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;

public class Spider extends Module {

	public Spider() {
		super("Spider", "Gives you the ability to climb up blocks", Keyboard.KEY_U, Category.EXPLOIT, AntiCheat.VANILLA, AntiCheat.AAC);
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
		if (this.isToggled()) {
			if(invoker.isCollidedHorizontally()) {
				invoker.setMotionY(0.2);
				
				float var6 = 0.15F;
				
				if(invoker.getMotionX() < (double)-var6) {
					invoker.setMotionX((double)-var6);
				}
				
				if(invoker.getMotionX() > (double)-var6) {
					invoker.setMotionX((double)-var6);
				}
				
				if(invoker.getMotionZ() < (double)-var6) {
					invoker.setMotionX((double)-var6);
				}
				
				if(invoker.getMotionZ() > (double)-var6) {
					invoker.setMotionX((double)-var6);
				}
				
				invoker.setFallDistance(0);
				
				if(invoker.getMotionY() < 0.15D) {
					invoker.setMotionY(-0.15D);
				}
				
			}
		}
	}
}
