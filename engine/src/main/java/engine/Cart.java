package engine;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    // declare lines as a list of CartLine init as a dynamic array
    private final List<CartLine> lines = new ArrayList<>();
    //method that get a product and its quantity as parameters and then create a new object CartLine using those param and add them to the lines list
    public void addLine (Product product, int quantity)
    {
        lines.add(new CartLine(product, quantity));
    }

    public double cheapestDrinkPrice() { 
        int drinksCount = 0; 
        // highest double possible so first drink analysed is always cheaper
        double cheapestPrice = Double.MAX_VALUE;
        for (CartLine line : lines) { 
            if (line.product().category() == ProductCategory.DRINKS) { 
                drinksCount += line.quantity(); 

                if (line.product().unitPrice() <= cheapestPrice) {
                    cheapestPrice = line.product().unitPrice();
                }
            }
        }
        if (drinksCount >= 3) {
            return cheapestPrice;
        }
        return 0.0; 
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
        total -= cheapestDrinkPrice();

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
