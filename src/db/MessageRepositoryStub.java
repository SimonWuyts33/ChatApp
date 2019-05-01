package db;

import domain.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class MessageRepositoryStub implements MessageRepository {
	private final Map<String, List<Message>> messageMap;

	public MessageRepositoryStub() {
		messageMap = new HashMap<>();
	}

	@Override
	public void add(String receiverUsername, Message message) {
        List<Message> messageList = messageMap.get(receiverUsername);

        if( isNull(messageList)){
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        messageMap.put(receiverUsername, messageList);
    }

	@Override
	public List<Message> popForReceiver(String username) {
		List<Message> messages =  messageMap.get(username);
		if (isNull(messages)){
			return new ArrayList<>();
		}
		else{
			messageMap.remove(username);
			return messages;
		}
	}
}
