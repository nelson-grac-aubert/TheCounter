package checkoutengine.VAT;

public interface VatRateStrategy {
    double rate();

    default double excludingTaxFrom(double includingTax) {
        return includingTax / (1 + rate() / 100);
    }

}