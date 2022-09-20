# Packet  
| Function                  | Internally Used |
| ------------------------- | --------------- | 
| Packet.GetHeader()        | No              |
| Packet.GetBody()          | No              |
| Packet.GetBodyRaw()       | No              |
| Packet.GetPacketRaw()     | No              |
| Packet.GetSenderAddress() | No              |
| Packet.GetSenderPort()    | No              |
	

## Packet((String) JSON, InetAddress SenderAddress, Integer SenderPort)  

## Packet.GetHeader()  
returns the packet header  
| Returns      |
| ------------ |
| PacketHeader |

## Packet.GetBody()  
returns the JSONOBject created from the body  
| Returns    |
| ---------- |
| JSONObject |

## Packet.GetBodyRaw()  
returns the raw body string  
| Returns     |
| ----------- |
| JSON String |

## Packet.GetPacketRaw()  
returns the raw packet string  
| Returns     |
| ----------- |
| JSON String |

## Packet.GetSenderAddress()  
returns the InetAddress from the socket the packet has been received with.  
| Returns     |
| ----------- |
| InetAddress |

## Packet.GetSenderPort()  
returns the Port from the socket the packet has been received with.  
| Returns |
| ------- |
| Integer |