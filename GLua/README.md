# GLua  
This Lua implementation isn't able to receive packages!  

| Function                                              | Internally Used |
| ----------------------------------------------------- | --------------- |
| GSocket.Connect((String) IP, (Integer) Port)          | No              |
| GSocket.SendPacket((String) Packet, (Table) Body)     | No              |
| GSocket.CreateHeader((String)Type, (JSONString) Body) | Yes             |

| Setting        | Type    | Default     |
| -------------- | ------- | ----------- |
| GSocket.IP     | String  | "localhost" |
| GSocket.Port   | Integer | 5000        |
| GSocket.Socket | Socket  | nil         |
<br>
<br>

## GSocket.Connect((String) IP, (Integer) Port)  
if no IP is provided it will use localhost!  
if no Port is provided it will use the default 5000 Port!  

## GSocket.SendPacket((String) Packet, (Table) Body)  
if no Packet is provided it throw an Error!  
if no Body is provided it will send an Empty Packet!  

## GSocket.CreateHeader((String)Type, (JSONString) Body) (internally used)  
if no Type is provided it will return an Header with no Destination!  
if no Body is provided the Java PacketManager will throw an warning and won't Handle the Packet properly!  

it returns a table like this:
```lua
 {
  ["Type"] = "Example", -- Packet Type
  ["Length"] = 10 -- Body length
 }
```