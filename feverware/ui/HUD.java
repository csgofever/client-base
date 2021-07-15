package feverware.ui;

import java.util.Collections;
import java.util.Comparator;

import feverware.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class HUD {
	
	public Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.getName())) {
				return -1;
			}
			
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.getName()) < Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.getName())) {
				return 1;
			}
			return 0;
		}
		
	}
	
	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		FontRenderer fr = mc.fontRendererObj;
		
		
		
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(2, 2, 1);
		GlStateManager.translate(-4, -4, 0);
		fr.drawString(Client.name + " b" + Client.version, 4, 4, -1);
		GlStateManager.translate(4, 4, 0);
		GlStateManager.scale(0.5, 0.5, 1);
		GlStateManager.translate(-4, -4, 0);
		
		int count = 0;
		
		
		
		for(feverware.modules.Module m : Client.modules) {
			if(!m.toggled)
				continue;
			
			double offset =  count*(fr.FONT_HEIGHT + 6);
			
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) - 8, offset, sr.getScaledWidth(), 6 + fr.FONT_HEIGHT + offset + 6, 0x90000000);
			fr.drawString(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) - 4, 4 + offset, -1);
			
			count++;
			
		}
	}

}