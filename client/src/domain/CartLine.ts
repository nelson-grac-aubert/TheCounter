import type { Product } from "./Product";

export interface CartLine {
  product: Product;
  quantity: number;
}
