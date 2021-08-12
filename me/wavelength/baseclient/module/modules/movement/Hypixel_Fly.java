package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;

public class Hypixel_Fly extends Module {

	public Hypixel_Fly() {
		super("Hypixel_Fly", "Fly on hypixel (not guaranteed bypass)", Keyboard.KEY_F, Category.MOVEMENT, AntiCheat.VANILLA, AntiCheat.AAC);
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
		double y;
		double y1;
		mc.thePlayer.motionY = 0;
		if (mc.thePlayer.ticksExisted % 3 == 0) {
			y = mc.thePlayer.posY - 1.0E-10D;
			mc.thePlayer.sendQueue.addToSendQueue(
					new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y, mc.thePlayer.posZ, true));
		}
		y1 = mc.thePlayer.posY + 1.0E-10D;
		mc.thePlayer.setPosition(mc.thePlayer.posX, y1, mc.thePlayer.posZ);
	}
}
