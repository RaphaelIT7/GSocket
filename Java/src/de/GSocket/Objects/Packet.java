package de.GSocket.Objects;

import java.net.InetAddress;

import org.json.JSONException;
import org.json.JSONObject;

import de.GSocket.GSocket;

public class Packet {
	
	private static PacketHeader Header;
	private static JSONObject Body;
	private static String BodyRaw;
	private static String PacketRaw;
	private InetAddress SenderAddress;
	private Integer SenderPort;
	public Packet(String JSON, InetAddress SenderAddress, Integer SenderPort) {
		this.SenderAddress = SenderAddress;
		this.SenderPort = SenderPort;
		JSONObject packet = new JSONObject(JSON);
	    try {
	        Header = new PacketHeader(this, packet.getJSONObject("Header"));
	    } catch (JSONException e) {
	    	GSocket.Log.warning("Invalid Packet header received! \n" + JSON);
	    }
	    try {
	        Body = new JSONObject(packet.getString("Body"));
	        BodyRaw = packet.getString("Body");
	        PacketRaw = JSON;
	    } catch (JSONException e) {
	    	GSocket.Log.warning("Invalid Packet body received! \n" + JSON);
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
	
	public InetAddress GetSenderAddress() {
		return SenderAddress;
	}
	
	public Integer GetSenderPort() {
		return SenderPort;
	}
}
