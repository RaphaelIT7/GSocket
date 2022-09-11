package de.GSocket.PacketManager;

import java.util.HashMap;

import org.json.JSONObject;

import de.GSocket.GSocket;
import de.GSocket.Objects.Packet;

public class PacketManager {
	
	private HashMap<String, PacketBase> Packets = new HashMap<String, PacketBase>();
	public PacketManager() {}
	public void Initialize() {
		for(@SuppressWarnings("rawtypes") Class c : GSocket.LoadPackages("de.GSocket.PacketManager.Packets")) {
			try {
				AddPacket((PacketBase) c.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void AddPacket(PacketBase Packet) {
		Packets.put(Packet.getClass().getSimpleName(), Packet);
		GSocket.Log.info("Adding Packet " + Packet.getClass().getSimpleName());
	}
	
	public void HandlePacket(String JSON) {
		if(isPacket(JSON)) {
			Packet Packet = new Packet(JSON);
			if(Packet.GetHeader().GetLength() != Packet.GetBodyRaw().length()) {
				GSocket.Log.warning("Recieved Packet has lost Data!");
			} else {
				if(Packets.containsKey(Packet.GetHeader().GetType())) {
					Packets.get(Packet.GetHeader().GetType()).Recieve(Packet);
				}
			}
		}
	}
	
	public boolean isPacket(String JSON) {
		boolean packet = false;
		try {
			new JSONObject(JSON);
			return !packet;
		} catch(Exception e) {}
		return packet;
	}
}
