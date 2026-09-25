package checkoutengine.http;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import checkoutengine.Checkout;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        Checkout checkout = new Checkout();
        server.createContext("/checkout", new CheckoutHandler(checkout));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }
}
