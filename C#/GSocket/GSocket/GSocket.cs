using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Text.Json;

Console.WriteLine("GSocket Loaded");

namespace GSocket
{
    public class PacketHeader
    {
        public short Length { get; set; }
        public String? Type { get; set; }
    }

    public class Packet
    {
        public PacketHeader? Header { get; set; }
        public String? Body { get; set; }
    }

    public class PacketBase
    {
        public virtual void Receive(Packet Packet) { }
    }

    public class Socket
    {
        private Dictionary<string, PacketBase> Packets = new Dictionary<string, PacketBase>();
        private UdpClient Udp;
        private String IP = "127.0.0.1";
        private int Port = 5000;
        public void Connect() { Connect(IP, Port); }
        public void Connect(String IP) { Connect(IP, Port); }
        public void Connect(String IP, int Port)
        {
            this.IP = IP;
            this.Port = Port + new Random().Next(10, 1000);
            Udp = new UdpClient(this.Port);
            Udp.Connect(IP, Port);
        }

        public void Listen()
        {
            Udp = new UdpClient(this.Port);
            IPEndPoint groupEP = new IPEndPoint(IPAddress.Any, this.Port);

            try
            {
                while (true)
                {
                    Console.WriteLine("Waiting for broadcast");
                    byte[] bytes = Udp.Receive(ref groupEP);
                    string json = Encoding.UTF8.GetString(bytes, 0, bytes.Length);
                    Packet? Packet = JsonSerializer.Deserialize<Packet>(json);
                    if (Packet == null || Packet.Header == null || Packet.Header.Type == null || Packet.Header.Length != Packet.Body.Length)
                    {
                        Console.Error.WriteLine("Packet data has been lost!");
                        continue;
                    }

                    if (Packets.ContainsKey(Packet.Header.Type))
                    {
                        Packets[Packet.Header.Type].Receive(Packet);
                    }
                }
            }
            catch (SocketException e)
            {
                Console.WriteLine(e);
            }
            finally
            {
                Udp.Close();
            }
        }

        public void Send(String Type, String JSON)
        {
            String Packet = JsonSerializer.Serialize(new Packet
            {
                Header = new PacketHeader
                {
                    Type = Type,
                    Length = (short)JSON.Length
                },
                Body = JSON
            });

            Byte[] PacketBytes = Encoding.UTF8.GetBytes(Packet);

            Udp.Send(PacketBytes, PacketBytes.Length);
        }

        public void Receive()
        {
            Udp.Close();
            Udp.Dispose();
            Udp = new UdpClient(this.Port);

            IPEndPoint RemoteIPEndPoint = new IPEndPoint(IPAddress.Any, this.Port);
            Console.WriteLine(this.Port);
            try
            {
                Byte[] receiveBytes = Udp.Receive(ref RemoteIPEndPoint);
                string returnData = Encoding.UTF8.GetString(receiveBytes, 0, receiveBytes.Length);
                Packet? Packet = JsonSerializer.Deserialize<Packet>(returnData);
                if (Packet == null || Packet.Header == null || Packet.Header.Type == null || Packet.Header.Length != Packet.Body.Length)
                {
                    Console.Error.WriteLine("Packet data has been lost!");
                    return;
                }

                if (Packets.ContainsKey(Packet.Header.Type))
                {
                    Packets[Packet.Header.Type].Receive(Packet);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
        }

        public void AddPacket(String Packet, PacketBase PacketBase)
        {
            Packets.Add(Packet, PacketBase);
        }
    }
}
