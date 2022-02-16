package co.edu.escuelaing.arep.impl;

import java.io.*;
import java.net.Socket;

public class player {

    private player(){}

    private static class helper{
        private static final player INSTANCE = new player();
    }

    public void convertSring(String url, Socket clientSocket) throws IOException {
       consumer c = new consumer() {

        };
        String type = getType(url);

        if ("css".equals(type) || "js".equals(type) || "html".equals(type)) {
            PrintWriter out;
            String pagina = "HTTP/1.1 200 OK\r\n";
            pagina += "Content-Type: text/" + type + "\n";
            BufferedReader br = c.read(url);
            if (br == null) {
                pagina = "Empty";
            } else {
                pagina += c.toHtml(br);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(pagina);
            out.close();

        } else if ("jpg".equals(type) || "png".equals(type) || "jpeg".equals(type)) {

            BufferedReader bytes = c.read(url);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/" + type + "\r\n");
            binaryOut.writeBytes(url);
            binaryOut.close();

        } else {
            PrintWriter out;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String pagina = "NotFound";
            out.println(pagina);
            out.close();
        }
    }

    public String getType(String url) {
        String type = null;
        if (url.contains("css")) {
            type = "css";
        } else if (url.contains("js")) {
            type = "js";
        } else if (url.contains("html")) {
            type = "html";
        } else if (url.contains("jpg")) {
            type = "jpg";
        } else if (url.contains("png")) {
            type = "png";
        } else if (url.contains("jpeg")) {
            type = "jpeg";
        }
        return type;
    }
}


