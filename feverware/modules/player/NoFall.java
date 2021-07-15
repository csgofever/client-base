package feverware.modules.player;

import org.lwjgl.input.Keyboard;

import feverware.event.Event;
import feverware.event.listener.EventUpdate;
import feverware.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
	
	
	public NoFall() {
		super("NoFall", Keyboard.KEY_N, Category.MOVEMENT);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			if(mc.thePlayer.fallDistance > 2)
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}

}


