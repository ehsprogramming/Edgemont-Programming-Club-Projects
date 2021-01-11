package packets.networking;

@FunctionalInterface
public interface MessageHandler {

	public void receiveMessage(String message, Person p);
	
}
