/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiyouchat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kcfke
 */
public class ClientRMIYouChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost:1099/ServerRMIYouChat");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Send message");
            System.out.println("2. View message history");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter recipient's username: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter your message: ");
                    String message = scanner.nextLine();
                    server.sendMessage(username, recipient, message);
                    System.out.println("Message sent.");
                    break;
                case 2:
                    List<String> messageHistory = server.getMessageHistory(username);
                    System.out.println("Message history:");
                    for (String historyItem : messageHistory) {
                        System.out.println(historyItem);
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
