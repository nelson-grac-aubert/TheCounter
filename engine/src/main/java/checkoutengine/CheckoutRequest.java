package checkoutengine;

import java.util.List;

public record CheckoutRequest(List<CartLine> cart, int fidelityPoints) {
}

