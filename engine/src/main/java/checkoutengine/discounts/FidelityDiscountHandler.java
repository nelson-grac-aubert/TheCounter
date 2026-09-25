package checkoutengine.discounts;

import checkoutengine.Cart;

public class FidelityDiscountHandler extends DiscountHandler {
    public double computeDiscount(Cart cart) {
        double subtotal = cart.subtotal();
        return cart.getFidPoints() >= 100 ? Math.min(5.0, subtotal) : 0.0;
    }
    public String name() { return "Fidelity Discount"; }
}

