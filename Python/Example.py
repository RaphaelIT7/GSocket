import GSocket

GSocket.IP = "127.0.0.1"
GSocket.Port = 5000
def Example(Body):
	print(Body)

GSocket.AddPacket("Example", Example)
Socket = GSocket.Send("Example", {
	"Hello" : "World"
	})

GSocket.Receive(Socket)