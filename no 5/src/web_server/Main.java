package web_server;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
    private static final int PORT = 8080;
    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new MyHandler());
        server.createContext("/shutdown", new ShutdownHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Server running."; // Tulisan yang ditampilkan di halaman
            t.getResponseHeaders().add("Connection", "keep-alive"); // Buka koneksi dengan status "keep-alive"
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.flush();
        }
    }

    static class ShutdownHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Shutting down server...";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            server.stop(0);
            System.out.println("Server stopped");
        }
    }
}