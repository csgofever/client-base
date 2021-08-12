package me.wavelength.baseclient.module.modules.semi_hidden;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.eventintent.listener.invoker;
import me.wavelength.baseclient.listeners.EventMotion;
import me.wavelength.baseclient.listeners.MoveEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class FastPlace extends Module {

	public FastPlace() {
		super("FastPlace", "increases your right click speed", Keyboard.KEY_Y, Category.EXPLOIT, AntiCheat.VANILLA, AntiCheat.AAC);

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
			this.mc.rightClickDelayTimer = 0;
		}
		
	}

}
