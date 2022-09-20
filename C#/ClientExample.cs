using GSocket;

Socket socket = new GSocket.Socket();
socket.Connect();
socket.AddPacket("Example", new ClientExample());
socket.Receive();

public class ClientExample : PacketBase
{
    public override void Receive(Packet Packet) {
        Console.WriteLine(Packet.Body);
    }
}