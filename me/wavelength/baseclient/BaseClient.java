package me.wavelength.baseclient;

import java.io.File;

import org.lwjgl.opengl.Display;

import com.google.common.collect.Lists;

import me.wavelength.baseclient.account.AccountManager;
import me.wavelength.baseclient.command.CommandManager;
import me.wavelength.baseclient.event.EventManager;
import me.wavelength.baseclient.font.Font;
import me.wavelength.baseclient.friends.FriendsManager;
import me.wavelength.baseclient.gui.altmanager.GuiAltManager;
import me.wavelength.baseclient.gui.clickgui.ClickGui;
import me.wavelength.baseclient.irc.IRCClient;
import me.wavelength.baseclient.module.ModuleManager;
import me.wavelength.baseclient.overlay.TabGui1;
import me.wavelength.baseclient.overlay.ToggledModules1;
import me.wavelength.baseclient.thealtening.AltService;
import me.wavelength.baseclient.utils.Config;
import me.wavelength.baseclient.utils.Files;
import me.wavelength.baseclient.utils.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Locale;

public class BaseClient {

	/**
	 * @formatter:off
	 * Credits
	 * 
	 * Fonts: Slick's font manager edited by Russian412 and color system by me
	 * Alt Manager: Russian412's Alt Manager with some small bug-fixes by me
	 * The Altening Implementation: Russian412
	 * 
	 * Everything else is made by me
	 * @formatter:on
	 **/

	private final String clientName = "FeverWare";
	private final String clientVersion = "b1";
	private final String author = "csgo fever";

	public static BaseClient instance;

	private final String defaultUsername = "csgo fever";

	private EventManager eventManager;

	private FriendsManager friendsManager;

	private CommandManager commandManager;
	private ModuleManager moduleManager;

	private IRCClient ircClient;

	private AccountManager accountManager;

	private AltService altService;

	private Font font;

	private String packageBase = "me.wavelength.baseclient";

	private boolean defaultHotbar = false;

	private Config genericConfig;

	private ClickGui clickGui;

	private Locale englishLocale;

	public BaseClient() {
		instance = this;
	}

	public void initialize() {
		Display.setTitle(String.format("%1$s - %2$s | Loading...", clientName, clientVersion));

		this.englishLocale = new Locale();

		this.ircClient = new IRCClient("chat.freenode.net", 6667, Minecraft.getMinecraft().getSession().getUsername(), "csgo fever");

		new GuiAltManager(); // We create the instance.

		String clientFolder = new File(".").getAbsolutePath();

		clientFolder = (clientFolder.contains("jars") ? new File(".").getAbsolutePath().substring(0, clientFolder.length() - 2) : new File(".").getAbsolutePath()) + Strings.getSplitter() + clientName;

		String accountManagerFolder = clientFolder + Strings.getSplitter() + "alts";

		Files.createRecursiveFolder(accountManagerFolder);

		this.accountManager = new AccountManager(new File(accountManagerFolder));

		this.clickGui = new ClickGui();

		this.eventManager = new EventManager();

		this.friendsManager = new FriendsManager();

		this.moduleManager = new ModuleManager();
		this.commandManager = new CommandManager(".");

		commandManager.registerCommands(); // Moved here to make sure the CommandManager instance is created, else the
											// "commandManager" variable in the Command class would be null (since we are
											// getting the CommandManager instance from this class)

		this.altService = new AltService();

		switchToMojang();

		this.genericConfig = new Config(new File(clientFolder + Strings.getSplitter() + "config.cfg"));
		genericConfig.addDefault("tabguicolor", "5556190");

		/** Setting a custom icon */

		/** Both 16x16 and 32x32 version encoded in Base64 */
		String icon16x16 = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAM+SURBVDhPHZPbThtXGIW/OXs8Po8NwdhgjoaEINQWmqRBpILksldtH6BPwju1V5Va9aKpogYKCUFpCyRNsXHxkfF57LGnO1zMSLO19/7X+tYaKRCa8WXVRDMjpPN3CdsJOuUKkXiMeDxMNKhzVapyVazg+RL9botm6R1ev4EkayjB6Oy+oplkVlbZ+/YrJtIp3GYT34eHn99H1TUC4RidVofhaEQsO8Ww7+G26kiShJzKzpFeXOTB7hc8295iZ2uDoKGj41O6KmMFDKZTCeykzcREEnU4Rh4O0RQNTTeRQ4kEy5+tsfN4i/m4xd3ZOyTtGJ6Y1m73SMSi3LEt+p02brfL2PMYiUcSA8QHshkKcm9thdVsGjsoYwckIobK/fVl6hUHp+GgyBK5/DxPv35K/sE6IXsCSXBDXCMv31vi0cY62biBpcGbk7dcXFzy3Te7ZCcsXvz8A+fn74nGI2yurZJNT2KY+q1/8UL2x2NURWEsFH1cq1dq9FpNTKGicP4HzVqFk6OjWxWWESCViBIWthRxRpZVRADT++mFWSTNIqCp6KrMyxe/c35Z5P35BwFLgBIbP320yeONPMmETaPX5+L0b7yesGdoiX2n7YqNPuWTQ84OjqmWi5yentFpNwjoAWK6Tthz6d78x+HzXxgMBtx0PHqNCuqkZbA2n8GWfJFCmKIoUT4aoVy8ZiGzgCzWRuUaU4qM7rTYnJvByiyijDTOdBFpcmGJVDZDcmaO9Sc7aEsrnNWGAojFP5WrW9J9URwtOcn23pc82d0hLxLRhZX2YIRs53IUynUqIvPaQOb0XYlLp0/P10imsqwtL9PVLT50BgREP5RQCHSDsQB6KSoud/outRuH5y+POb1q8HBvG0X8Dy0BrikZHF3W6Yqqt1C47vsMVI2qK91O731U0HcHIgoDRdz6b6mCIaqri3KNZZnO0BfEB7Rdj+NXf3J4VsARh4vOgMp1FdUMIs+LCHO5DNFYhELpGkVVMQR51x0y9MY4TgczGqVaqRMyTX57W+DXg1c0qg30YBAlNf/Jfr1xw3g8olQoMTU9zes3f9G8aaEZBp1mWyjUBUtZeA9wcPCan77/kbaouNvr8j+uz1BEPJ54tAAAAABJRU5ErkJggg==";
		String icon32x32 = "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";

		/** Calling the #setWindowIcon() method with the two encoded icons */
		Minecraft.getMinecraft().setWindowIcon(icon16x16, icon32x32);
	}

	public void afterMinecraft() {
		Display.setTitle(String.format("%1$s - %2$s", clientName, clientVersion));

		this.font = new Font(packageBase + ".font.fonts", "BwModelicaSS01-RegularCondensed", 50, 25, 30, 33);

		registerHuds();
	}

	private void registerHuds() {
		new ToggledModules1();
		new TabGui1();
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public String getAuthor() {
		return author;
	}

	public String getDefaultUsername() {
		return defaultUsername;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public FriendsManager getFriendsManager() {
		return friendsManager;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public IRCClient getIRCClient() {
		return ircClient;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public AltService getAltService() {
		return altService;
	}

	@Deprecated
	public Font getFontRenderer() {
		return font;
	}

	public Font getFont() {
		return font;
	}

	public String getPackageBase() {
		return packageBase;
	}

	public boolean isDefaultHotbar() {
		return defaultHotbar;
	}

	public Config getGenericConfig() {
		return genericConfig;
	}

	public ClickGui getClickGui() {
		return clickGui;
	}

	public Locale getEnglishLocale() {
		return englishLocale;
	}

	public void switchToMojang() {
		try {
			this.altService.switchService(AltService.EnumAltService.MOJANG);
		} catch (NoSuchFieldException e) {
			System.out.println("Couldn't switch to modank altservice");
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't switch to modank altservice -2");
		}
	}

	public void switchToTheAltening() {
		try {
			this.altService.switchService(AltService.EnumAltService.THEALTENING);
		} catch (NoSuchFieldException e) {
			System.out.println("Couldn't switch to altening altservice");
		} catch (IllegalAccessException e) {
			System.out.println("Couldn't switch to altening altservice -2");
		}
	}

}