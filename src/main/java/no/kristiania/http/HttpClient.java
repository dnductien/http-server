package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    private final int statusCode;
    private final Map<String, String> headerFields = new HashMap<>();
    private String messageBody;

    public HttpClient(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        String clientRequest =
                "GET " + requestTarget + " HTTP/1.1\r\n"
                        + "Host: " + host + "\r\n"
                        + "\r\n";

        socket.getOutputStream().write(clientRequest.getBytes());

        String statusLine = HttpMessage.readLine(socket);
        this.statusCode = Integer.parseInt(statusLine.split(" ")[1]);
        String headerLine;
        while (!(headerLine = HttpMessage.readLine(socket)).isBlank()) {
            int colonPos = headerLine.indexOf(':');
            String headerName = headerLine.substring(0, colonPos);
            String headerValue = headerLine.substring(colonPos+1).trim();
            headerFields.put(headerName, headerValue);
        }
        messageBody = HttpMessage.readBytes(socket, getContentLength());
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String fieldName) {
        return headerFields.get(fieldName);
    }

    public int getContentLength() {
        return Integer.parseInt(getHeader("Content-Length"));
    }

    public String getMessageBody() {
        return messageBody;
    }
}
