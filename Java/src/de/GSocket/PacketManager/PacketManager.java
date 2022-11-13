package de.GSocket.PacketManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import org.json.JSONObject;

import de.GSocket.GSocket;
import de.GSocket.Objects.Packet;

public class PacketManager {
	
	private HashMap<String, PacketBase> Packets = new HashMap<String, PacketBase>();
	public PacketManager() {}
	
	// searches for Packets and adds them
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
	
	// Handles incoming packets and checks if any data has been lost!
	public void HandlePacket(String JSON, DatagramPacket income) {
		if(isPacket(JSON)) {
			Packet Packet = new Packet(JSON, income.getAddress(), income.getPort());
			if(!Packet.IsValid() || Packet.GetHeader().GetLength() != Packet.GetBodyRaw().length()) {
				GSocket.Log.warning("Recieved Packet has lost Data!");
			} else {
				if(Packets.containsKey(Packet.GetHeader().GetType())) {
					Packets.get(Packet.GetHeader().GetType()).Receive(Packet);
				}
			}
		}
	}
	
	private static DatagramSocket socket;
	public void Connect(InetAddress IP, Integer Port) {
		if(Port == null) {Port = 5000;}
		if(socket != null && socket.isConnected()) {
			
		}
		try {
			socket = new DatagramSocket();
			socket.connect(IP, Port);
		} catch (SocketException e) {
			GSocket.Log.warning("Failed to connect to " + IP + " on port " + Port);
			e.printStackTrace();
		}
	}
	
	public void SendPacket(String Type, JSONObject JSON) {
		if(socket == null || !socket.isConnected()) {
			GSocket.Log.warning("Socket does not exist!");
			return;
		}

		JSONObject Packet = new JSONObject();
		Packet.put("Body", JSON.toString());
		
		JSONObject Header = new JSONObject();
		Header.put("Type", Type);
		Header.put("Length", Packet.getString("Body").length());
		
		Packet.put("Header", Header.toString());
		
		byte[] bytes = Packet.toString().getBytes(); 
		DatagramPacket request = new DatagramPacket(bytes, bytes.length);

		try {
			socket.send(request);
		} catch (IOException e) {
			GSocket.Log.warning("Failed to send Packet!");
			e.printStackTrace();
		}
	}
	
	public boolean isPacket(String JSON) {
		try {
			new JSONObject(JSON);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
