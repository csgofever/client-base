package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Module {

	public Fly() {
		super("Vanilla_Fly", "Fly freely", Keyboard.KEY_NONE, Category.MOVEMENT,
				AntiCheat.VANILLA, AntiCheat.AAC);
	}

	private boolean isFlying;
	private boolean allowFlying;

	@Override
	public void setup() {
		moduleSettings.addDefault("speed", 1.0D);
	}

	@Override
	public void onEnable() {
		mc.thePlayer.capabilities.allowFlying = true;
	}

	@Override
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
	}

	@Override
	public void onUpdate(UpdateEvent event) {

		mc.thePlayer.capabilities.allowFlying = true;
	}
}
