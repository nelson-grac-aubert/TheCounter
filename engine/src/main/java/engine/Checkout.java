package engine;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Checkout {
    
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
        Checkout checkout = new  Checkout();
        Cart cart = new Cart();
        Ticket ticket = checkout.convert(cart);
        Gson gson = new Gson();
        System.out.println(gson.toJson(ticket));
        checkout.print(cart);
    }
}


