package checkoutengine.discounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import checkoutengine.domain.Cart;
import checkoutengine.domain.CartLine;
import checkoutengine.domain.Product.ProductCategory;

public class FreeDrinksDiscountHandler extends DiscountHandler {

    public double computeDiscount(Cart cart, int fidelityPoints) {
        // one entry per drink unit
        List<Double> drinkPrices = new ArrayList<>();
        for (CartLine line : cart.getLines()) {
            if (line.product().category() == ProductCategory.DRINKS) {
                for (int i = 0; i < line.quantity(); i++) {
                    drinkPrices.add(line.product().unitPrice());
                }
            }
        }

        // cheapest first
        Collections.sort(drinkPrices);

        // one free drink every 3 drinks
        int freeDrinksCount = drinkPrices.size() / 3;
        double freeDrinksTotal = 0.0;
        for (int i = 0; i < freeDrinksCount; i++) {
            freeDrinksTotal += drinkPrices.get(i);
        }

        return freeDrinksTotal;
    }

    public String name() { return "Free Drink(s)"; }
}
