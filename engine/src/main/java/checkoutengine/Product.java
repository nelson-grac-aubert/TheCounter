package checkoutengine;

import checkoutengine.VAT.VatRateStrategy;
import checkoutengine.VAT.FoodVatRate;
import checkoutengine.VAT.StandardVatRate;

//Record is a type of class to store data, no setters, read-only
// public record Product(String label, double unit_price, String refrence, String category)
//unit price is TTC

public record Product(String reference, ProductCategory category, String label, double unitPrice)
{
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

/*
init object 
Product écran = new Product("écran", 1500);
*/

/*
no need for getters

System.out.println(écran.unit_price()); : 1500
System.out.println(écran)); Product[label="écran", price=1500]
*/