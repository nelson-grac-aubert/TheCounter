import { Product } from "./Product";

export interface TicketLine {
  product: Product;
  quantity: number;
  lineTotal: number;
}

export interface Ticket {
  lines: TicketLine[];
  total: number;
}
