package db;

import domain.Message;

import java.util.List;

interface MessageRepository {

	void add(String receiverUsername, Message message);

	List<Message> popForReceiver(String username);
}