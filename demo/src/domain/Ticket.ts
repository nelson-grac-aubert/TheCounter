import type { Product } from "./Product";

export interface TicketLine {
  product: Product;
  quantity: number;
  lineTotal: number;
  excludingTax: number;
  vatRate: number;
}

export interface VatBreakdownLine {
  rate: number;
  vatAmount: number;
}

export interface Ticket {
  lines: TicketLine[];
  totalHt: number;
  vatBreakdown: VatBreakdownLine[];
  totalTtc: number;
  discountAmount: number;
  fidPEarned: number;
  fidBalance: number;
  discountName: string;
}
