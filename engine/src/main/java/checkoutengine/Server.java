package checkoutengine;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class Server {
    public static void main(String[] args) throws IOException {
        // backlog : how many connections can queue after the current one 
        // 0 = default
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        Gson gson = new Gson();
        Checkout checkout = new Checkout();
        // exchange = HttpExchange, object created on each entering request
        server.createContext("/checkout", exchange -> {
            // getRequestBody return an InputStream, brute bytes of the request body
            // String(xxxxxxxx UTF8) : convert bytes into a string with UTF8 standard
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            
            CheckoutRequest request = gson.fromJson(body, CheckoutRequest.class);


            // recreate a cart with the lines, as lines are just raw data, we need the methods of a cart 
            Cart cart = new Cart();
            for (CartLine line : request.cart()) {
                cart.addLine(line.product(), line.quantity());
            }

            Ticket ticket = checkout.convert(cart, request.fidelityPoints());
            // inverse of turning bytes into string
            byte[] responseBytes = gson.toJson(ticket).getBytes(StandardCharsets.UTF_8);
            // tell the client he will recieve a JSON
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            // Send the 200 success code and the headers
            exchange.sendResponseHeaders(200, responseBytes.length);
            // Send the body
            exchange.getResponseBody().write(responseBytes);
            // Close the response
            exchange.getResponseBody().close();


        });

        // Executor controls how requests are distributed on threads
        // null = default, 1 thread per request
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");

    }
}
