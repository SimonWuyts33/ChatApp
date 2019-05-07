package controller;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/Blog")
public class BlogEndpoint {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session){
        //System.out.println("onOpen");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        //System.out.println("onMessage: " + message);
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace(); // betere optie?
            }
        });
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }

}
