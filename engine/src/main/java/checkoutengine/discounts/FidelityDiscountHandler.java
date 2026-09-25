package checkoutengine.discounts;

import checkoutengine.domain.Cart;

public class FidelityDiscountHandler extends DiscountHandler {
    public double computeDiscount(Cart cart, int fidelityPoints) {
        double subtotal = cart.subtotal();
        return fidelityPoints >= 100 ? Math.min(5.0, subtotal) : 0.0;
    }
    public String name() { return "Fidelity Discount"; }
}

