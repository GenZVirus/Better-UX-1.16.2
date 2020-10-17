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

import net.minecraftforge.client.gui.ForgeIngameGui;

public class XMLFileJava {

	private static final int default_LeftShieldPosX = -124;
	private static final int default_LeftShieldPosY = -ForgeIngameGui.left_height - 15;
	private static final int default_RightShieldPosX = 92;
	private static final int default_RightShieldPosY = -ForgeIngameGui.left_height - 15;
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
	private static final String default_Texture = "genzvirus";

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

			// EXP Y position
			Element texture = document.createElement("Texture");
			texture.appendChild(document.createTextNode(default_Texture));
			root.appendChild(texture);

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
		XMLFileJava.editElement("Texture", default_Texture);
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
		}
	}

}
