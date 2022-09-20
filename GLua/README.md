# Lua GSocket API  
This Lua implementation isn't able to receive packages!  

### Public:  
	GSocket.Connect((String) IP, (Integer) Port)  
	GSocket.SendPacket((String) Packet, (Table) Body)  
	
	### (internal)  
	GSocket.CreateHeader((String)Type, (JSONString) Body)  
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
```
 {
  ["Type"] = "Example", -- Packet Type
  ["Length"] = 10 -- Body length
 }
```

## (UDP Socket) GSocket.Socket  
## (String) GSocket.IP  
## (Integer) GSocket.Port  