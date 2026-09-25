package checkoutengine;

import java.util.ArrayList;
import java.util.List;

import checkoutengine.VAT.VatRateStrategy;
import checkoutengine.discounts.DiscountHandler;
import checkoutengine.discounts.FidelityDiscountHandler;
import checkoutengine.discounts.FreeDrinksDiscountHandler;
import checkoutengine.discounts.NoDiscount;
import checkoutengine.discounts.ThresholdDiscountHandler;

public class Checkout {
    
    // fidelityPoints is passed as a parameter, not stored on Cart : loyalty balance
    // belongs to the customer, not to a single basket : keeps Cart a simple line container (SRP)
    public Ticket convert(Cart cart, int fidelityPoints) {
        List<TicketLine> lines = new ArrayList<>();

        for (CartLine line : cart.getLines()) {
            VatRateStrategy vatRateStrategy = line.product().category().vatRateStrategy();
            double vat = vatRateStrategy.rate();
            
            double excludingTaxLine = vatRateStrategy.excludingTaxFrom(line.cartLineTotal());

            TicketLine ticketLine = new TicketLine(line.product(), line.quantity(), line.cartLineTotal(), excludingTaxLine, vat);

            lines.add(ticketLine);
        }

        // order doesn't matter : all are checked, and only the best one is kept
        DiscountHandler chain = new ThresholdDiscountHandler();
        chain.setNext(new FreeDrinksDiscountHandler()).setNext(new FidelityDiscountHandler());
        // Default is NoDiscount to start the chain check
        DiscountHandler winner = chain.findBest(cart, fidelityPoints, new NoDiscount());
        
        // COR : get the best discount value (for computation) and name (for receipt display)
        double discountAmount = winner.computeDiscount(cart, fidelityPoints);
        String discountName = winner.name();

        double noDisFoodTtc = cart.foodTotal();
        double noDisOtherTtc = cart.otherTotal();
        double noDisSubTotal = noDisFoodTtc + noDisOtherTtc;

        boolean usedFid = winner instanceof FidelityDiscountHandler;
        double ratio = noDisFoodTtc / noDisSubTotal;
        double foodTtc = noDisFoodTtc - discountAmount * ratio;
        double otherTtc = noDisOtherTtc - discountAmount * (1 - ratio);

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
        int fidBalance = fidelityPoints - (usedFid ? 100 : 0) + fidPEarned;

        return new Ticket(
            lines,
            totalHt,
            vat5,
            vat20,
            totalTtc,
            discountAmount,
            fidPEarned,
            fidBalance,
            discountName
            );
    }
}


