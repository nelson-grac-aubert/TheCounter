import type { Category } from "./Category";

export interface Product {
  reference: string;
  label: string;
  unitPrice: number;
  category: Category;
}
