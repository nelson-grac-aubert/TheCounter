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
    // placeholder label
    const vat = "VAT%".padEnd(12);
    // placeholder label only
    const excludingTax = "ET".padEnd(16);
    // "including tax" price
    const includingTax = `IT ${currencyFormatter.format(line.lineTotal)}`.padStart(10);
    return `${label}${quantity}${vat}${excludingTax}${includingTax}`;
  });

  const separator = "-".repeat(74);

  // footer: engine now computes these four (v4), discount amount itself still isn't exposed separately
  const footer = [
    "ET TOTAL".padEnd(31) + currencyFormatter.format(ticket.totalHt).padStart(43),
    "TOTAL 5.5% TAX".padEnd(31) + currencyFormatter.format(ticket.vat5).padStart(43),
    "TOTAL 20% TAX".padEnd(31) + currencyFormatter.format(ticket.vat20).padStart(43),
    "APPLIED DISCOUNT".padEnd(46),
    "NET TOTAL".padEnd(31) + currencyFormatter.format(ticket.totalTtc).padStart(43)
  ];

  const ticketEnd = "\n\n\n";

  // one string per line, joined with real line breaks for console.log
  return [...lines, separator, ...footer, ticketEnd].join("\n");
}
