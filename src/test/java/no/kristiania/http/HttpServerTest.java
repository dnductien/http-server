package no.kristiania.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {
    @Test
    void shouldReturnSuccessfulErrorCode() throws IOException {
        HttpServer server = new HttpServer(10001);
        HttpClient client = new HttpClient("localhost", 10001, "/hello");
        assertEquals(200, client.getStatusCode());
    }
}