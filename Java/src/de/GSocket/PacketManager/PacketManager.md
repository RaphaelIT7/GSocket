# PacketManager  
| Function                                                       | Internally Used |
| -------------------------------------------------------------- | --------------- | 
| PacketManager.Connect(InetAddress IP, Integer Port)            | No              |
| PacketManager.SendPacket(String Type, JSONObject JSON)         | No              |
| PacketManager.isPacket(String JSON)                            | No              |
| PacketManager.Initialite()                                     | Yes             |
| PacketManager.AddPacket(PacketBase Packet)                     | Yes             |
| PacketManager.HandlePacket(String JSON, DatagramPacket income) | Yes             |

## PacketManager.Initialite() (internally used)  
called by the GSocket when it Initializes  
Searches for Packets and Loads them  

## PacketManager.AddPacket(PacketBase Packet) (internally used)  
used by the PacketManager.Initialize Method  
adds the Packet to the Packets list  

## PacketManager.HandlePacket(String JSON, DatagramPacket income) (internally used)  
called by the GSocket.Initialize when a String has been received  
converts the JSON String to a Packet and checks if any data has been lost before it calls the referenced Packet  

## PacketManager.Connect(InetAddress IP, Integer Port)  
connects to a UDP DatagramServer with the Specified IP and Port  

it will use Port 5000 if none is provided!  
it will throw an error if no IP is provided!  

## PacketManager.SendPacket(String Type, JSONObject JSON)  
sends a Packet to the connected UDP Socket  

it will throw an error if you didn't connect to a UDP Socket!  

## PacketManager.isPacket(String JSON)  
checks if a String is a JSON String  