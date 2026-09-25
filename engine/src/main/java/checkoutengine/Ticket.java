package checkoutengine;

import java.util.List;

public record Ticket (
    List<TicketLine> lines, 
    double totalHt,
    double vat5,
    double vat20,
    double totalTtc,
    //added for fidelity points in v5
    double discountAmount,
    int fidPEarned,
    int fidBalance,
    String discountName
)
{
    
}
