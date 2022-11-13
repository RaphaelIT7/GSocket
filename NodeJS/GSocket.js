var udp = require("dgram")

class GSocket {
	constructor() {

	}

	isJsonString(str) {
	    try {
	    	console.log(str);
	        JSON.parse(str);
	    } catch (e) {
	        return false;
	    }
	    return true;
	}

	Connect(IP, Port) {
		this.Client = udp.createSocket("udp4");
		this.IP = IP || "localhost";
		this.Port = Port || 5000;
		this.Packets = {};
	}

	Send(Name, str) {
		console.log(typeof str);
		if(!(typeof str === 'object')) {
			if(this.isJsonString(str)) {
				str = JSON.parse(str);
			} else {
				throw new Error('Type is not a String!');
			}
		} else {
			str = JSON.stringify(str);
		}

		var packet = '{"Header": {"Type": "' + Name + '", "Length": ' + str.length + '}, "Body": ' + str + '}';
		this.Client.send(Buffer.from(packet), this.Port, this.IP, function(error) {
			if(error) {
				this.Client.close();
			}
		});
	}

	Listen() {
		this.Client.on('message', function(msg, info) {
			msg = msg.tostring()
			if(this.isJsonString(msg)) {
				if(msg["Header"] != null || msg["Header"]["Length"] != null || msg["Header"]["Type"] != null || msg["Body"] != null) {
					if(this.Packets[msg["Header"]["Type"]] != null) {
						this.Packets[msg["Header"]["Type"]](msg);
					}
				}
			}
		});

		this.Client.on('listening', function() {
			var address = this.Client.address();
			var port = address.port;
			var family = address.family;
			var ipaddr = address.address;
			console.log('Server is listening at port' + port);
			console.log('Server ip :' + ipaddr);
			console.log('Server is IP4/IP6 : ' + family);
		});

		this.Client.on('close', function() {
			console.Log("Server closed!");
		});
		this.Client.bind(this.Port);
	}

	Receive() {
		this.Client.on('message', function(msg, info){
			msg = msg.tostring()
			if(this.isJsonString(msg)) {
				if(msg["Header"] != null || msg["Header"]["Length"] != null || msg["Header"]["Type"] != null || msg["Body"] != null) {
					if(this.Packets[msg["Header"]["Type"]] != null) {
						this.Packets[msg["Header"]["Type"]](msg);
					}
				}
			}		
		});
	}

	AddPacket(Name, Function) {
		this.Packets[Name] = Function;
	}
}
module.exports = GSocket;