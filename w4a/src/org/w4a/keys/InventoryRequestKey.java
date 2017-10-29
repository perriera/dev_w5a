package org.w4a.keys;

public class InventoryRequestKey {
	
	String token;
	String key;

//	public InventoryRequestKey(String filename) {
//		new RequestKey(new InventoryKey(token, key), userRequest);
//	}
	
	public static String format(UserRequest userRequest, String token, String key) {
		return RequestKey.format(InventoryKey.format(token, key), userRequest);
	}
	
}
