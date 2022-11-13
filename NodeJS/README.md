# NodeJS  
| Function                          | Internally Used |
| --------------------------------- | --------------- |
| GSocket.Connect(IP, Port)         | No              | 
| GSocket.Send(Name, json)          | No              |
| GSocket.Listen()                  | No              |
| GSocket.Receive()                 | No              | 
| GSocket.AddPacket(Name, Function) | No              |
| GSocket.isJsonString(str)         | Yes             |

| Setting         | Type       | Default     |
| --------------- | ---------- | ----------- |
| GSocket.IP      | String     | "localhost" |
| GSocket.Port    | Integer    | 5000        |
| GSocket.Client  | UDP Socket | undefined   |
| GSocket.Packets | Object     | Empty       |

## GSocket.isJsonString() (internally used)  
checks and returns a bool if a given string is a json string. Used By:  
- GSocket.Listen()  
- GSocket.Send()  
- GSocket.Receive()  

| Returns |
| ------- |
| bool    |

## GSocket.Connect(IP, Port)
creates a udp socket which is accessable with GSocket.Client

## GSocket.Listen()  
opens a UDP Server on the specified IP and Port.  

## GSocket.Send(Type, JSON)  
creates a Packet and sends it to the specified IP and Port  

## GSocket.Receive(Socket)  
waits until a valid packet has been received, and the referenced packet has been executed  

## GSocket.AddPacket(Name, Function)  
adds a Packet to the Packets list.
The function will be executed if a packet has been received with the Type(reference)