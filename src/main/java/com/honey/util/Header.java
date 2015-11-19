package com.honey.util;

/**
 * @author Star.Zhang
 * @date 2014年10月31日 上午11:25:12
 */
public class Header {
	private final static Header xmlHeader = new Header("Content-Type", "text/xml;charset=utf-8");
	private final static Header jsonHeader = new Header("Content-Type", "application/json;charset=utf-8");
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Header(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Header() {
		super();
	}

	public static Header getXmlHeader() {
		return xmlHeader;
	}

	public static Header getJsonHeader() {
		return jsonHeader;
	}

}
