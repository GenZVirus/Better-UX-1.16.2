package com.GenZVirus.BetterUX.Client.Events;

import java.net.URI;
import java.net.URISyntaxException;

import com.GenZVirus.BetterUX.BetterUX;
import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;
import com.GenZVirus.BetterUX.Client.GUI.BetterUXResources;
import com.GenZVirus.BetterUX.Client.GUI.EditOverlay;
import com.GenZVirus.BetterUX.Client.GUI.SelectedOverlay;
import com.GenZVirus.BetterUX.Client.GUI.Settings;
import com.GenZVirus.BetterUX.Util.KeyboardHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseClickedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseReleasedEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BetterUX.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class BetterOverlayEvents {

	private static Minecraft mc = Minecraft.getInstance();
	private static boolean mousePressed = false;
	private static int cooldown = 0;

	@SubscribeEvent(receiveCanceled = true)
	public static void Overlay(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
			event.setCanceled(true);
			BetterOverlay.renderHealth();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
			event.setCanceled(true);
			BetterOverlay.renderFood();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
			event.setCanceled(true);
			BetterOverlay.renderArmor();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
			event.setCanceled(true);
			BetterOverlay.renderExpBar();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
			event.setCanceled(true);
			BetterOverlay.renderWaterBreathing();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
			event.setCanceled(true);
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public static void FireOverlay(RenderBlockOverlayEvent event) {
		if (event.getOverlayType() == OverlayType.FIRE) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void cooldown(ClientTickEvent event) {

		if (cooldown > 0) {
			cooldown--;
		}
	}

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void Discord(DrawScreenEvent.Post event) {
		if (event.getGui() instanceof IngameMenuScreen) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD));
			RenderSystem.scalef(0.1F, 0.1F, 0.1F);
			RenderSystem.enableBlend();
			int posX = 6;
			int posY = mc.getMainWindow().getScaledHeight() - 28;
			AbstractGui.func_238464_a_(new MatrixStack(), posX * 10, posY * 10, 0, 0, 0, 160, 160, 160, 160);
			RenderSystem.disableBlend();
			RenderSystem.scalef(10.0F, 10.0F, 10.0F);
			mc.fontRenderer.func_238421_b_(new MatrixStack(), "Better UX", posX + 28, posY + 4, 0xFFFFFFFF);
		}
	}

	@SubscribeEvent
	public static void DiscordBackground(DrawScreenEvent.Pre event) {
		if (event.getGui() instanceof IngameMenuScreen) {
			int posX = 0;
			int posY = mc.getMainWindow().getScaledHeight() - 40;
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD_BACKGROUND));
			RenderSystem.enableBlend();
			AbstractGui.func_238464_a_(new MatrixStack(), posX, posY, 0, 0, 0, 120, 40, 40, 120);
			RenderSystem.disableBlend();
		}
	}

	@SubscribeEvent
	public static void MenuOptions(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof IngameMenuScreen) {

			event.addWidget(new Button(mc.getMainWindow().getScaledWidth() / 2 - 102, event.getWidgetList().get(7).field_230691_m_ + 25, 204, 20, new TranslationTextComponent("Better UX Settings"), (x) -> {
				mc.displayGuiScreen(Settings.instance);
			}));
			event.addWidget(new Button(4, mc.getMainWindow().getScaledHeight() - 30, 20, 20, new TranslationTextComponent(" "), (x) -> {
				mc.displayGuiScreen(new ConfirmOpenLinkScreen(BetterOverlayEvents::confirmLink, new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD").getValue(), false));

			}));
		}
	}
	
	public static void confirmLink(boolean p_confirmLink_1_) {
		if (p_confirmLink_1_) {
			try {
				openLink(new URI(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/ty6gQaD").getValue()));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		mc.displayGuiScreen((Screen) null);
	}

	private static void openLink(URI p_openLink_1_) {
		Util.getOSType().openURI(p_openLink_1_);
	}

	@SubscribeEvent
	public static void Arrows(DrawScreenEvent event) {
		if (!(event.getGui() instanceof EditOverlay))
			return;
		SelectedOverlay overlay = null;
		for (Widget button : EditOverlay.instance.getButtons()) {
			if (((SelectedOverlay) button).isOverlaySelected) {
				overlay = (SelectedOverlay) button;
				break;
			}
		}

		if (overlay == null)
			return;

		// UP

		if (KeyboardHelper.isHoldingUP() && cooldown == 0) {
			XMLFileJava.editElement(overlay.name + "PosY", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosY")) - 1));
			cooldown = 3;
		}

		// DOWN

		if (KeyboardHelper.isHoldingDOWN() && cooldown == 0) {
			XMLFileJava.editElement(overlay.name + "PosY", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosY")) + 1));
			cooldown = 3;
		}

		// RIGHT

		if (KeyboardHelper.isHoldingRIGHT() && cooldown == 0) {
			XMLFileJava.editElement(overlay.name + "PosX", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosX")) + 1));
			cooldown = 3;
		}

		// LEFT

		if (KeyboardHelper.isHoldingLEFT() && cooldown == 0) {
			XMLFileJava.editElement(overlay.name + "PosX", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosX")) - 1));
			cooldown = 3;
		}

		if (mousePressed) {
			XMLFileJava.editElement(overlay.name + "PosX", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosX")) + ((int) event.getMouseX() - overlay.field_230690_l_ - overlay.func_230998_h_() / 2)));
			XMLFileJava.editElement(overlay.name + "PosY", Integer.toString(Integer.parseInt(XMLFileJava.readElement(overlay.name + "PosY")) + ((int) event.getMouseY() - overlay.field_230691_m_ - overlay.func_238483_d_() / 2)));
		}
	}

	@SubscribeEvent
	public static void MousePressed(MouseClickedEvent.Pre event) {
		if (!(event.getGui() instanceof EditOverlay))
			return;
		SelectedOverlay overlay = null;
		for (Widget button : EditOverlay.instance.getButtons()) {
			if (((SelectedOverlay) button).isOverlaySelected) {
				overlay = (SelectedOverlay) button;
				break;
			} else {
				int widthIn = button.field_230690_l_;
				int heightIn = button.field_230691_m_;
				int width = button.func_230998_h_();
				int height = button.func_238483_d_();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					mousePressed = true;
				}
			}
		}
		if (overlay == null)
			return;
		int widthIn = overlay.field_230690_l_;
		int heightIn = overlay.field_230691_m_;
		int width = overlay.func_230998_h_();
		int height = overlay.func_238483_d_();
		int x = (int) event.getMouseX();
		int y = (int) event.getMouseY();
		if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
			mousePressed = true;
		}
	}

	@SubscribeEvent
	public static void MouseReleased(MouseReleasedEvent event) {
		if (!(event.getGui() instanceof EditOverlay))
			return;
		mousePressed = false;
	}
}
