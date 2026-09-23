package engine.src.main.java.engine;

public record CartLine(Product product, int quantity) {

    // method that multiply each product by the quantity for each line
    public double cartLineTotal() 
    {
        return product.unitPrice() *quantity;
    }
}
