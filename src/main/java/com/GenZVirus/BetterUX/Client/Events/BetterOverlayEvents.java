package com.GenZVirus.BetterUX.Client.Events;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShapes;
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
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = BetterUX.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class BetterOverlayEvents {

	private static Minecraft mc = Minecraft.getInstance();
	private static boolean mousePressed = false;
	private static int cooldown = 0;
	public static float fading = 1.0F;
	public static boolean increase = false;

	public static int mainWindowWidth = 0;
	public static int mainWindowHeight = 0;

	public static int checkForBosses = 0;
	
	@SubscribeEvent(receiveCanceled = true)
	public static void Overlay(RenderGameOverlayEvent.Pre event) {
		if (BetterOverlay.Enabled_Disabled.contentEquals("disabled"))
			return;
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
			BetterOverlay.renderAirBar();
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
			event.setCanceled(true);
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
			event.setCanceled(true);
		}
		if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO) {
			event.setCanceled(true);
			checkForBosses = 100;
		}
	}

	@SubscribeEvent(receiveCanceled = true)
	public static void FireOverlay(RenderBlockOverlayEvent event) {
		if (BetterOverlay.Enabled_Disabled.contentEquals("disabled"))
			return;
		if (event.getOverlayType() == OverlayType.FIRE) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onPlayerLogging(PlayerLoggedInEvent event) {
		XMLFileJava.load();
	}

	@SubscribeEvent
	public static void checkForClosestBoss(RenderGameOverlayEvent.Pre event) {
		if(event.getType() != RenderGameOverlayEvent.ElementType.CHAT) return;
		if (checkForBosses <= 0)
			return;
		PlayerEntity player = mc.player;
		AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
		AxisAlignedBB aabb = CUBE_BOX.offset(player.getPositionVec()).grow(100);
		List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(player, aabb);
		float minDist = 200;
		Entity Boss = null;
		for (Entity entity : list) {
			if (!entity.isNonBoss() && entity instanceof LivingEntity) {
				float dist = entity.getDistance(player);
				if (dist < minDist) {
					minDist = dist;
					Boss = entity;
				}
			}
		}
		if (Boss != null) {
			BetterOverlay.renderBossBar((LivingEntity) Boss);
		}
	}

	@SubscribeEvent
	public static void checkForBoss(ClientTickEvent event) {
		if (checkForBosses > 0) {
			checkForBosses--;
		}
	}

	@SubscribeEvent
	public static void cooldown(ClientTickEvent event) {
		if (cooldown > 0) {
			cooldown--;
		}
	}

	@SubscribeEvent
	public static void updatePositions(ClientTickEvent event) {
		if (mainWindowWidth != mc.getMainWindow().getScaledWidth() || mainWindowHeight != mc.getMainWindow().getScaledHeight()) {
			mainWindowWidth = mc.getMainWindow().getScaledWidth();
			mainWindowHeight = mc.getMainWindow().getScaledHeight();
			BetterOverlay.updatePositions();
		}
	}

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void Discord(DrawScreenEvent.Post event) {
		if (event.getGui() instanceof Settings && mc.world != null) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD));
			RenderSystem.scalef(0.1F, 0.1F, 0.1F);
			RenderSystem.enableBlend();
			int posX = 6;
			int posY = mc.getMainWindow().getScaledHeight() - 28;
			AbstractGui.blit(new MatrixStack(), posX * 10, posY * 10, 0, 0, 0, 160, 160, 160, 160);
			RenderSystem.disableBlend();
			RenderSystem.scalef(10.0F, 10.0F, 10.0F);
			mc.fontRenderer.drawString(new MatrixStack(), "Better UX", posX + 28, posY + 4, 0xFFFFFFFF);
		}
	}

	@SubscribeEvent
	public static void DiscordBackground(DrawScreenEvent.Pre event) {
		if (event.getGui() instanceof Settings && mc.world != null) {
			int posX = 0;
			int posY = mc.getMainWindow().getScaledHeight() - 40;
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.DISCORD_BACKGROUND));
			RenderSystem.enableBlend();
			AbstractGui.blit(new MatrixStack(), posX, posY, 0, 0, 0, 120, 40, 40, 120);
			RenderSystem.disableBlend();
		}
	}

	@SubscribeEvent
	public static void MenuOptions(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof OptionsScreen && mc.world != null) {
			event.addWidget(new Button(5, event.getGui().height - 25, 100, 20, new TranslationTextComponent("Better UX Settings"), (x) -> {
				mc.displayGuiScreen(Settings.instance);
			}));
		}
		if (event.getGui() instanceof Settings && mc.world != null) {
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
	public static void Arrows(DrawScreenEvent.Post event) {
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
			overlay.substractY(1);
			cooldown = 3;
		}

		// DOWN

		if (KeyboardHelper.isHoldingDOWN() && cooldown == 0) {
			overlay.addY(1);
			cooldown = 3;
		}

		// RIGHT

		if (KeyboardHelper.isHoldingRIGHT() && cooldown == 0) {
			overlay.addX(1);
			cooldown = 3;
		}

		// LEFT

		if (KeyboardHelper.isHoldingLEFT() && cooldown == 0) {
			overlay.substractX(1);
			cooldown = 3;
		}
		if (mousePressed) {
			overlay.setX(event.getMouseX() - overlay.scaledX - overlay.getWidth() / 2);
			overlay.setY(event.getMouseY() - overlay.scaledY - overlay.getHeightRealms() / 2);
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
				int widthIn = button.x;
				int heightIn = button.y;
				int width = button.getWidth();
				int height = button.getHeightRealms();
				int x = (int) event.getMouseX();
				int y = (int) event.getMouseY();
				if (x >= widthIn && x < widthIn + width && y >= heightIn && y < heightIn + height) {
					mousePressed = true;
				}
			}
		}
		if (overlay == null)
			return;
		int widthIn = overlay.x;
		int heightIn = overlay.y;
		int width = overlay.getWidth();
		int height = overlay.getHeightRealms();
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

	@SubscribeEvent
	public static void fadingTick(ClientTickEvent event) {
		if (increase) {
			fading += 0.05;
		} else {
			fading -= 0.05;
		}
		if (fading < 0.0F) {
			increase = true;
		}
		if (fading > 1.0F) {
			increase = false;
		}
	}

}
