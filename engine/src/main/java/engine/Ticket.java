package engine;

import java.util.List;

public record Ticket (List<TicketLine> lines, double total) {
    
}
