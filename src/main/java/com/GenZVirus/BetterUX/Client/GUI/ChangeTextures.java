package com.GenZVirus.BetterUX.Client.GUI;

import com.GenZVirus.BetterUX.Client.File.XMLFileJava;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ChangeTextures extends Screen{

	private Button GenZVirus, Zetta, Squared, Improved;
	private Minecraft mc = Minecraft.getInstance();
	public static ChangeTextures instance = new ChangeTextures(new TranslationTextComponent("ChangeTextures"));
	
	protected ChangeTextures(ITextComponent titleIn) {
		super(titleIn);
	}

	@Override
	protected void init() {
		super.init();
		
		GenZVirus = new Button(mc.getMainWindow().getScaledWidth() / 2 - 105, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("GenZVirus"), (x) -> {
			resetButtons();
			GenZVirus.active = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "genzvirus");
			XMLFileJava.editElement("HasOverlay", "false");
			XMLFileJava.load();
		});
		
		Zetta = new Button(mc.getMainWindow().getScaledWidth() / 2 + 5, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("Zetta"), (x) -> {
			resetButtons();
			Zetta.active = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "zetta");
			XMLFileJava.editElement("HasOverlay", "false");
			XMLFileJava.load();
		});
		Squared = new Button(mc.getMainWindow().getScaledWidth() / 2 + 5, mc.getMainWindow().getScaledHeight() / 2 + 25, 100, 20, new TranslationTextComponent("Squared"), (x) -> {
			resetButtons();
			Squared.active = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "squared");
			XMLFileJava.editElement("HasOverlay", "true");
			XMLFileJava.load();
		});
		
		Improved = new Button(mc.getMainWindow().getScaledWidth() / 2 - 105, mc.getMainWindow().getScaledHeight() / 2 + 25, 100, 20, new TranslationTextComponent("Improved"), (x) -> {
			resetButtons();
			Improved.active = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "improved");
			XMLFileJava.editElement("HasOverlay", "true");
			XMLFileJava.load();
		});
		
		XMLFileJava.checkFileAndMake();
		String texture = XMLFileJava.readElement("Texture");
		if (texture.contentEquals("genzvirus")) {
			GenZVirus.active = false;
		} else if (texture.contentEquals("zetta")) {
			Zetta.active = false;
		} else if (texture.contentEquals("squared")) {
			Squared.active = false;
		} else if (texture.contentEquals("improved")) {
			Improved.active = false;
		}

		this.addButton(GenZVirus);
		this.addButton(Zetta);
		this.addButton(Squared);
//		this.addButton(Improved);
	}
	
	public void resetButtons() {
		for(Widget button : this.buttons) {
			button.active = true;
		}
	}
	
	@Override
	public boolean shouldCloseOnEsc() {
		return true;
	}
	
	@Override
	public boolean isPauseScreen() {
		return true;
	}
	@Override
	public void render(MatrixStack stack, int p_render_1_, int p_render_2_, float p_render_3_) {
		this.renderBackground(stack);
		super.render(stack, p_render_1_, p_render_2_, p_render_3_);
	}
	
}
