package checkoutengine;

import java.util.List;

import checkoutengine.VAT.VatBreakdownLine;

public record Ticket (
    List<TicketLine> lines, 
    double totalHt,
    List<VatBreakdownLine> vatBreakdown,
    double totalTtc,
    //added for fidelity points in v5
    double discountAmount,
    int fidPEarned,
    int fidBalance,
    String discountName
)
{
    
}
