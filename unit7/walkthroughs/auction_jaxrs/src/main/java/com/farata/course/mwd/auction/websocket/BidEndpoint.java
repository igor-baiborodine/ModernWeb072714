package com.farata.course.mwd.auction.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO: Pick one of
@ServerEndpoint("/bid")
public class BidEndpoint {

    public transient List<Session> participantList = new ArrayList<>();

    @OnMessage
    public void onMessage(String textMessage, Session mySession) {
        try {
            mySession.getBasicRemote().sendText(
                "[Server speaking]: Got your message " + textMessage + "Sending it back to you");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.err.println("Closing: " + closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.err.println("Error: " + t.getLocalizedMessage());
    }

}
