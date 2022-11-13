import socket
import json
import random

Packets = json.loads(json.dumps({}))

global IP
global Port
IP = "localhost"
Port = 5000
def Socket():
	return socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

def Listen():
	s = Socket()
	s.bind((IP, Port))
	while True:
		data, address = s.recvfrom(4096)
		try:
			data = json.loads(data.decode('utf-8'))
			Header = json.loads(data["Header"])
			if not data["Body"] or not Header:
				continue

			if len(data["Body"]) != Header["Length"]:
				print("Packet data has been lost!")

			Body = json.loads(data["Body"])

			if Packets[Header["Type"]] != None:
				Packets[Header["Type"]](Body)
		except ValueError as e:
			print("Invalid packet has beed received!")

	
def Send(Type, JSON):
	try:
		Body = str(JSON)

		Header = {
			"Type" : Type,
			"Length" : len(Body)
		}

		packet = json.dumps({
			"Header" : Header,
			"Body" : Body
		})

		S = Socket()
		S.bind((IP, Port + random.randint(10, 1000)))
		S.sendto(packet.encode('utf-8'), (IP, Port))
		return S
	except ValueError as e:
		print("Invalid JSON String!")

def Receive(Socket):
	Empty = True
	while Empty:
		data, address = Socket.recvfrom(4096)
		try:
			data = json.loads(data.decode('utf-8'))
			Header = json.loads(data["Header"])
			if len(data["Body"]) != Header["Length"]:
				print("Packet data has been lost!")

			Body = json.loads(data["Body"])
			if Packets[Header["Type"]] != None:
				Packets[Header["Type"]](Body)
				Empty = False
		except ValueError as e:
			print("Invalid Packet has beed received!")


def AddPacket(Name, func):
	Packets[Name] = func