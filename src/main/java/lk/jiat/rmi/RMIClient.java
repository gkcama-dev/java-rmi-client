package lk.jiat.rmi;

import lk.jiat.rmi.client.Message;
import lk.jiat.rmi.model.Data;

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

            System.out.println(msg);

            Data data = message.getData();

            System.out.println(data.getId());
            System.out.println(data.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
