# Packet  
### Public:  
	Packet.GetHeader()  
	Packet.GetBody()  
	Packet.GetBodyRaw()  
	Packet.GetPacketRaw()  
	Packet.GetSenderAddress()  
	Packet.GetSenderPort()  
	

## Packet((String) JSON, InetAddress SenderAddress, Integer SenderPort)  

## Packet.GetHeader()  
returns the packet header  
returns:  
	PacketHeader (de.GSocket.Objects.PacketHeader)  

## Packet.GetBody()  
returns the JSONOBject created from the body  
retuns:  
	JSONObject  

## Packet.GetBodyRaw()  
returns the raw body string  
retuns:  
	String (a JSON String)  

## Packet.GetPacketRaw()  
returns the raw packet string  
retuns:  
	String (a JSON String)  

structure:  
	Header:  
		Type: Example  
		Length: (Body Length)  
	Body:  


## Packet.GetSenderAddress()  
returns the InetAddress from the socket the packet has been received with.  
returns:  
	InetAddress  

## Packet.GetSenderPort()  
returns the Port from the socket the packet has been received with.  
returns:  
	(Integer) Port  