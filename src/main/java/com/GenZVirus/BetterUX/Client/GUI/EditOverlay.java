package com.GenZVirus.BetterUX.Client.GUI;

import java.util.List;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class EditOverlay extends Screen {

	private SelectedOverlay leftShield, rightShield, healthBar, foodBar, expBar, fireBar, airBar;
	private Minecraft mc = Minecraft.getInstance();

	public static EditOverlay instance = new EditOverlay(new TranslationTextComponent("EditOverlay"));

	public EditOverlay(ITextComponent titleIn) {
		super(titleIn);
	}

	@Override
	protected void func_231160_c_() {
		leftShield = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosY")), 32, 32, new TranslationTextComponent("LeftShield"), "LeftShield");
		rightShield = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("RightShieldPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("RightShieldPosY")), 32, 32, new TranslationTextComponent("RightShield"), "RightShield");
		healthBar = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("HealthBarPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("HealthBarPosY")), 90, 10, new TranslationTextComponent("HealthBar"), "HealthBar");
		foodBar = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FoodBarPosX")) - 90, mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FoodBarPosY")), 90, 10, new TranslationTextComponent("FoodBar"), "FoodBar");
		expBar = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("ExpBarPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("ExpBarPosY")), 182, 16, new TranslationTextComponent("ExpBar"), "ExpBar");
		fireBar = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FirePosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FirePosY")), 200, 32, new TranslationTextComponent("Fire"), "Fire");
		airBar = new SelectedOverlay(mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("AirBarPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("AirBarPosY")), 182, 16, new TranslationTextComponent("AirBar"), "AirBar");
		this.func_230480_a_(leftShield);
		this.func_230480_a_(rightShield);
		this.func_230480_a_(healthBar);
		this.func_230480_a_(foodBar);
		this.func_230480_a_(expBar);
		this.func_230480_a_(fireBar);
		this.func_230480_a_(airBar);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void func_230430_a_(MatrixStack stack, int p_render_1_, int p_render_2_, float p_render_3_) {
		leftShield.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosX"));
		leftShield.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosY"));
		rightShield.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("RightShieldPosX"));
		rightShield.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("RightShieldPosY"));
		healthBar.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("HealthBarPosX"));
		healthBar.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("HealthBarPosY"));
		foodBar.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FoodBarPosX")) - 90;
		foodBar.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FoodBarPosY"));
		expBar.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("ExpBarPosX"));
		expBar.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("ExpBarPosY"));
		fireBar.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FirePosX"));
		fireBar.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FirePosY"));
		airBar.field_230690_l_ = mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("AirBarPosX"));
		airBar.field_230691_m_ = mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("AirBarPosY"));
		if (!mc.player.isBurning()) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.FIRE));
			AbstractGui.func_238464_a_(new MatrixStack(), mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("FirePosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("FirePosY")), 0, 0, 0, 200, 32, 5792, 200);
		}
		if (!mc.player.areEyesInFluid(FluidTags.WATER) || !(mc.player.getAir() < mc.player.getMaxAir())) {
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR));
			AbstractGui.func_238464_a_(new MatrixStack(), mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("AirBarPosX")), mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("AirBarPosY")), 0, 0, 0, 182, 16, 16, 182);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.WATER_BREATHING_BAR_FILL));
			AbstractGui.func_238464_a_(new MatrixStack(), mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("AirBarPosX")) + 1, mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("AirBarPosY")) + 1, 0, 0, 0, 180, 14, 14, 180);
		}
		if (mc.player.getTotalArmorValue() <= 0) {
			RenderSystem.scalef(0.5F, 0.5F, 0.5F);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_LEFT));
			AbstractGui.func_238464_a_(new MatrixStack(), (mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosX"))) * 2, (mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("LeftShieldPosY"))) * 2, 0, 0, 0, 64, 64, 64, 64);
			mc.getTextureManager().bindTexture(BetterUXResources.getResourceOf(BetterUXResources.ARMOR_RIGHT));
			AbstractGui.func_238464_a_(new MatrixStack(), (mc.getMainWindow().getScaledWidth() / 2 + Integer.parseInt(XMLFileJava.readElement("RightShieldPosX"))) * 2, (mc.getMainWindow().getScaledHeight() + Integer.parseInt(XMLFileJava.readElement("RightShieldPosY"))) * 2, 0, 0, 0, 64, 64, 64, 64);
			RenderSystem.scalef(2.0F, 2.0F, 2.0F);
		}
		super.func_230430_a_(stack, p_render_1_, p_render_2_, p_render_3_);

	}

	public List<Widget> getButtons() {
		return this.field_230710_m_;
	}

}
