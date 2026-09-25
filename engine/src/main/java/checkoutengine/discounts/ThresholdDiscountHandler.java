package checkoutengine.discounts;

import checkoutengine.domain.Cart;

public class ThresholdDiscountHandler extends DiscountHandler {
    
    public double computeDiscount(Cart cart, int fidelityPoints) {
        double subtotal = cart.subtotal();
        return subtotal > 50 ? subtotal * 0.10 : 0.0;
    }
    
    public String name() { return "50€ Threshold Discount"; }

}

