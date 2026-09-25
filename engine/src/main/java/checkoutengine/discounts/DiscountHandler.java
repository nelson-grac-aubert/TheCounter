package checkoutengine.discounts;

import checkoutengine.Cart;

/* Chain Of Reponsability 
DiscountHandler goes through each possible discount and keeps the best 
with a recursion */
public abstract class DiscountHandler {
    private DiscountHandler next;

    public DiscountHandler setNext(DiscountHandler next) {
        this.next = next;
        return next;
    }

    public DiscountHandler findBest(Cart cart, int fidelityPoints, DiscountHandler currentBest) {
        // Is the current discount on the chain the best one? if so, save it
        DiscountHandler winner = (computeDiscount(cart, fidelityPoints) > currentBest.computeDiscount(cart, fidelityPoints)) ? this : currentBest;
        // Is there still a discount to check? Recursively check if its the best 
        return (next != null) ? next.findBest(cart, fidelityPoints, winner) : winner;
    }

    public abstract double computeDiscount(Cart cart, int fidelityPoints);
    public abstract String name();
}
