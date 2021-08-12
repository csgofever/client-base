package me.wavelength.baseclient.module.modules.semi_hidden;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.server.S0CPacketSpawnPlayer;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import me.wavelength.baseclient.utils.EventReceivePacket;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;

public class AntiBot extends Module {

	public AntiBot() {
		super("AntiBot", "e", Keyboard.KEY_NONE, Category.COMBAT, AntiCheat.ADVANCED, AntiCheat.HYPIXEL);
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
		if (antiCheat.equals("HYPIXEL"))
			for (Object entity : mc.theWorld.loadedEntityList)
				if (((Entity) entity).isInvisible() && entity != mc.thePlayer)
					mc.theWorld.removeEntity((Entity) entity);

	}

	public void onReceivePacket(EventReceivePacket event) {

		if (antiCheat.equals("ADVANCED") && event.getPacket() instanceof S0CPacketSpawnPlayer) {
			S0CPacketSpawnPlayer packet = (S0CPacketSpawnPlayer) event.getPacket();
			double posX = packet.getX() / 32D;
			double posY = packet.getY() / 32D;
			double posZ = packet.getZ() / 32D;

			double diffX = mc.thePlayer.posX - posX;
			double diffY = mc.thePlayer.posY - posY;
			double diffZ = mc.thePlayer.posZ - posZ;

			double dist = Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);

			if (dist <= 17D && posX != mc.thePlayer.posX && posY != mc.thePlayer.posY && posZ != mc.thePlayer.posZ)
				;
		}
	}

}