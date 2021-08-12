package me.wavelength.baseclient.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", "You won't take fall damage", Keyboard.KEY_NONE, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onUpdate(UpdateEvent event) {
		if(mc.thePlayer.fallDistance > 2)
		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
	}

}