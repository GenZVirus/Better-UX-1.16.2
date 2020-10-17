package com.GenZVirus.BetterUX.Client.GUI;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class Settings extends Screen{

	private Button closeButton, resetButton, editButton, changeButton;
	private Minecraft mc = Minecraft.getInstance();
	
	public static Settings instance = new Settings(new TranslationTextComponent("Settings"));
	
	public Settings(ITextComponent titleIn) {
		super(titleIn);
	}
	
	@Override
	protected void func_231160_c_() {
		super.func_231160_c_();
		closeButton = new Button(mc.getMainWindow().getScaledWidth() / 2 + 5, mc.getMainWindow().getScaledHeight() / 2 + 20, 100, 20, new TranslationTextComponent("Close"), (x) -> {
			this.func_231175_as__();
		}, Button.field_238486_s_);
		
		resetButton = new Button(mc.getMainWindow().getScaledWidth() / 2 - 105, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("Reset to Default"), (x) -> {
			XMLFileJava.resetToDefault();
		}, Button.field_238486_s_);
		
		editButton = new Button(mc.getMainWindow().getScaledWidth() / 2 + 5, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("Edit UI"), (x) -> {
			mc.displayGuiScreen(EditOverlay.instance);
		}, Button.field_238486_s_);
		
		changeButton = new Button(mc.getMainWindow().getScaledWidth() / 2 - 105, mc.getMainWindow().getScaledHeight() / 2 + 20, 100, 20, new TranslationTextComponent("Change Textures"), (x) -> {
			mc.displayGuiScreen(ChangeTextures.instance);
		}, Button.field_238486_s_);
		this.func_230480_a_(closeButton);
		this.func_230480_a_(resetButton);
		this.func_230480_a_(editButton);
		this.func_230480_a_(changeButton);
	}
	
	@Override
	public boolean func_231178_ax__() {
		return true;
	}
	
	@Override
	public boolean func_231177_au__() {
		return true;
	}
	
	@Override
	public void func_230430_a_(MatrixStack stack, int p_render_1_, int p_render_2_, float p_render_3_) {
		this.func_230446_a_(stack);
		super.func_230430_a_(stack, p_render_1_, p_render_2_, p_render_3_);
	}
	
}
