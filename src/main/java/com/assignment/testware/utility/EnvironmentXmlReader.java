package com.assignment.testware.utility;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assignment.testware.utility.Constants;

public class EnvironmentXmlReader {

	DocumentBuilderFactory dFactory;
	DocumentBuilder dBuilder;
	Document doc;
	String host = Constants.ENV_HOST;

	String protocol = "";
	String baseurl = "";
	String port = "";
	String endpoint ="";

	public EnvironmentXmlReader() {
		try {
			dFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dFactory.newDocumentBuilder();
			doc = dBuilder.parse(envFile());
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String returnCompleteUri(String serviceName, String apiUnderTest) {

		endpoint = returnEndpoint(serviceName, apiUnderTest);
		return protocol + baseurl + port + endpoint;
	}

	private String returnEndpoint(String serviceName, String apiUnderTest) {
		if (checkRootNode()) {
			NodeList apiNodes = retrunApiNode(serviceName);

			for (int i = 0; i < apiNodes.getLength(); i++) {
				Element eElement = (Element) apiNodes.item(i);
				if (eElement.getAttribute("name").equals(apiUnderTest)) {
					return eElement.getAttribute("endpoint");
				}
			}
		}
		return "";

	}

	private NodeList retrunApiNode(String ServiceName) {
		NodeList serviceNodeList = doc.getElementsByTagName("Service");

		for (int i = 0; i < serviceNodeList.getLength(); i++) {
			Node nNode = serviceNodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				if (eElement.getAttribute("name").equals(ServiceName)) {
					baseurl = eElement.getAttribute("baseurl");
					port = eElement.getAttribute("port");
					NodeList apiNodes = eElement.getElementsByTagName("Api");
					return apiNodes;
				}
			}
		}
		return null;
	}

	private boolean checkRootNode() {
		if (doc.getDocumentElement().getAttribute("name").equals(host)) {
			protocol = doc.getDocumentElement().getAttribute("protocol");
			return true;
		}
		return false;

	}

	private String envFile() {
		if (StringUtils.isBlank(Constants.ENV_HOST)) {
			host = Constants.ENV_PROD;
		}
		return Constants.ENV_XML_PATH + host + ".xml";
	}
}
