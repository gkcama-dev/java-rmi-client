package lk.jiat.rmi;

import lk.jiat.rmi.client.Message;
import lk.jiat.rmi.client.UserService;
import lk.jiat.rmi.model.Data;
import lk.jiat.rmi.model.User;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 6666);
            String[] list = registry.list();
            for (String s : list) {
                System.out.println(s);
            }

            Message message = (Message) registry.lookup("message_service");
            String msg = message.hello();

            System.out.println("................");
            System.out.println(msg);
            System.out.println("................");
            System.out.println("................");

            Data data = message.getData();

            System.out.println(data.getId());
            System.out.println(data.getName());
            System.out.println("................");
            System.out.println("................");

            UserService userService = (UserService) registry.lookup("user_service");
            userService.addUser(1,new User(1,"Jhon","Colombo","jhon@gmail.com"));
            userService.addUser(2,new User(2,"Kasun","Colombo","kasun@gmail.com"));
            userService.addUser(3,new User(3,"Snow","Colombo","snow@gmail.com"));

            userService.getAllUsers().forEach(user -> {
                System.out.println(user.getId() + " " + user.getName()+" "+ user.getAddress() + " " + user.getEmail());
                System.out.println(".................................");
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
