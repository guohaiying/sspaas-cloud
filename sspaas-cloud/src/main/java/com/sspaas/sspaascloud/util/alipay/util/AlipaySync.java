package com.sspaas.sspaascloud.util.alipay.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sspaas.sspaascloud.util.alipay.config.AlipayConfig;
import com.sspaas.sspaascloud.util.alipay.query.AlipaySubmit;


public class AlipaySync {

	public static boolean isSuccTrade(Map<String, String> map) {
		if (CollectionUtils.isEmpty(map))
			return false;
		String string = map.get("is_success");
		return "T".equals(string);
	}

	public static Map<String, String> syncData(String out_trade_no) throws Exception {

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "single_trade_query");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("trade_no", "");
		sParaTemp.put("out_trade_no", out_trade_no);

		try {
			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
			System.out.println(sHtmlText);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);

			Map<String, String> retMap = new HashMap<String, String>();

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(new ByteArrayInputStream(sHtmlText.getBytes(AlipayConfig.input_charset)));
			doc.normalize();
			Element root = doc.getDocumentElement();

			NodeList childNodes = root.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				if (item.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) item;
					if ("is_success".equalsIgnoreCase(ele.getTagName())) {
						retMap.put(ele.getTagName(), ele.getNodeValue());

					} else if ("response".equals(ele.getTagName())) {
						Element ele2 = (Element) ele.getChildNodes().item(0);
						NodeList childNodes2 = ele2.getChildNodes();
						for (int j = 0; j < childNodes2.getLength(); j++) {
							Element ele3 = (Element) childNodes2.item(j);
							retMap.put(ele3.getTagName(), ele3.getNodeValue());
						}
					} else {
						retMap.put(ele.getTagName(), ele.getNodeValue());
					}
				}
			}

			System.out.println(sHtmlText);
			System.out.println(retMap);

			return retMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Map<String, String> retMap = new HashMap<String, String>();
		String sHtmlText = "<?xml version=\"1.0\" encoding=\"utf-8\"?><util><is_success>T</is_success><request><param name=\"_input_charset\">utf-8</param><param name=\"service\">single_trade_query</param><param name=\"partner\">2088911770737190</param><param name=\"out_trade_no\">2015071715225173864</param></request><response><trade><body>wpmens</body><buyer_email>15068198783</buyer_email><buyer_id>2088702369689815</buyer_id><discount>0.00</discount><flag_trade_locked>0</flag_trade_locked><gmt_create>2015-07-17 15:23:13</gmt_create><gmt_last_modified_time>2015-07-17 15:23:14</gmt_last_modified_time><gmt_payment>2015-07-17 15:23:14</gmt_payment><is_total_fee_adjust>F</is_total_fee_adjust><operator_role>B</operator_role><out_trade_no>2015071715225173864</out_trade_no><payment_type>1</payment_type><price>1.00</price><quantity>1</quantity><seller_email>qianmin@joinclub.cn</seller_email><seller_id>2088911770737190</seller_id><subject>卡瑞姿 魔镜</subject><to_buyer_fee>0.00</to_buyer_fee><to_seller_fee>1.00</to_seller_fee><total_fee>1.00</total_fee><trade_no>2015071700001000810059922047</trade_no><trade_status>TRADE_SUCCESS</trade_status><use_coupon>F</use_coupon></trade></response><sign>0ddb4f88f913a822fdf98148e5dc020b</sign><sign_type>MD5</sign_type></util>";
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(new ByteArrayInputStream(sHtmlText.getBytes(AlipayConfig.input_charset)));
			doc.normalize();
			Element root = doc.getDocumentElement();

			NodeList childNodes = root.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				if (item.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) item;
					if ("is_success".equalsIgnoreCase(ele.getTagName())) {
						retMap.put(ele.getTagName(), ele.getNodeValue());

					} else if ("response".equals(ele.getTagName())) {
						Element ele2 = (Element) ele.getChildNodes().item(0);
						NodeList childNodes2 = ele2.getChildNodes();
						for (int j = 0; j < childNodes2.getLength(); j++) {
							Element ele3 = (Element) childNodes2.item(j);
							retMap.put(ele3.getTagName(), ele3.getNodeValue());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(retMap);
	}
}