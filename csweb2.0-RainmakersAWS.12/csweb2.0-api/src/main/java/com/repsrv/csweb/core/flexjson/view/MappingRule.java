package com.repsrv.csweb.core.flexjson.view;

/**
 * Class that represents a mapping rule. Mappings rules are of the form<br />
 *
 * <pre>
 * user.authorities.name
 * </pre>
 */
public class MappingRule {

	private static final String WILDCARD = "*";

	private String prefix;

	private String attribute;

	private Class<?> clazz;

	private boolean wildcard;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isWildcard() {
		return wildcard;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
		this.wildcard = this.attribute.equals(WILDCARD);
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
