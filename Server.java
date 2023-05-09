/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

/**
 *
 * @author imman
 */
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        String root = "C:/Users/imman/OneDrive/Documents";

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler(root));
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server listening on port " + port);
    }

    static class RootHandler implements HttpHandler {
        private final String root;

        public RootHandler(String root) {
            this.root = root;
        }

        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            File file = new File(root, path.substring(1)); // remove leading "/"
            if (!file.exists()) {
                exchange.sendResponseHeaders(404, -1);
                exchange.close();
                return;
            }

            if (file.isDirectory()) {
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, 0);
                OutputStream responseBody = exchange.getResponseBody();
                responseBody.write("<html><body>".getBytes());
                for (File f : file.listFiles()) {
                    String href = f.getName();
                    if (f.isDirectory()) {
                        href += "/";
                    }
                    responseBody.write(("<a href=\"" + href + "\">" + f.getName() + "</a><br>").getBytes());
                }
                responseBody.write("</body></html>".getBytes());
                responseBody.close();
            } else {
                String mimeType = Files.probeContentType(file.toPath());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                exchange.getResponseHeaders().set("Content-Type", mimeType);
                exchange.sendResponseHeaders(200, file.length());
                OutputStream responseBody = exchange.getResponseBody();
                Files.copy(file.toPath(), responseBody);
                responseBody.close();
            }
        }
    }
}