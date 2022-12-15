package edu.ship.engr.peertopeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.ship.engr.GUI.GUI;
import edu.ship.engr.communication.ConnectionManager;
import edu.ship.engr.communication.MessageAccumulator;

public class Runner {
    public static MessageAccumulator messageAccumulator = new MessageAccumulator();

    public static GUI gui;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("RUNNING");
        Socket socket;
        if (isHost(args)) {
            try (ServerSocket serverSocket = new ServerSocket(42420, 10)) {
                socket = serverSocket.accept();
            }
        } else {
            socket = new Socket("localhost", 42420);
        }

        new ConnectionManager(socket, messageAccumulator);
        gui = new GUI(isHost(args));

    }

    private static boolean isHost(String[] args) {
        return args.length >= 1 && args[0].equals("host");
    }


}

