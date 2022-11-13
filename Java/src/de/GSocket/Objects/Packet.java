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
	private boolean Valid = true;
	public Packet(String JSON, InetAddress SenderAddress, Integer SenderPort) {
		this.SenderAddress = SenderAddress;
		this.SenderPort = SenderPort;
		JSONObject packet = new JSONObject(JSON);
		System.out.println(packet);
	    try {
	        Header = new PacketHeader(this, packet.getJSONObject("Header"));
	    } catch (JSONException e) {
	    	GSocket.Log.warning("Invalid Packet header received! \n" + JSON);
	    	Valid = false;
	    }
	    try {
	    	System.out.println(packet.getJSONObject("Body"));
	    	if(packet.getJSONObject("Body") != null) {
		        Body = packet.getJSONObject("Body");
		        BodyRaw = Body.toString();
		        PacketRaw = JSON;	
	    	} else {
		        Body = new JSONObject(packet.getString("Body"));
		        BodyRaw = packet.getString("Body");
		        PacketRaw = JSON;
	    	}
	    } catch (JSONException e) {
	    	GSocket.Log.warning("Invalid Packet body received! \n" + JSON);
	    	Valid = false;
	    }
	}
	
	public boolean IsValid() {
		return Valid;
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
