package me.wavelength.baseclient.overlay;

import java.awt.Color;
import java.util.List;

import me.wavelength.baseclient.BaseClient;
import me.wavelength.baseclient.event.EventListener;
import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import me.wavelength.baseclient.utils.RenderUtils;
import me.wavelength.baseclient.utils.Strings;

public class ToggledModules1 extends EventListener {
	
	public static int astolfoColors(int yOffset, int yTotal) {
        float speed = 2900F;
        float hue = (float) (System.currentTimeMillis() % (int)speed) + ((yTotal - yOffset) * 9);
        while (hue > speed) {
            hue -= speed;
        }
        hue /= speed;
        if (hue > 0.5) {
            hue = 0.5F - (hue - 0.5f);
        }
        hue += 0.5F;
        return Color.HSBtoRGB(hue, 0.5f, 1F);
    }

	public ToggledModules1() {
		BaseClient.instance.getEventManager().registerListener(this);
	}

	@Override
	public void onRender2D(Render2DEvent event) {
		List<Module> modules = BaseClient.instance.getModuleManager().getToggledModules();

		modules.sort((module1, module2) -> Strings.getStringWidthCFR(Strings.capitalizeFirstLetter(module2.getNameWithAntiCheat())) - Strings.getStringWidthCFR(Strings.capitalizeFirstLetter(module1.getNameWithAntiCheat())));
		int y = 1;

		int relativeYOffset = 3;
		int relativeXOffset = -2;

		int offset = BaseClient.instance.getFontRenderer().getFontSize() / 2 + relativeYOffset;

		for (int i = 0; i < modules.size(); i++) {
			Module module = modules.get(i);
			if (module.getCategory().equals(Category.HIDDEN) || !(module.isShownInModuleArrayList()))
				continue;

			String s = Strings.capitalizeFirstLetter(module.getNameWithAntiCheat());
			int mWidth = Strings.getStringWidthCFR(s);

			/** Draw the black background */
			RenderUtils.drawRect(event.getWidth() - mWidth - relativeYOffset * 2, y - 1, event.getWidth(), y + offset, new Color(0, 0, 0, 100).getRGB());

			/** Draw the line next to the module name */
			RenderUtils.drawRect(event.getWidth() - mWidth - relativeYOffset * 2, y - 1, event.getWidth() - mWidth - 4, y + offset, astolfoColors(10, 1000));

			RenderUtils.drawString(s, event.getWidth() - mWidth + relativeXOffset, y, astolfoColors(10, 1000));
			y += offset + 1;
		}
	}

}