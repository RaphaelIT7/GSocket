package de.GSocket.PacketManager;

import de.GSocket.Objects.Packet;

public class PacketBase {
	
	/* will be called when an packet is received.
	 * the packet type needs to be exactly the same as the class Name
	 */
	public void Recieve(Packet packet) {}

}
