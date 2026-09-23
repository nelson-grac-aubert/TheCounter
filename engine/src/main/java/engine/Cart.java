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
    //method that simply sums up the cart total by iterating through each line and adding the result of cartLineTotal
    public double cartTotal()
    {
        double total = 0.0;
        for (CartLine line : lines)
        
        {
            total += line.cartLineTotal();
        }

        // quick, dirty v2
        if (total >= 50) { 
            return (total * 0.9);
        }

        return total;
    }
    
    // method that automatically calls toString method that gives our object a textual representation must overide later
    // public String checkout()
    // {
    //     return this.toString();
    // }
    @Override 
    public String toString()
    {
        return "Product: " + lines + " Total: " + cartTotal() + " $";
    }
}
