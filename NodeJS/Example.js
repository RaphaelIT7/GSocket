var GSocket = require("./GSocket.js");
GSocket = new GSocket();
GSocket.Connect()
GSocket.Send("Example", {"Hello": "World"})