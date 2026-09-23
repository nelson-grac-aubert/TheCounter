import type { Ticket } from "./domain/Ticket";

// created once, reused for every price — building an Intl.NumberFormat is not free
const currencyFormatter = new Intl.NumberFormat("fr-FR", {
  style: "currency",
  currency: "EUR",
});

export function formatReceipt(ticket: Ticket): string {
  // turn each TicketLine into one aligned text line
  const lines = ticket.lines.map((line) => {
    // label padded to 25 chars so every line's quantity column lines up
    const label = line.product.label.padEnd(30);
    // quantity prefixed with "x", padded so the price column lines up
    const quantity = `x${line.quantity}`.padEnd(6);
    // price right-aligned on 10 chars so decimals line up across lines
    const price = currencyFormatter.format(line.lineTotal).padStart(10);
    return `${label}${quantity}${price}`;
  });

  // dashes matching the total line width (25 + 6 + 10), purely visual
  const separator = "-".repeat(46);
  const ticketEnd = " ".repeat(46);


  const totalLine = "TOTAL".padEnd(31) + currencyFormatter.format(ticket.total).padStart(15);

  // one string per line, joined with real line breaks for console.log
  return [...lines, separator, totalLine, ticketEnd, ticketEnd, ticketEnd].join("\n");
}
