package com.GenZVirus.BetterUX.Client.GUI;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.GenZVirus.BetterUX.ModCompatibility.VampirismComp;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.CombatRules;
import net.minecraft.util.FoodStats;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

@OnlyIn(Dist.CLIENT)
public class BetterOverlay {

	private static Minecraft mc = Minecraft.getInstance();
	private static int delay = 0;
	public static int fire_offset = 0;

	public static void renderOverlay() {

		renderArmor();
		renderExpBar();
		renderFood();
		renderHealth();
	}

	private static void checkInGame() {
		if (mc.world == null) { return; }

		assert mc.player != null;

		if (!Minecraft.isGuiEnabled()) { return; }
	}

	private static void incrementOverlayMovement() {
		if (Minecraft.debugFPS / 10 <= delay) {
			fire_offset++;
			delay = 0;
		}
		delay++;

		if (fire_offset > 31) {
			fire_offset = 0;
		}
	}

	@SuppressWarnings("deprecation")
	public static void renderHealth() {
		checkInGame();
		incrementOverlayMovement();
		if (mc.player.isCreative()) { return; }

		mc.getProfiler().startSection("BUXHealth");
		RenderSystem.scalef(1.0F, 1.0F, 1.0F);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

		// Health Bar

		RenderSystem.enableBlend();
		XMLFileJava.checkFileAndMake();
		int HealthPosX = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("HealthBarPosX"));
		int HealthPosY = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("HealthBarPosY"));
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_LEFT));
		AbstractGui.func_238464_a_(new MatrixStack(), HealthPosX, HealthPosY, 0, 0, 0, 90, 10, 10, 90);
		int percentage = (int) (88 * mc.player.getHealth() / mc.player.getMaxHealth());
		if (percentage > 88)
			percentage = 88;
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.HEALTH_BAR_FILL));
		if (mc.player.isPotionActive(Effects.POISON)) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.POISONED_HEALTH_BAR_FILL));
		}
		if (mc.player.isPotionActive(Effects.WITHER)) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WITHERING_HEALTH_BAR_FILL));
		}
		AbstractGui.func_238464_a_(new MatrixStack(), HealthPosX + 1, HealthPosY + 1, 0, 0, 0, percentage, 8, 8, 88);
		if (mc.player.getAbsorptionAmount() > 0) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SHIELD_BAR));
			AbstractGui.func_238464_a_(new MatrixStack(), HealthPosX, HealthPosY, 0, 0, 0, 90, 10, 10, 90);
		}

		if (mc.player.isBurning()) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FIRE));
			int FirePosX = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FirePosX"));
			int FirePosY = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FirePosY"));
			AbstractGui.func_238464_a_(new MatrixStack(), FirePosX, FirePosY, 0, 0, fire_offset * 32, 200, 32, 5792, 200);
		}
		RenderSystem.disableBlend();

		// Health Text

		String health = Integer.toString((int) mc.player.getHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth());
		String swidth = Integer.toString((int) mc.player.getMaxHealth()) + " / " + Integer.toString((int) mc.player.getMaxHealth());
		if (mc.player.getAbsorptionAmount() > 0) {
			health += " | " + Integer.toString((int) mc.player.getAbsorptionAmount());
			swidth += " | " + Integer.toString((int) mc.player.getAbsorptionAmount());
		}
		int stringWidth = mc.fontRenderer.getStringWidth(swidth);
		int offset = mc.fontRenderer.getStringWidth(health);
		mc.fontRenderer.func_238421_b_(new MatrixStack(), health, HealthPosX + 45 - stringWidth / 2 + (stringWidth - offset), HealthPosY + 1, 0xFFFFFFFF);
		mc.getProfiler().endSection();
	}

	public static void renderFood() {
		checkInGame();
		if (mc.player.isCreative()) { return; }
		mc.getProfiler().startSection("BUXFood");
		mc.getTextureManager().bindTexture(Screen.field_230665_h_);
		RenderSystem.enableBlend();
		XMLFileJava.checkFileAndMake();
		int posX = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FoodBarPosX"));
		int posY = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FoodBarPosY"));
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.BAR_RIGHT));
		AbstractGui.func_238464_a_(new MatrixStack(), posX - 90, posY, 0, 0, 0, 90, 10, 10, 90);
		if (ModList.get().isLoaded("vampirism")) {
			VampirismComp.bloodOverlay();
		} else {
			FoodStats stats = mc.player.getFoodStats();
			int level = stats.getFoodLevel();
			int percentage = (int) (88 * level / 20);
			if (percentage > 88)
				percentage = 88;
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FOOD_BAR_FILL));
			AbstractGui.func_238464_a_(new MatrixStack(), posX - 89 + 88 - percentage, posY + 1, 0, 88 - percentage, 0, percentage, 8, 8, 88);
			level = (int) stats.getSaturationLevel();
			percentage = (int) (88 * level / 20);
			if (percentage > 88)
				percentage = 88;
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.SATURATION_BAR_FILL));
			AbstractGui.func_238464_a_(new MatrixStack(), posX - 89 + 88 - percentage, posY + 1, 0, 88 - percentage, 0, percentage, 8, 8, 88);
			String food = ((int) stats.getFoodLevel()) + " | " + ((int) stats.getSaturationLevel());
			int stringWidth = mc.fontRenderer.getStringWidth(food);
			mc.fontRenderer.func_238421_b_(new MatrixStack(), food, posX - 45 - stringWidth / 2, posY + 1, 0xFFFFFFFF);
			RenderSystem.disableBlend();
			mc.getProfiler().endSection();
		}
	}

	@SuppressWarnings("deprecation")
	public static void renderArmor() {
		checkInGame();
		if (mc.player.isCreative()) { return; }
		if (mc.player.getTotalArmorValue() <= 0)
			return;

		mc.getProfiler().startSection("BUXArmor");
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_LEFT));
		RenderSystem.enableBlend();
		XMLFileJava.checkFileAndMake();
		int leftShieldPosX = Minecraft.getInstance().getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosX"));
		int leftShieldPosY = Minecraft.getInstance().getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosY"));
		
		int rightShieldPosX = Minecraft.getInstance().getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("RightShieldPosX"));
		int rightShieldPosY = Minecraft.getInstance().getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("RightShieldPosY"));

		float totalArmor = mc.player.getTotalArmorValue();
		float toughness = (float) mc.player.getAttribute(Attributes.field_233827_j_).getValue();
		RenderSystem.scalef(0.5F, 0.5F, 0.5F);
		AbstractGui.func_238464_a_(new MatrixStack(), leftShieldPosX * 2, leftShieldPosY * 2, 0, 0, 0, 64, 64, 64, 64);
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_RIGHT));
		AbstractGui.func_238464_a_(new MatrixStack(), rightShieldPosX * 2, rightShieldPosY * 2, 0, 0, 0, 64, 64, 64, 64);
		RenderSystem.scalef(2.0F, 2.0F, 2.0F);
		float damageReduction = Math.round((100.0F - CombatRules.getDamageAfterAbsorb(100, totalArmor, toughness)) * 10) / 10.0F;
		for (ItemStack stack : mc.player.getArmorInventoryList()) {
			damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
		}
		if (damageReduction > 100)
			damageReduction = 100;
		String s = damageReduction + "%";
		mc.fontRenderer.func_238421_b_(new MatrixStack(), s, leftShieldPosX + 16 - mc.fontRenderer.getStringWidth(s) / 2, leftShieldPosY + 10, 0xFFFFFFFF);
		damageReduction = Math.round(4.0 * totalArmor * 10) / 10;
		for (ItemStack stack : mc.player.getArmorInventoryList()) {
			damageReduction += EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, stack);
		}
		if (damageReduction > 100)
			damageReduction = 100;
		s = damageReduction + "%";
		mc.fontRenderer.func_238421_b_(new MatrixStack(), s, rightShieldPosX + 17 - mc.fontRenderer.getStringWidth(s) / 2, leftShieldPosY + 10, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		mc.getProfiler().endSection();
	}

	@SuppressWarnings("deprecation")
	public static void renderExpBar() {
		checkInGame();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getProfiler().startSection("BUXExpBar");
		mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.EXP_BAR));
		RenderSystem.enableBlend();
		XMLFileJava.checkFileAndMake();
		int k = (int) (mc.player.experience * 180.0F);
		int posX = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("ExpBarPosX"));
		int posY = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("ExpBarPosY"));
		AbstractGui.func_238464_a_(new MatrixStack(), posX, posY, 0, 0, 0, 182, 16, 16, 182);
		if (k > 0) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.EXP_BAR_FILL));
			AbstractGui.func_238464_a_(new MatrixStack(), posX + 1, posY + 1, 0, 0, 0, k, 14, 14, 180);
		}
		mc.getProfiler().endSection();
		if (mc.player.experienceLevel > 0) {
			mc.getProfiler().startSection("BUXExpLevel");
			String s = "" + mc.player.experienceLevel;
			RenderSystem.scalef(0.8F, 0.8F, 0.8F);
			mc.fontRenderer.func_238421_b_(new MatrixStack(), s, (posX + 93 - mc.fontRenderer.getStringWidth(s) / 2) * 1.25F, (posY + 5) * 1.25F, 0xFFFFFFFF);
			RenderSystem.scalef(1.25F, 1.25F, 1.25F);
			mc.getProfiler().endSection();
		}
		RenderSystem.disableBlend();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@SuppressWarnings("deprecation")
	public static void renderWaterBreathing() {
		checkInGame();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		XMLFileJava.checkFileAndMake();
		if (mc.player.areEyesInFluid(FluidTags.WATER) || mc.player.getAir() < mc.player.getMaxAir()) {
			mc.getProfiler().startSection("BUXWaterBreathingBar");
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR));
			RenderSystem.enableBlend();
			int k = (int) (((float) mc.player.getAir() / mc.player.getMaxAir()) * 180.0F);
			int posX = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("AirBarPosX"));
			int posY = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("AirBarPosY"));
			AbstractGui.func_238464_a_(new MatrixStack(), posX, posY, 0, 0, 0, 182, 16, 16, 182);
			if (k > 0) {
				mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR_FILL));
				AbstractGui.func_238464_a_(new MatrixStack(), posX + 1, posY + 1, 0, 0, 0, k, 14, 14, 180);
			}
			RenderSystem.disableBlend();
			mc.getProfiler().endSection();
			RenderSystem.enableBlend();
		}
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
