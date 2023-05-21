/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiyouchat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kcfke
 */
public class ServerRMIYouChat extends UnicastRemoteObject implements ServerInterface {

    public static final String MESSAGE = "Welcome to this chat";
    private Map<String, User> users; // This will store the information of the connected users

    public ServerRMIYouChat() throws RemoteException {
        super(0); //this handles the exceptions 
        this.users = new HashMap<>();
    }

    public String getMessage(String username) {
        User user = users.get(username);
        if (user == null) {
            user = new User(username);
            users.put(username, user);
        }
        return MESSAGE;
    }

    public List<String> getMessageHistory(String username) {
        User user = users.get(username);
        if (user == null) {
            user = new User(username);
            users.put(username, user);
        }
        return user.getMessageHistory();
    }

    public void sendMessage(String sender, String recipient, String message) {
        User senderUser = users.get(sender);
        User recipientUser = users.get(recipient);

        if (senderUser != null && recipientUser != null) {
            String formattedMessage = "[" + sender + "]: " + message;
            senderUser.addMessageToHistory(formattedMessage);
            recipientUser.addMessageToHistory(formattedMessage);
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        System.out.println("Server starting...");
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registry created.");
        } catch (RemoteException e) {
            System.out.println("Registry already exists");
        }
        ServerRMIYouChat server = new ServerRMIYouChat();
        Naming.rebind("ServerRMIYouChat", server);
        System.out.println("Registry Bound");

    }
}
