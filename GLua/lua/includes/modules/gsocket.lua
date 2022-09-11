require "socket"

GSocket = {}
local udp
function GSocket.Connect(IP, Port)
	IP = IP or "localhost"
	Port = Port or 5000

	udp = socket.udp()
	udp:settimeout(0)
	udp:setpeername(IP, Port)
	if !udp then
		error("failed to create a GSocket with targeted host")
	end
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
	local packet = {
		["Header"] = GSocket.CreateHeader(Packet, Body),
		["Body"] = Body
	}

	if !udp then
		error("attempted to send a packet using a nil GSocket")
	end
	udp:send(util.TableToJSON(packet))
end