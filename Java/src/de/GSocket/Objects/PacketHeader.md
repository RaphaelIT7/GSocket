# PacketHeader  
### Public:  
	PacketHeader.GetPacket()  
	PacketHeader.GetType()  

	### (internal)  
	PacketHeader.GetLength()  

## PacketHeader(Packet Packet, JSONObject Header)  

## PacketHeader.GetPacket()  
returns the packet the header belongs to  
returns:  
	Packet (de.GSocket.Objects.Packet)  

## PacketHeader.GetType()  
the type is used as a referance to a packet with a function at the destination  

returns the packet type  
returns:  
	(String) Type  

## PacketHeader.GetLength() (internally used)  
used to check if any data has been lost  
returns the body length  
returns:  
	(Integer) Length  