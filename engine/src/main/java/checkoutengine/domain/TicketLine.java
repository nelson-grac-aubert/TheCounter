package checkoutengine.domain;

public record TicketLine (Product product, int quantity, double lineTotal, double excludingTax, double vatRate) {
    
}
