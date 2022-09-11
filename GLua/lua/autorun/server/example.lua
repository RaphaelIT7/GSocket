require "gsocket"

GSocket.Connect()
GSocket.SendPacket("Example", {a = "HI"})