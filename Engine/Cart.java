package Engine;

import Engine.CartLine;
import Engine.Product;

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
        return total;
    }
    
    // method that automatically calls toString method that gives our object a textual representation must overide later
    public String checkout()
    {
        return this.toString();
    }
}
