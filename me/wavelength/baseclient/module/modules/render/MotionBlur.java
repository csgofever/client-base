package me.wavelength.baseclient.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.wavelength.baseclient.event.events.UpdateEvent;
import me.wavelength.baseclient.module.AntiCheat;	
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.renderer.EntityRenderer;

public class MotionBlur extends Module {

	public MotionBlur() {
		super("MotionBlur", "blur of motion", Keyboard.KEY_O, Category.RENDER, AntiCheat.VANILLA, AntiCheat.AAC);
	}

	@Override
	public void setup() {

	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
		this.mc.entityRenderer.useShader = true;
        if (this.mc.entityRenderer.theShaderGroup != null) {
            this.mc.entityRenderer.theShaderGroup.deleteShaderGroup();
        }
        super.onDisable();
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		
		final EntityRenderer er = this.mc.entityRenderer;
        this.mc.entityRenderer.useShader = true;
        if (this.mc.theWorld != null && (this.mc.entityRenderer.theShaderGroup == null || !this.mc.entityRenderer.theShaderGroup.getShaderGroupName().contains("phosphor"))) {
            if (er.theShaderGroup != null) {
                er.theShaderGroup.deleteShaderGroup();
            }
            er.loadShader(EntityRenderer.shaderResourceLocations[12]);
        }
		
	}

}