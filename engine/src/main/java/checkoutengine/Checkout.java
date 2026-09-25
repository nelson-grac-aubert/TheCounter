package checkoutengine;

import java.util.ArrayList;
import java.util.List;

import checkoutengine.VAT.VatRateStrategy;

public class Checkout {
    
    // Convert a cart with CartLines into a Ticket with Ticketlines, with 
    // all the data we need : total, discounts... 
    public Ticket convert(Cart cart) {
        List<TicketLine> lines = new ArrayList<>();

        for (CartLine line : cart.getLines()) {
            VatRateStrategy vatRateStrategy = line.product().category().vatRateStrategy();
            double vat = vatRateStrategy.rate();
            
            double excludingTaxLine = vatRateStrategy.excludingTaxFrom(line.cartLineTotal());

            TicketLine ticketLine = new TicketLine(line.product(), line.quantity(), line.cartLineTotal(), excludingTaxLine, vat);

            lines.add(ticketLine);
        }

        //ttc v3
        // double foodTtc = cart.foodTotal()- cart.freeDrinksDiscount();
        // double otherTtc = cart.otherTotal()  ;
        // double subTotal = foodTtc + otherTtc;
        
        double noDisFoodTtc = cart.foodTotal();
        double noDisOtherTtc = cart.otherTotal();
        double noDisSubTotal = noDisFoodTtc + noDisOtherTtc;

        //ttc v2
        // double discount = (subTotal > 50) ? 0.9 : 1.0;
        // foodTtc *= discount;
        // otherTtc *= discount;

        //disoucnt v2 including v5
        double discountV2 = (noDisSubTotal > 50) ? (noDisSubTotal * 0.10) : 0.0;
        //disocunt v3 including v5
        double discountV3 = cart.freeDrinksDiscount();
        //discountv5
        //choose a max of 5 euros as a possible discount if more than 100 points available or else nothing 
        double discountV5 = (cart.getFidPoints() >= 100)? Math.min(5.0, noDisSubTotal) : 0.0;
        // compare best discounts and choose best 
        double bestdiscount = Math.max(discountV2, Math.max(discountV3, discountV5));
        double discountAmount = 0;
        boolean usedFid = false;
        double foodTtc = noDisFoodTtc;
        double otherTtc = noDisOtherTtc;
        //conditions that compare discount amounts and choose best

            discountAmount = bestdiscount;
            if (bestdiscount == discountV2) {
                foodTtc *= 0.9;
                otherTtc *= 0.9;
            }
            else if (bestdiscount == discountV3) {
                foodTtc -= discountV3;
            }
            else // discount v5 is best
            {
                usedFid = true;
                double ratio = noDisFoodTtc / noDisSubTotal; //separate food from other, should rework v3 
                foodTtc -= discountV5 * ratio;
                otherTtc -= discountV5 * (1-ratio);
            }


        //ttc and tax
        double foodHt = foodTtc / 1.055;
        double vat5 = foodTtc - foodHt;
        double otherHt = otherTtc / 1.2;
        double vat20 = otherTtc - otherHt;
        double totalHt = foodHt + otherHt;
        double totalTtc = foodTtc + otherTtc;

        //fid points
        //cast a double as an int and round the amount 48.66 euros = 48 points
        int fidPEarned = (int) Math.floor(totalTtc); 
        int fidBalance = cart.getFidPoints() - (usedFid ? 100 : 0) + fidPEarned;


        return new Ticket(
            lines,
            totalHt,
            vat5,
            vat20,
            totalTtc,
            discountAmount,
            fidPEarned,
            fidBalance
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


