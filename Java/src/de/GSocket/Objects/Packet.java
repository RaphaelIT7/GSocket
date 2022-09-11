package de.GSocket.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Packet {
	
	private static PacketHeader Header;
	private static JSONObject Body;
	private static String BodyRaw;
	private static String PacketRaw;
	public Packet(String JSON) {
	    try {
	        JSONObject packet = new JSONObject(JSON);
	        Header = new PacketHeader(this, packet.getJSONObject("Header"));
	        Body = new JSONObject(this, packet.getString("Body"));
	        BodyRaw = packet.getString("Body");
	        PacketRaw = JSON;
	    } catch (JSONException e) {
	    	e.printStackTrace();
	    }
	}
	
	public PacketHeader GetHeader() {
		return Header;
	}
	
	public JSONObject GetBody() {
		return Body;
	}
	
	public String GetBodyRaw() {
		return BodyRaw;
	}
	
	public String GetPacketRaw() {
		return PacketRaw;
	}
}
