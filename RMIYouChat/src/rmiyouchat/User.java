/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiyouchat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kcfke
 */
public class User {
        private String username;
    private List<String> messageHistory;

    public User(String username) {
        this.username = username;
        this.messageHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<String> getMessageHistory() {
        return messageHistory;
    }

    public void addMessageToHistory(String message) {
        messageHistory.add(message);
    }
}
