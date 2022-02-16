package co.edu.escuelaing.arep.impl;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class requests<T> implements Runnable{
    private final Socket socket;

    public requests(Socket s){
        socket=s;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            String recurso;
            while ((inputLine = in.readLine()) != null) {

                if (inputLine.contains("GET")) {
                    recurso = inputLine.split(" ")[1];
                    recurso = recurso.substring(1);
                    System.out.println(recurso);
                    player.convertSring (recurso, socket);
                }
                if (!in.ready()) {
                    break;
                }
                if (inputLine.contains("bye")) {
                    System.exit(1);
                }
            }
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(requests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
