package engine;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;


public class Cart {
    // declare lines as a list of CartLine init as a dynamic array
    private final List<CartLine> lines = new ArrayList<>();
    //method that get a product and its quantity as parameters and then create a new object CartLine using those param and add them to the lines list
    public void addLine (Product product, int quantity)
    {
        lines.add(new CartLine(product, quantity));
    }

    public double freeDrinksDiscount() {
        // one entry per drink unit
        List<Double> drinkPrices = new ArrayList<>();
        for (CartLine line : lines) {
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

    //method that simply sums up the cart total by iterating through each line and adding the result of cartLineTotal
    public double cartTotal()
    {
        double total = 0.0;
        for (CartLine line : lines)
        
        {
            total += line.cartLineTotal();
        }

        // quick, dirty v3
        total -= freeDrinksDiscount();

        // quick, dirty v2
        if (total >= 50) { 
            return (total * 0.9);
        }

        return total;
    }

    public List<CartLine> getLines() {
        return lines;
    }

}
