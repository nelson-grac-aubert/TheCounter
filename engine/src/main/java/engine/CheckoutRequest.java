package engine.src.main.java.engine;

import java.util.List;

public record CheckoutRequest(List<CartLine> cart, int fidelityPoints) {
}

