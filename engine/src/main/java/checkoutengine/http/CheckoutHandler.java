package checkoutengine.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import checkoutengine.Checkout;
import checkoutengine.domain.Cart;
import checkoutengine.domain.CartLine;
import checkoutengine.domain.Ticket;

public class CheckoutHandler implements HttpHandler {
    private final Checkout checkout;
    private final Gson gson = new Gson();

    public CheckoutHandler(Checkout checkout) {
        this.checkout = checkout;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // AUDIT : reject anything that isn't a POST (avoids the NullPointerException/crash the audit flagged on GET)
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }

        // #AUDIT : try-with-resources : the stream is guaranteed closed when the block ends, even on exception
        String body;
        try (InputStream requestBody = exchange.getRequestBody()) {
            body = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
        }

        CheckoutRequest request = gson.fromJson(body, CheckoutRequest.class);
        Cart cart = new Cart();
        for (CartLine line : request.cart()) {
            cart.addLine(line.product(), line.quantity());
        }
        Ticket ticket = checkout.convert(cart, request.fidelityPoints());
        byte[] responseBytes = gson.toJson(ticket).getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(responseBytes);
        }
    }
}
