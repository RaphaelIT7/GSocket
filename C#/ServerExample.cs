using GSocket;

Socket socket = new GSocket.Socket();
socket.AddPacket("Example", new ServerExample());
socket.Listen();

public class ServerExample : PacketBase
{
    public override void Receive(Packet Packet)
    {
        Console.WriteLine(Packet.Body);
    }
}