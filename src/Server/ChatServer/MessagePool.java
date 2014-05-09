package Server.ChatServer;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * MessagePool.java
 * MOBA Turn-based Online Game
 * Assignment 1, COSC2440 Software Architecture: Design and Implementation
 * RMIT International University Vietnam
 * -
 * Copyright 2013 Vuong Do Thanh Huy      (s3342135)
 * Nguyen Quoc Trong Nghia (s3343711)
 * Kieu Hoang Anh          (s3275058)
 * -
 * Refer to the NOTICE.txt file in the root of the source tree for
 * acknowledgements of third party works used in this software.
 * -
 * Date created: 13/03/2013
 * Date last modified: 05/05/2013
 */

public class MessagePool extends Observable {

    private String chatContent;
    private static Map<String, MessagePool> arr = new HashMap<String, MessagePool>();
    private String team;
    public boolean isNew;

    public MessagePool(String team) {
        this.team = team;
        arr.put(team, this);
    }

    public String getTeam() {
        return team;
    }

    public synchronized void clearChatPool() {
        chatContent = "";
    }

    public static MessagePool getMsgPool(String team) {
        return arr.get(team);
    }

    public String getChatContent() {
        if (chatContent == null) {
            return "";
        }
        return chatContent;
    }

    public synchronized void setChatContent(String chatContent) {
        this.chatContent = chatContent;
        setChanged();
        notifyObservers(team);
    }

}
