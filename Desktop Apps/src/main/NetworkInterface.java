package main;

import java.net.InetAddress;

public interface NetworkInterface {
	
	void online(String name, InetAddress address);
	
	void process(String message);
	
}
