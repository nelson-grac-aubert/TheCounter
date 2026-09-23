package Engine.src.main.java.engine;

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

        //ttc v3
        double foodTtc = cart.foodTotal()- cart.freeDrinksDiscount();
        double otherTtc = cart.otherTotal()  ;
        double subTotal = foodTtc + otherTtc;

        //ttc v2
        double discount = (subTotal > 50) ? 0.9 : 1.0;
        foodTtc *= discount;
        otherTtc *= discount;

        //ttc and tax
        double foodHt = foodTtc / 1.055;
        double vat5 = foodTtc - foodHt;
        double otherHt = otherTtc / 1.2;
        double vat20 = otherTtc - otherHt;
        double totalHt = foodHt + otherHt;
        double totalTtc = foodTtc + otherTtc;

        return new Ticket(
            lines,
            totalHt,
            vat5,
            vat20,
            totalTtc
        );


        


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


