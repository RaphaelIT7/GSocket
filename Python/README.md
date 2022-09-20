# Python  
| Function                          | Internally Used |
| --------------------------------- | --------------- |
| GSocket.Listen()                  | No              | 
| GSocket.Send()                    | No              |
| GSocket.Receive()                 | No              | 
| GSocket.AddPacket(Name, Function) | No              |
| GSocket.Socket()                  | Yes             |

| Setting      | Type    | Default     |
| ------------ | ------- | ----------- |
| GSocket.IP   | String  | "localhost" |
| GSocket.Port | Integer | 5000        |

## GSocket.Socket() (internally used)  
returns a datagram socket which is used by:  
- GSocket.Listen()  
- GSocket.Send()  
- GSocket.Receive()  

| Returns |
| ------- |
| socket  |

## GSocket.Listen()  
opens a UDP Server on the specified IP and Port.  

## GSocket.Send(Type, JSON)  
creates a Packet and sends it to the specified IP and Port  
returns a Socket which can be used with GSocket.Receive(Socket)  
| Returns |
| ------- |
| socket  |

## GSocket.Receive(Socket)  
waits until a valid packet has been received, and the referenced packet has been executed  

## GSocket.AddPacket(Name, Function)  
adds a Packet to the Packets list.
The function will be executed if a packet has been received with the Type(reference)