import type { Product } from "./Product";

export interface TicketLine {
  product: Product;
  quantity: number;
  lineTotal: number;
  excludingTax: number;
  vatRate: number;
}

export interface Ticket {
  lines: TicketLine[];
  totalHt: number;
  vat5: number;
  vat20: number;
  totalTtc: number;
  discountAmount: number;
  fidPEarned: number;
  fidBalance: number;
}
