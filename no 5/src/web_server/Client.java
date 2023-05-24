package web_server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String[] args) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080").openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection", "close");
        connection.getResponseCode();
        System.out.println("Server terminated");
//        System.exit(0);
    }
}
