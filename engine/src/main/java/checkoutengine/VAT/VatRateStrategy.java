package checkoutengine.VAT;

// VAT becomes a Strategy, each new VAT rate becomes an implementation
// Adding/Removing/Modifying a VAT rate will never touch Checkout anymore
public interface VatRateStrategy {
    double rate();

    // default : children auto-inherit this method
    default double excludingTaxFrom(double includingTax) {
        return includingTax / (1 + rate() / 100);
    }

}