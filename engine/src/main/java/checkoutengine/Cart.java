package checkoutengine;

import java.util.ArrayList;
import java.util.List;

import checkoutengine.Product.ProductCategory;

public class Cart {
    // declare lines as a list of CartLine init as a dynamic array
    private final List<CartLine> lines = new ArrayList<>();
    //method that get a product and its quantity as parameters and then create a new object CartLine using those param and add them to the lines list
    public void addLine (Product product, int quantity)
    {
        lines.add(new CartLine(product, quantity));
    }

    // No fidelity balance stored here anymore (removed per audit SRP finding)
    // it now flows through Checkout.convert(cart, fidelityPoints) as a parameter
    public double subtotal() {
        double total = 0.0;
        for (CartLine line : lines) {
            total += line.cartLineTotal();
        }
        return total;
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

    public List<CartLine> getLines() {
        return lines;
    }

}
