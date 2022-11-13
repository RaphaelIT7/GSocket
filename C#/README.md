# C#
## Class Socket:
| Function                       | Internally Used |
| ------------------------------ | --------------- |
| Connect(String IP, int Port)   | No              |
| Listen()                       | No              |
| Send(String Type, String JSON) | No              |
| Receive()                      | No              |
| AddPacket                      | No              |

## Socket.Connect(String IP, int Port)  
creates a UdpClient and connects it to the specified IP and Port  
if no IP is provided it will use "127.0.0.1"  
if no Port is provided it will use 5000  

## Socket.Listen()  
creates a UdpServer and Listens for Packets  

## Socket.Send(String Type, String JSON)  
sends an Packet to the connected UdpServer  
if the UdpClient is not connected it will throw an error!  

## Socket.Receive()  
allows the client to wait for a packet  

## Socket.AddPacket()  
adds an Packet and its reference name to the Packets Dictionary  
