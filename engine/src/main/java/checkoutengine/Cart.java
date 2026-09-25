package checkoutengine;

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

    // No fidelity balance stored here anymore (removed per audit SRP finding)
    // it now flows through Checkout.convert(cart, fidelityPoints) as a parameter
    public double subtotal() {
        double total = 0.0;
        for (CartLine line : lines) {
            total += line.cartLineTotal();
        }
        return total;
    }

    public List<CartLine> getLines() {
        return lines;
    }

}
