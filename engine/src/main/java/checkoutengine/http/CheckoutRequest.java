package checkoutengine.http;

import java.util.List;

import checkoutengine.domain.CartLine;

public record CheckoutRequest(List<CartLine> cart, int fidelityPoints) {
}

