use std::net::UdpSocket;

trait GSocket {
	fn Connect(IP, Port) -> RetType {
		let mut address: String = IP.to_owned();
		address.push_str(":");
		address.push_str(Port);
		let udp = UdpSocket::bind(IP + "");
	}
}