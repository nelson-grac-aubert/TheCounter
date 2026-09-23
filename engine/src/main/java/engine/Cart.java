package Engine.src.main.java.engine;

import java.util.ArrayList;
import java.util.List;

import Engine.src.main.java.engine.Product.ProductCategory;


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
            if (line.product().category() == Product.ProductCategory.DRINKS) { 
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

        //TTC price calculation for food 5.5
    public double foodTotal()
    {
        double total = 0.0;
        for(CartLine line : lines)
        {
            //for each line of cartline check if each product of said line is equal to enum cat food 
            if (line.product().category() == Product.ProductCategory.FOOD || line.product().category() ==  (Product.ProductCategory.DRINKS))
            {
                total += line.cartLineTotal();
            }
        }
        return total;
    }

        //TTC price calculation for everything else 20
    public double otherTotal()
    {
        double total = 0.0;
        
        for (CartLine line : lines)
             //for each line of cartline check if each product of said line is not equal to enum cat food 
        {
            if (line.product().category() == ProductCategory.OTHER) {
                total += line.cartLineTotal();
            }
        }
        return total;

    }





    //method that simply sums up the cart total by iterating through each line and adding the result of cartLineTotal
    public double cartTotal()
    {
        double food = foodTotal(); 
        double other = otherTotal();
        double total = food + other;
        // for (CartLine line : lines)
        
        // {
        //     total += line.cartLineTotal();
        // }

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
