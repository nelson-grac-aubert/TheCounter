package checkoutengine.discounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import checkoutengine.domain.Cart;
import checkoutengine.domain.CartLine;
import checkoutengine.domain.Product.ProductCategory;

public class V6DiscountHandler extends DiscountHandler {
    public double computeDiscount(Cart cart, int fidelityPoints) {
        
        int drinkCount = 0;
        int foodCount = 0; 
        // one entry per drink or food unit
        List<Double> foodPrices = new ArrayList<>();
        for (CartLine line : cart.getLines()) {
            if (line.product().category() == ProductCategory.FOOD) {
                for (int i = 0; i < line.quantity(); i++) {
                    foodPrices.add(line.product().unitPrice());
                    if (line.product().category() == ProductCategory.FOOD) { 
                        foodCount += 1; 
                    }
                }
            }
            if (line.product().category() == ProductCategory.DRINKS) {
                drinkCount += line.quantity();
            }
        }

        // cheapest first
        Collections.sort(foodPrices);

        double totalDiscount = 0.0;

        if (drinkCount >= 1 && foodCount >= 2) {
            totalDiscount = 0.45 * foodPrices.get(0);
        }
        return totalDiscount;
    }

    public String name() { return "2 foods 1 drink discount"; }
}
