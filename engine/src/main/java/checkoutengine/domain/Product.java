package checkoutengine.domain;

import checkoutengine.VAT.VatRateStrategy;
import checkoutengine.VAT.FoodVatRate;
import checkoutengine.VAT.StandardVatRate;

// Record is a type of class to store data, no setters, read-only
// public record Product(String label, double unit_price, String refrence, String category)
// unit price is TTC

public record Product(String reference, ProductCategory category, String label, double unitPrice)
{   

    // Each category has its VAT rate Strategy implementation, no more if/else
    public enum ProductCategory {
        FOOD(new FoodVatRate()),
        DRINKS(new FoodVatRate()),
        OTHER(new StandardVatRate());

        private final VatRateStrategy vatRateStrategy;

        ProductCategory(VatRateStrategy vatRateStrategy) {
            this.vatRateStrategy = vatRateStrategy;
        }

        public VatRateStrategy vatRateStrategy() {
            return vatRateStrategy;
        }
    }
 
}
