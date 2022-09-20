package de.GSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.common.reflect.ClassPath;

import de.GSocket.PacketManager.PacketManager;

public class GSocket {
	
	public static String IP = "localhost";
	public static Integer Port = 5000;
	private static boolean Secure = true;
	public static PacketManager PacketManager;
	public static Logger Log;
	public static void main(String[] args) {
		Log = Logger.getLogger("GSocket");
		if(args.length > 0 && args[0] != null) {
			IP = args[0];
		} else { Secure = false; }
		
		if(args.length > 1 && args[1] != null) {
			Port = Integer.valueOf(args[1]);
		}
		
		PacketManager = new PacketManager();
		Initialize();
	}
	
	@SuppressWarnings("rawtypes")
	// returns a list of classes in a specified package
	public static Set<Class> LoadPackages(String Package) {
		try {
			return ClassPath.from(ClassLoader.getSystemClassLoader())
				      .getAllClasses()
				      .stream()
				      .filter(clazz -> clazz.getPackageName()
				        .equalsIgnoreCase(Package))
				      .map(clazz -> clazz.load())
				      .collect(Collectors.toSet());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static DatagramSocket server;
	public static void Initialize() {
		PacketManager.Initialize();
	
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server = new DatagramSocket(Port);
			        byte[] incomingBytes = new byte[8192];
			        Log.info("Server is listening on ip " + server.getLocalSocketAddress());
			        Log.info("Server is listening on port " + Port);
			        if(Secure) {
			        	Log.info("Server accepts packages from IP " + IP);
			        } else {
			        	Log.warning("Server accepts all packages!");
			        }
					
					while(true) {
						DatagramPacket incomingPacket = new DatagramPacket(incomingBytes, incomingBytes.length);
					    server.receive(incomingPacket);
					    String received = new String(incomingBytes,0 , incomingPacket.getLength());
					    if(Secure) {
					    	if(incomingPacket.getAddress().getHostAddress().equals(IP)) {
					    		PacketManager.HandlePacket(received, incomingPacket);
					    	}
					    } else {
					    	PacketManager.HandlePacket(received, incomingPacket);
					    }
					}
				} catch(Exception e) {
					e.printStackTrace();
				}; 
			}
		}).start();
	}
}
