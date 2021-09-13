package no.kristiania.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        String response = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html; charset-utf-8" +
                "Content-length: 10\r\n" +
                "\r\n" + "Hello World";
        socket.getOutputStream().write(response.getBytes());
    }
}
