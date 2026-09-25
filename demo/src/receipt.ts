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
    // VAT rate computed by the engine (v4)
    const vat = `VAT ${line.vatRate}%`.padEnd(12);
    // price excluding tax computed by the engine (v4)
    const excludingTax = `ET ${currencyFormatter.format(line.excludingTax)}`.padEnd(16);
    // "including tax" price
    const includingTax = `IT ${currencyFormatter.format(line.lineTotal)}`.padStart(10);
    return `${label}${quantity}${vat}${excludingTax}${includingTax}`;
  });

  const separator = "-".repeat(74);

  // one line per VAT rate actually present on this ticket — no hardcoded rate count anymore
  const vatLines = ticket.vatBreakdown.map((entry) => {
    return `TOTAL ${entry.rate}% TAX`.padEnd(31) + currencyFormatter.format(entry.vatAmount).padStart(43);
  });

  // footer: engine now computes all of this (v4 totals, v5 discount + loyalty points)
  const footer = [
    "ET TOTAL".padEnd(31) + currencyFormatter.format(ticket.totalHt).padStart(43),
    ...vatLines,
    `${ticket.discountName}`.padEnd(31) + currencyFormatter.format(ticket.discountAmount).padStart(43),
    "NET TOTAL".padEnd(31) + currencyFormatter.format(ticket.totalTtc).padStart(43),
    "POINTS EARNED".padEnd(31) + String(ticket.fidPEarned).padStart(43),
    "POINTS BALANCE".padEnd(31) + String(ticket.fidBalance).padStart(43),
  ];

  const ticketEnd = "\n\n\n";

  // one string per line, joined with real line breaks for console.log
  return [...lines, separator, ...footer, ticketEnd].join("\n");
}
