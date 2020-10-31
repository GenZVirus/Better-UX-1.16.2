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

	private Button GenZVirus, Zetta;
	private Minecraft mc = Minecraft.getInstance();
	public static ChangeTextures instance = new ChangeTextures(new TranslationTextComponent("ChangeTextures"));
	
	protected ChangeTextures(ITextComponent titleIn) {
		super(titleIn);
	}

	@Override
	protected void func_231160_c_() {
		super.func_231160_c_();
		
		GenZVirus = new Button(mc.getMainWindow().getScaledWidth() / 2 - 105, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("GenZVirus"), (x) -> {
			resetButtons();
			GenZVirus.field_230693_o_ = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "genzvirus");
			XMLFileJava.load();
		});
		
		Zetta = new Button(mc.getMainWindow().getScaledWidth() / 2 + 5, mc.getMainWindow().getScaledHeight() / 2 - 5, 100, 20, new TranslationTextComponent("Zetta"), (x) -> {
			resetButtons();
			Zetta.field_230693_o_ = false;
			XMLFileJava.checkFileAndMake();
			XMLFileJava.editElement("Texture", "zetta");
			XMLFileJava.load();
		});
		XMLFileJava.checkFileAndMake();
		String texture = XMLFileJava.readElement("Texture");
		if(texture.contentEquals("genzvirus")) {
			GenZVirus.field_230693_o_ = false;
		} else if(texture.contentEquals("zetta")) {
			Zetta.field_230693_o_ = false;
		}
		
		this.func_230480_a_(GenZVirus);
		this.func_230480_a_(Zetta);
		
	}
	
	public void resetButtons() {
		for(Widget button : this.field_230710_m_) {
			button.field_230693_o_ = true;
		}
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
