package checkoutengine.discounts;

import checkoutengine.domain.Cart;

public class NoDiscount extends DiscountHandler {
    public double computeDiscount(Cart cart, int fidelityPoints) { return 0.0; }
    public String name() { return "No discount"; }
}
