package db;

import domain.Message;

import java.util.List;

public interface MessageRepository {

	public abstract void add(String receiverUsername, Message message);

	public abstract List<Message> popForReceiver(String username);
}