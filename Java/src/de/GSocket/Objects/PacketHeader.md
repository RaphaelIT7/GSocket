# PacketHeader  
| Function                 | Internally Used |
| ------------------------ | --------------- |
| PacketHeader.GetPacket() | No              |
| PacketHeader.GetType()   | No              |
| PacketHeader.GetLength() | Yes             |

## PacketHeader(Packet Packet, JSONObject Header)  

## PacketHeader.GetPacket()  
returns the packet the header belongs to  
| Returns |
| ------- |
| Packet  |

## PacketHeader.GetType()  
the type is used as a referance to a packet with a function at the destination  

returns the packet type  
| Returns |
| ------- |
| String  |

## PacketHeader.GetLength() (internally used)  
used to check if any data has been lost  
returns the body length  
| Returns |
| ------- |
| Integer |