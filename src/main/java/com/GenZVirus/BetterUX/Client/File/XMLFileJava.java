package com.GenZVirus.BetterUX.Client.File;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.GenZVirus.BetterUX.Client.GUI.BetterOverlay;

import net.minecraftforge.client.gui.ForgeIngameGui;

public class XMLFileJava {

	private static final int default_LeftShieldPosX = -107;
	private static final int default_LeftShieldPosY = -ForgeIngameGui.left_height - 10;
	private static final int default_RightShieldPosX = 94;
	private static final int default_RightShieldPosY = -ForgeIngameGui.left_height - 10;
	private static final int default_HealthBarPosX = -91;
	private static final int default_HealthBarPosY = -ForgeIngameGui.left_height - 11;
	private static final int default_FirePosX = -100;
	private static final int default_FirePosY = -ForgeIngameGui.left_height - 43;
	private static final int default_FoodBarPosX = 91;
	private static final int default_FoodBarPosY = -ForgeIngameGui.left_height - 11;
	private static final int default_AirBarPosX = -91;
	private static final int default_AirBarPosY = -ForgeIngameGui.left_height - 25;
	private static final int default_ExpBarPosX = -91;
	private static final int default_ExpBarPosY = -ForgeIngameGui.left_height;
	private static final int default_BossBarPosX = -160;
	private static final int default_BossBarPosY = 0;
	private static final int default_EffectsPosX = 0;
	private static final int default_EffectsPosY = 0;
	private static final String default_textDisabled = "false";
	private static final String default_hasOverlay = "false";
	private static final String default_Enabled_or_Disabled = "enabled";
	private static final String default_Texture = "genzvirus";
	private static final String default_soundeffects = "true";

	public static String default_xmlFilePath = "betterux/settings.xml";

	public XMLFileJava() {
		try {

			File file = new File(default_xmlFilePath);
			File parent = file.getParentFile();
			if (!parent.exists() && !parent.mkdirs()) { throw new IllegalStateException("Couldn't create dir: " + parent); }

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// Root
			Element root = document.createElement("Root");
			document.appendChild(root);

			// Left Shield X position
			Element leftShieldPosX = document.createElement("LeftShieldPosX");
			leftShieldPosX.appendChild(document.createTextNode(Integer.toString(default_LeftShieldPosX)));
			root.appendChild(leftShieldPosX);

			// Left Shield Y position
			Element leftShieldPosY = document.createElement("LeftShieldPosY");
			leftShieldPosY.appendChild(document.createTextNode(Integer.toString(default_LeftShieldPosY)));
			root.appendChild(leftShieldPosY);

			// Right Shield X position
			Element rightShieldPosX = document.createElement("RightShieldPosX");
			rightShieldPosX.appendChild(document.createTextNode(Integer.toString(default_RightShieldPosX)));
			root.appendChild(rightShieldPosX);

			// Right Shield Y position
			Element rightShieldPosY = document.createElement("RightShieldPosY");
			rightShieldPosY.appendChild(document.createTextNode(Integer.toString(default_RightShieldPosY)));
			root.appendChild(rightShieldPosY);

			// Health X position
			Element healthBarPosX = document.createElement("HealthBarPosX");
			healthBarPosX.appendChild(document.createTextNode(Integer.toString(default_HealthBarPosX)));
			root.appendChild(healthBarPosX);

			// Health Y position
			Element healthBarPosY = document.createElement("HealthBarPosY");
			healthBarPosY.appendChild(document.createTextNode(Integer.toString(default_HealthBarPosY)));
			root.appendChild(healthBarPosY);

			// Fire X position
			Element firePosX = document.createElement("FirePosX");
			firePosX.appendChild(document.createTextNode(Integer.toString(default_FirePosX)));
			root.appendChild(firePosX);

			// Fire Y position
			Element firePosY = document.createElement("FirePosY");
			firePosY.appendChild(document.createTextNode(Integer.toString(default_FirePosY)));
			root.appendChild(firePosY);

			// Food X position
			Element foodBarPosX = document.createElement("FoodBarPosX");
			foodBarPosX.appendChild(document.createTextNode(Integer.toString(default_FoodBarPosX)));
			root.appendChild(foodBarPosX);

			// Food Y position
			Element foodBarPosY = document.createElement("FoodBarPosY");
			foodBarPosY.appendChild(document.createTextNode(Integer.toString(default_FoodBarPosY)));
			root.appendChild(foodBarPosY);

			// Air X position
			Element airBarPosX = document.createElement("AirBarPosX");
			airBarPosX.appendChild(document.createTextNode(Integer.toString(default_AirBarPosX)));
			root.appendChild(airBarPosX);

			// Air Y position
			Element airBarPosY = document.createElement("AirBarPosY");
			airBarPosY.appendChild(document.createTextNode(Integer.toString(default_AirBarPosY)));
			root.appendChild(airBarPosY);

			// EXP X position
			Element expBarPosX = document.createElement("ExpBarPosX");
			expBarPosX.appendChild(document.createTextNode(Integer.toString(default_ExpBarPosX)));
			root.appendChild(expBarPosX);

			// EXP Y position
			Element expBarPosY = document.createElement("ExpBarPosY");
			expBarPosY.appendChild(document.createTextNode(Integer.toString(default_ExpBarPosY)));
			root.appendChild(expBarPosY);

			// Texture
			Element texture = document.createElement("Texture");
			texture.appendChild(document.createTextNode(default_Texture));
			root.appendChild(texture);

			// EXP Y position
			Element bossBarPosX = document.createElement("BossBarPosX");
			bossBarPosX.appendChild(document.createTextNode(Integer.toString(default_BossBarPosX)));
			root.appendChild(bossBarPosX);

			// EXP Y position
			Element bossBarPosY = document.createElement("BossBarPosY");
			bossBarPosY.appendChild(document.createTextNode(Integer.toString(default_BossBarPosY)));
			root.appendChild(bossBarPosY);

			// Enabled or Disabled
			Element enabled_or_disabled = document.createElement("Enabled_Disabled");
			enabled_or_disabled.appendChild(document.createTextNode(default_Enabled_or_Disabled));
			root.appendChild(enabled_or_disabled);

			// Enabled or Disabled
			Element hasOverlay = document.createElement("HasOverlay");
			hasOverlay.appendChild(document.createTextNode(default_hasOverlay));
			root.appendChild(hasOverlay);

			// Enabled or Disabled
			Element textDisabled = document.createElement("TextDisabled");
			textDisabled.appendChild(document.createTextNode(default_textDisabled));
			root.appendChild(textDisabled);

			// Enabled or Disabled
			Element soundEffects = document.createElement("SoundEffects");
			soundEffects.appendChild(document.createTextNode(default_soundeffects));
			root.appendChild(soundEffects);

			// Enabled or Disabled
			Element effectsPosX = document.createElement("EffectsPosX");
			effectsPosX.appendChild(document.createTextNode(Integer.toString(default_EffectsPosX)));
			root.appendChild(effectsPosX);

			// Enabled or Disabled
			Element effectsPosY = document.createElement("EffectsPosY");
			effectsPosY.appendChild(document.createTextNode(Integer.toString(default_EffectsPosY)));
			root.appendChild(effectsPosY);

			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(default_xmlFilePath));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void editElement(String elementTag, String elementTextContent) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(default_xmlFilePath);

			// Get root element

			checkFileElement(document, default_xmlFilePath, elementTag);
			Node element = document.getElementsByTagName(elementTag).item(0);
			element.setTextContent(elementTextContent);
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(default_xmlFilePath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static String readElement(String elementTag) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(default_xmlFilePath);
			checkFileElement(document, default_xmlFilePath, elementTag);
			Node element = document.getElementsByTagName(elementTag).item(0);
			return element.getTextContent();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
		return "0";
	}

	public static void checkFileElement(Document document, String xmlFilePath, String elementTag) {
		Node element = document.getElementsByTagName(elementTag).item(0);
		if (element == null) {
			try {
				element = document.createElement(elementTag);
				element.appendChild(document.createTextNode(Integer.toString(0)));
				Node root = document.getElementsByTagName("Root").item(0);
				root.appendChild(element);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult = new StreamResult(new File(xmlFilePath));
				transformer.transform(domSource, streamResult);
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
			resetElement(elementTag);
		}
	}

	public static void checkFileAndMake() {
		File file = new File(default_xmlFilePath);
		boolean found = file.exists();

		if (!found) {
			new XMLFileJava();
		}

	}

	public static void resetToDefault() {
		XMLFileJava.editElement("LeftShieldPosX", Integer.toString(default_LeftShieldPosX));
		XMLFileJava.editElement("LeftShieldPosY", Integer.toString(default_LeftShieldPosY));
		XMLFileJava.editElement("RightShieldPosX", Integer.toString(default_RightShieldPosX));
		XMLFileJava.editElement("RightShieldPosY", Integer.toString(default_RightShieldPosY));
		XMLFileJava.editElement("HealthBarPosX", Integer.toString(default_HealthBarPosX));
		XMLFileJava.editElement("HealthBarPosY", Integer.toString(default_HealthBarPosY));
		XMLFileJava.editElement("FirePosX", Integer.toString(default_FirePosX));
		XMLFileJava.editElement("FirePosY", Integer.toString(default_FirePosY));
		XMLFileJava.editElement("FoodBarPosX", Integer.toString(default_FoodBarPosX));
		XMLFileJava.editElement("FoodBarPosY", Integer.toString(default_FoodBarPosY));
		XMLFileJava.editElement("AirBarPosX", Integer.toString(default_AirBarPosX));
		XMLFileJava.editElement("AirBarPosY", Integer.toString(default_AirBarPosY));
		XMLFileJava.editElement("ExpBarPosX", Integer.toString(default_ExpBarPosX));
		XMLFileJava.editElement("ExpBarPosY", Integer.toString(default_ExpBarPosY));
		XMLFileJava.editElement("BossBarPosX", Integer.toString(default_BossBarPosX));
		XMLFileJava.editElement("BossBarPosY", Integer.toString(default_BossBarPosY));
		XMLFileJava.editElement("Texture", default_Texture);
		XMLFileJava.editElement("Enabled_Disabled", default_Enabled_or_Disabled);
		XMLFileJava.editElement("HasOverlay", default_hasOverlay);
		XMLFileJava.editElement("TextDisabled", default_textDisabled);
		XMLFileJava.editElement("SoundEffects", default_soundeffects);
		XMLFileJava.editElement("EffectsPosX", Integer.toString(default_EffectsPosX));
		XMLFileJava.editElement("EffectsPosY", Integer.toString(default_EffectsPosY));
	}

	private static void resetElement(String elementTag) {
		if (elementTag.contentEquals("LeftShieldPosX")) {
			XMLFileJava.editElement("LeftShieldPosX", Integer.toString(default_LeftShieldPosX));
		} else if (elementTag.contentEquals("LeftShieldPosY")) {
			XMLFileJava.editElement("LeftShieldPosY", Integer.toString(default_LeftShieldPosY));
		} else if (elementTag.contentEquals("RightShieldPosX")) {
			XMLFileJava.editElement("RightShieldPosX", Integer.toString(default_RightShieldPosX));
		} else if (elementTag.contentEquals("RightShieldPosY")) {
			XMLFileJava.editElement("RightShieldPosY", Integer.toString(default_RightShieldPosY));
		} else if (elementTag.contentEquals("HealthBarPosX")) {
			XMLFileJava.editElement("HealthBarPosX", Integer.toString(default_HealthBarPosX));
		} else if (elementTag.contentEquals("HealthBarPosY")) {
			XMLFileJava.editElement("HealthBarPosY", Integer.toString(default_HealthBarPosY));
		} else if (elementTag.contentEquals("FirePosX")) {
			XMLFileJava.editElement("FirePosX", Integer.toString(default_FirePosX));
		} else if (elementTag.contentEquals("FirePosY")) {
			XMLFileJava.editElement("FirePosY", Integer.toString(default_FirePosY));
		} else if (elementTag.contentEquals("FoodBarPosX")) {
			XMLFileJava.editElement("FoodBarPosX", Integer.toString(default_FoodBarPosX));
		} else if (elementTag.contentEquals("FoodBarPosY")) {
			XMLFileJava.editElement("FoodBarPosY", Integer.toString(default_FoodBarPosY));
		} else if (elementTag.contentEquals("AirBarPosX")) {
			XMLFileJava.editElement("AirBarPosX", Integer.toString(default_AirBarPosX));
		} else if (elementTag.contentEquals("AirBarPosY")) {
			XMLFileJava.editElement("AirBarPosY", Integer.toString(default_AirBarPosY));
		} else if (elementTag.contentEquals("ExpBarPosX")) {
			XMLFileJava.editElement("ExpBarPosX", Integer.toString(default_ExpBarPosX));
		} else if (elementTag.contentEquals("ExpBarPosY")) {
			XMLFileJava.editElement("ExpBarPosY", Integer.toString(default_ExpBarPosY));
		} else if (elementTag.contentEquals("Texture")) {
			XMLFileJava.editElement("Texture", default_Texture);
		} else if (elementTag.contentEquals("BossBarPosX")) {
			XMLFileJava.editElement("BossBarPosX", Integer.toString(default_BossBarPosX));
		} else if (elementTag.contentEquals("BossBarPosY")) {
			XMLFileJava.editElement("BossBarPosY", Integer.toString(default_BossBarPosY));
		} else if (elementTag.contentEquals("Enabled_Disabled")) {
			XMLFileJava.editElement("Enabled_Disabled", default_Enabled_or_Disabled);
		} else if (elementTag.contentEquals("HasOverlay")) {
			XMLFileJava.editElement("HasOverlay", default_hasOverlay);
		} else if (elementTag.contentEquals("TextDisabled")) {
			XMLFileJava.editElement("TextDisabled", default_textDisabled);
		} else if (elementTag.contentEquals("SoundEffects")) {
			XMLFileJava.editElement("SoundEffects", default_soundeffects);
		} else if (elementTag.contentEquals("EffectsPosX")) {
			XMLFileJava.editElement("EffectsPosX", Integer.toString(default_EffectsPosX));
		} else if (elementTag.contentEquals("EffectsPosY")) {
			XMLFileJava.editElement("EffectsPosY", Integer.toString(default_EffectsPosY));
		}
	}

	public static void load() {
		checkFileAndMake();
		BetterOverlay.LeftShieldPosX = Integer.parseInt(XMLFileJava.readElement("LeftShieldPosX"));
		BetterOverlay.LeftShieldPosY = Integer.parseInt(XMLFileJava.readElement("LeftShieldPosY"));
		BetterOverlay.RightShieldPosX = Integer.parseInt(XMLFileJava.readElement("RightShieldPosX"));
		BetterOverlay.RightShieldPosY = Integer.parseInt(XMLFileJava.readElement("RightShieldPosY"));
		BetterOverlay.HealthBarPosX = Integer.parseInt(XMLFileJava.readElement("HealthBarPosX"));
		BetterOverlay.HealthBarPosY = Integer.parseInt(XMLFileJava.readElement("HealthBarPosY"));
		BetterOverlay.FirePosX = Integer.parseInt(XMLFileJava.readElement("FirePosX"));
		BetterOverlay.FirePosY = Integer.parseInt(XMLFileJava.readElement("FirePosY"));
		BetterOverlay.FoodBarPosX = Integer.parseInt(XMLFileJava.readElement("FoodBarPosX"));
		BetterOverlay.FoodBarPosY = Integer.parseInt(XMLFileJava.readElement("FoodBarPosY"));
		BetterOverlay.AirBarPosX = Integer.parseInt(XMLFileJava.readElement("AirBarPosX"));
		BetterOverlay.AirBarPosY = Integer.parseInt(XMLFileJava.readElement("AirBarPosY"));
		BetterOverlay.ExpBarPosX = Integer.parseInt(XMLFileJava.readElement("ExpBarPosX"));
		BetterOverlay.ExpBarPosY = Integer.parseInt(XMLFileJava.readElement("ExpBarPosY"));
		BetterOverlay.Texture = XMLFileJava.readElement("Texture");
		BetterOverlay.BossBarPosX = Integer.parseInt(XMLFileJava.readElement("BossBarPosX"));
		BetterOverlay.BossBarPosY = Integer.parseInt(XMLFileJava.readElement("BossBarPosY"));
		BetterOverlay.Enabled_Disabled = XMLFileJava.readElement("Enabled_Disabled");
		BetterOverlay.HasOverlay = Boolean.parseBoolean(XMLFileJava.readElement("HasOverlay"));
		BetterOverlay.textDisabled = Boolean.parseBoolean(XMLFileJava.readElement("TextDisabled"));
		BetterOverlay.soundEffects = Boolean.parseBoolean(XMLFileJava.readElement("SoundEffects"));
		BetterOverlay.EffectsPosX = Integer.parseInt(XMLFileJava.readElement("EffectsPosX"));
		BetterOverlay.EffectsPosY = Integer.parseInt(XMLFileJava.readElement("EffectsPosY"));
		BetterOverlay.updatePositions();
	}

	public static void save() {
		checkFileAndMake();
		XMLFileJava.editElement("LeftShieldPosX", Integer.toString(BetterOverlay.LeftShieldPosX));
		XMLFileJava.editElement("LeftShieldPosY", Integer.toString(BetterOverlay.LeftShieldPosY));
		XMLFileJava.editElement("RightShieldPosX", Integer.toString(BetterOverlay.RightShieldPosX));
		XMLFileJava.editElement("RightShieldPosY", Integer.toString(BetterOverlay.RightShieldPosY));
		XMLFileJava.editElement("HealthBarPosX", Integer.toString(BetterOverlay.HealthBarPosX));
		XMLFileJava.editElement("HealthBarPosY", Integer.toString(BetterOverlay.HealthBarPosY));
		XMLFileJava.editElement("FirePosX", Integer.toString(BetterOverlay.FirePosX));
		XMLFileJava.editElement("FirePosY", Integer.toString(BetterOverlay.FirePosY));
		XMLFileJava.editElement("FoodBarPosX", Integer.toString(BetterOverlay.FoodBarPosX));
		XMLFileJava.editElement("FoodBarPosY", Integer.toString(BetterOverlay.FoodBarPosY));
		XMLFileJava.editElement("AirBarPosX", Integer.toString(BetterOverlay.AirBarPosX));
		XMLFileJava.editElement("AirBarPosY", Integer.toString(BetterOverlay.AirBarPosY));
		XMLFileJava.editElement("ExpBarPosX", Integer.toString(BetterOverlay.ExpBarPosX));
		XMLFileJava.editElement("ExpBarPosY", Integer.toString(BetterOverlay.ExpBarPosY));
		XMLFileJava.editElement("Texture", BetterOverlay.Texture);
		XMLFileJava.editElement("BossBarPosX", Integer.toString(BetterOverlay.BossBarPosX));
		XMLFileJava.editElement("BossBarPosY", Integer.toString(BetterOverlay.BossBarPosY));
		XMLFileJava.editElement("Enabled_Disabled", BetterOverlay.Enabled_Disabled);
		XMLFileJava.editElement("HasOverlay", Boolean.toString(BetterOverlay.HasOverlay));
		XMLFileJava.editElement("TextDisabled", Boolean.toString(BetterOverlay.textDisabled));
		XMLFileJava.editElement("SoundEffects", Boolean.toString(BetterOverlay.soundEffects));
		XMLFileJava.editElement("EffectsPosX", Integer.toString(BetterOverlay.EffectsPosX));
		XMLFileJava.editElement("EffectsPosY", Integer.toString(BetterOverlay.EffectsPosY));
	}

}
