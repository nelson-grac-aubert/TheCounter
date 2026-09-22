package Engine;

public record CartLine(Product product, int quantity) {

    
    public double cartLineTotal() 
    {
        return product.unit_price() *quantity;
    }
}
