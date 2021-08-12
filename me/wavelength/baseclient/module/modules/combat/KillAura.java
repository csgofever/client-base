package me.wavelength.baseclient.module.modules.combat;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class KillAura extends Module {

	public KillAura() {
		super("KillAura", "Automatically hits entities around you", Keyboard.KEY_R, Category.COMBAT, AntiCheat.VANILLA,
				AntiCheat.AAC);
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
		if (!this.isToggled())
			return;

		for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
			Object theObject = entities.next();
			if (theObject instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) theObject;

				if (entity instanceof EntityPlayerSP)
					continue;
				
				if(mc.thePlayer.getDistanceToEntity(entity) <= 6.2173613F) {
					if(entity.isEntityAlive()) {
						mc.playerController.attackEntity(mc.thePlayer, entity);
						mc.thePlayer.swingItem();
					}
				}
				
			}
		}
	}

}