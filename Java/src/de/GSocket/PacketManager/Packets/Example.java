package de.GSocket.PacketManager.Packets;

import de.GSocket.Objects.Packet;
import de.GSocket.PacketManager.PacketBase;

public class Example extends PacketBase {

	public void Recieve(Packet Packet) {
		System.out.println("Packet Type :" + Packet.GetHeader().GetType() + "\n" +
						   "Body Length :" + Packet.GetHeader().GetLength() + "\n" +
						   "Body :" + Packet.GetBodyRaw());
	}
}
