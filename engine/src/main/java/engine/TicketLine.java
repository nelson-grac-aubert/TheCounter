package engine.src.main.java.engine;

public record TicketLine (Product product, int quantity, double lineTotal, double excludingTax, double vatRate) {
    
}
