/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiyouchat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author kcfke
 */
public interface ServerInterface  extends Remote {
    String getMessage(String username) throws RemoteException;
    List<String> getMessageHistory(String username) throws RemoteException;
    void sendMessage(String sender, String recipient, String message) throws RemoteException; 

  
}
