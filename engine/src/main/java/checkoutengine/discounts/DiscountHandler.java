package checkoutengine.discounts;

import checkoutengine.Cart;

public abstract class DiscountHandler {
    private DiscountHandler next;

    public DiscountHandler setNext(DiscountHandler next) {
        this.next = next;
        return next;
    }

    public double handle(Cart cart, double bestSoFar) {
        double candidate = computeDiscount(cart);
        double newBest = Math.max(bestSoFar, candidate);
        return (next != null) ? next.handle(cart, newBest) : newBest;
    }

    protected abstract double computeDiscount(Cart cart);
}
