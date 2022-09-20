package de.GSocket.PacketManager.Packets;

import de.GSocket.GSocket;
import de.GSocket.Objects.Packet;
import de.GSocket.PacketManager.PacketBase;

public class Example extends PacketBase {

	public void Receive(Packet Packet) {
		GSocket.Log.info("Packet Type :" + Packet.GetHeader().GetType() + "\n" +
						   "Body Length :" + Packet.GetHeader().GetLength() + "\n" +
						   "Body :" + Packet.GetBodyRaw());
		
		GSocket.PacketManager.Connect(Packet.GetSenderAddress(), Packet.GetSenderPort());
		GSocket.PacketManager.SendPacket("Example", Packet.GetBody());
	}
}
