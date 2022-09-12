require "socket"

GSocket = {
	["IP"] = "localhost",
	["Port"] = 5000
}

function GSocket.Connect(IP, Port)
	IP = IP or GSocket.IP
	Port = Port or GSocket.Port

	local udp = socket.udp()
	udp:settimeout(0)
	udp:setpeername(IP, Port)
	if !udp then
		error("failed to create a GSocket with targeted host")
	end
	GSocket.IP = IP
	GSocket.Port = Port
	GSocket.Socket = udp
end

function GSocket.CreateHeader(Type, Body)
	return {
		["Type"] = Type,
		["Length"] = string.len(Body)
	}
end

function GSocket.SendPacket(Packet, Body)
	Body = Body or {}
	Body = util.TableToJSON(Body)
	if !Packet then
		error("no packet name was provided")
	end

	local packet = {
		["Header"] = GSocket.CreateHeader(Packet, Body),
		["Body"] = Body
	}

	if !GSocket.Socket then
		error("attempted to send a packet using a nil GSocket")
	end
	GSocket.Socket:send(util.TableToJSON(packet))
end