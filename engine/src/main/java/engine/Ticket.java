package Engine.src.main.java.engine;

import java.util.List;

public record Ticket (
    List<TicketLine> lines, 
    double totalHt,
    double vat5,
    double vat20,
    double totalTtc
)
{
    
}
