package com.GenZVirus.BetterUX.ModCompatibility;

import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;
import com.GenZVirus.BetterUX.Client.GUI.BetterUXResources;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SurviveComp {
	public static Minecraft mc = Minecraft.getInstance();

	public static void surviveOverlay() {
		if (mc.player == null)
			return;
		PlayerEntity player = mc.player;
		CompoundNBT playerData = player.serializeNBT();
		CompoundNBT ForgeData = playerData.getCompound("ForgeData");
		CompoundNBT survivePD = ForgeData.getCompound("survive:PlayerData");

		// Energy Subfolder

		CompoundNBT EnergyStats = survivePD.getCompound("EnergyStats");
		double EnergyLevel = EnergyStats.getDouble("energyLevel");

		renderEnergy((int) EnergyLevel);

		// Temperature Subfolder
		CompoundNBT TemperatureStats = survivePD.getCompound("TemperatureStats");
		double temperatureLevel = TemperatureStats.getDouble("temperatureLevel");

		switch ((int) temperatureLevel) {
		case 33: {
			renderHypo2();
			break;
		} // Hypothermia II
		case 34: {
			renderHypo2();
			break;
		} // Hypothermia II
		case 35: {
			renderHypo1();
			break;
		} // Hypothermia I
		case 36: {
			renderNormalTemp();
			break;
		} // Normal temp
		case 37: {
			renderNormalTemp();
			break;
		} // Normal temp
		case 38: {
			renderHyper1();
			break;
		} // Hyperthermia I
		case 39: {
			renderHyper2();
			break;
		} // Hyperthermia II
		default:
			break;

		}

		// Water Subfolder

		CompoundNBT WaterStats = survivePD.getCompound("WaterStats");
		double waterLevel = WaterStats.getDouble("waterLevel");
		renderHydration((int) waterLevel);
	}

	@SuppressWarnings("deprecation")
	private static void renderNormalTemp() {
		RenderSystem.scalef(0.4F, 0.4F, 0.4F);
		float RED = 14.0F / 255.0F;
		float GREEN = 209.0F / 255.0f;
		float BLUE = 69.0F / 255.0F;
		RenderSystem.color4f(RED, GREEN, BLUE, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_FILL));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F) + 40, 0, 0, 40, 100, 60, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_BOTTLE));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F), 0, 0, 0, 100, 100, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.scalef(2.5F, 2.5F, 2.5F);
	}

	@SuppressWarnings("deprecation")
	private static void renderHypo1() {
		RenderSystem.scalef(0.4F, 0.4F, 0.4F);
		float RED = 63.0F / 255.0F;
		float GREEN = 72.0F / 255.0F;
		float BLUE = 204.0F / 255.0F;
		RenderSystem.color4f(RED, GREEN, BLUE, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_FILL));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F) + 50, 0, 0, 50, 100, 50, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_BOTTLE));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F), 0, 0, 0, 100, 100, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.scalef(2.5F, 2.5F, 2.5F);
	}

	@SuppressWarnings("deprecation")
	private static void renderHypo2() {
		RenderSystem.scalef(0.4F, 0.4F, 0.4F);
		float RED = 0.0F;
		float GREEN = 243.0F / 255.0F;
		float BLUE = 168.0F / 255.0F;
		RenderSystem.color4f(RED, GREEN, BLUE, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_FILL));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F) + 60, 0, 0, 60, 100, 40, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_BOTTLE));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F), 0, 0, 0, 100, 100, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.scalef(2.5F, 2.5F, 2.5F);
	}

	@SuppressWarnings("deprecation")
	private static void renderHyper1() {
		RenderSystem.scalef(0.4F, 0.4F, 0.4F);
		float RED = 1.0F;
		float GREEN = 127.0F / 255.0F;
		float BLUE = 39.0F / 255.0F;
		RenderSystem.color4f(RED, GREEN, BLUE, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_FILL));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F) + 30, 0, 0, 30, 100, 70, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_BOTTLE));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F), 0, 0, 0, 100, 100, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.scalef(2.5F, 2.5F, 2.5F);
	}

	@SuppressWarnings("deprecation")
	private static void renderHyper2() {
		RenderSystem.scalef(0.4F, 0.4F, 0.4F);
		float RED = 136.0F / 255.0F;
		float GREEN = 0.0F;
		float BLUE = 27.0F / 255.0F;
		RenderSystem.color4f(RED, GREEN, BLUE, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_FILL));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F) + 20, 0, 0, 20, 100, 80, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.THERMOMETER_BOTTLE));
		AbstractGui.blit(new MatrixStack(), (int) (BetterOverlay.tempPosX * 2.5F), (int) (BetterOverlay.tempPosY * 2.5F), 0, 0, 0, 100, 100, 100, 100);

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.scalef(2.5F, 2.5F, 2.5F);
	}

	@SuppressWarnings("deprecation")
	private static void renderEnergy(int energy) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();

		int energyPercentage = (int) (88 * energy / 20);
		if (energyPercentage > 88)
			energyPercentage = 88;

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_RIGHT));
		AbstractGui.blit(new MatrixStack(), BetterOverlay.energyPosX - 90, BetterOverlay.energyPosY, 0, 0, 0, 90, 10, 10, 90);
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SATURATION_BAR_FILL));
		AbstractGui.blit(new MatrixStack(), BetterOverlay.energyPosX - 89 + 88 - energyPercentage, BetterOverlay.energyPosY + 1, 0, 88 - energyPercentage, 0, energyPercentage, 8, 8, 88);

		if (!BetterOverlay.textDisabled) {

			/***********************************
			 * 
			 * Creating display message
			 * 
			 ***********************************/

			String energyText = energy + " / " + 20;

			/***********************************
			 * 
			 * Calculating message length
			 * 
			 ***********************************/

			int stringWidth = mc.fontRenderer.getStringWidth(energyText);

			/***********************************
			 * 
			 * Drawing the message on the screen
			 * 
			 ***********************************/

			mc.fontRenderer.drawString(new MatrixStack(), energyText, BetterOverlay.energyPosX - 45 - stringWidth / 2, BetterOverlay.energyPosY + 1, 0xFFFFFFFF);
		}

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@SuppressWarnings("deprecation")
	private static void renderHydration(int waterLevel) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		/***********************************
		 * 
		 * Enable image transparency
		 * 
		 ***********************************/

		RenderSystem.enableBlend();

		int hydrationPercentage = (int) (88 * waterLevel / 20);
		if (hydrationPercentage > 88)
			hydrationPercentage = 88;

		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_LEFT));
		AbstractGui.blit(new MatrixStack(), BetterOverlay.hydrationPosX, BetterOverlay.hydrationPosY, 0, 0, 0, 90, 10, 10, 90);
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.HYDRATION_FILL));
		AbstractGui.blit(new MatrixStack(), BetterOverlay.hydrationPosX + 1, BetterOverlay.hydrationPosY + 1, 0, 0, 0, hydrationPercentage, 8, 8, 88);

		if (!BetterOverlay.textDisabled) {

			/***********************************
			 * 
			 * Creating display message
			 * 
			 ***********************************/

			String waterText = waterLevel + " / " + 20;

			/***********************************
			 * 
			 * Calculating message length
			 * 
			 ***********************************/

			int stringWidth = mc.fontRenderer.getStringWidth(waterText);

			/***********************************
			 * 
			 * Drawing the message on the screen
			 * 
			 ***********************************/

			mc.fontRenderer.drawString(new MatrixStack(), waterText, BetterOverlay.hydrationPosX + 45 - stringWidth / 2, BetterOverlay.hydrationPosY + 1, 0xFFFFFFFF);
		}

		/***********************************
		 * 
		 * Disable image transparency
		 * 
		 ***********************************/

		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
