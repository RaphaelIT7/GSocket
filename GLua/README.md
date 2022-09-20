# Lua GSocket API
This Lua implementation isn't able to receive packages!

<br>
<br>

## GSocket.Connect((String) IP, (Integer) Port)
if no IP is provided it will use localhost!    
if no Port is provided it will use the default 5000 Port!   

## GSocket.SendPacket((String) Packet, (Table) Body)
if no Packet is provided it throw an Error!  
if no Body is provided it will send an Empty Packet!

<br>
<br>

# Internal API

## GSocket.CreateHeader((String)Type, (JSONString) Body)
if no Type is provided it will return an Header with no Destination!  
if no Body is provided the Java PacketManager will throw an warning and won't Handle the Packet properly!

it returns a table like this:
```
 {
  ["Type"] = "Example", -- Packet Type
  ["Length"] = 10 -- Body length
 }
```

## (udp Socket) GSocket.Socket
## (String) GSocket.IP
## (Integer) GSocket.Port
