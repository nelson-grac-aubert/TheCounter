package engine;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    
    // Convert a cart with CartLines into a Ticket with Ticketlines, with 
    // all the data we need : total, discounts... 
    public Ticket convert(Cart cart) {
        List<TicketLine> lines = new ArrayList<>();

        for (CartLine line : cart.getLines()) {
            TicketLine ticketLine = new TicketLine(line.product(), line.quantity(), line.cartLineTotal());

            lines.add(ticketLine);
        }

        return new Ticket(lines, cart.cartTotal());
    }
    
    public void print(Cart cart)
    {
        System.out.println(cart.toString());
    }
    public static void main(String[] args)
    {
        Checkout checkout = new Checkout();
        Cart cart = new Cart();
        checkout.print(cart);
    }
}


