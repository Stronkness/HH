package labb1;

/**
 * A message left by the caller.
 */
public class Message {
	
	/**
	 * Construct a Message object.
	 * 
	 * @param messageText
	 *            the message text
	 */
	public Message(String messageText) {
		text = messageText;
	}

	/**
	 * Get the message text.
	 * 
	 * @return message text
	 */
	public String toString() {
		return text;
	}

	private String text;
}