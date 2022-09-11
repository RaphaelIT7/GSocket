package de.GSocket.Objects;

import org.json.JSONObject;

public class PacketHeader {
	
	private Packet Packet;
	private String Type;
	private Integer Length;
	public PacketHeader(Packet Packet, JSONObject Header) {
		this.Packet = Packet;
		this.Type = Header.getString("Type");
		this.Length = Header.getInt("Length");
	}
	
	public Packet GetPacket() {
		return Packet;
	}
	
	public String GetType() {
		return Type;
	}
	
	public Integer GetLength() {
		return Length;
	}
}
