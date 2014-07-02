/**
 * 
 */
package com.jm.constants;

/**
 * @author mzl
 * 
 */
public enum GoodsType {
	SHOPPING_CARD("1"), TRANSPORT_CARD("2"), TELEPHONE_CARD("3");
	
	private final String _type;

	GoodsType(String type) {
		this._type = type;
	}

	/**
	 * @return the _type
	 */
	@Override
	public String toString() {
		return _type;
	}
}
