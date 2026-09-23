import type { Cart } from "./domain/Cart";
import type { Ticket } from "./domain/Ticket";

export async function checkout(cart: Cart): Promise<Ticket> {
  const response = await fetch("http://localhost:8080/checkout", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(cart),
  });

  const ticket: Ticket = await response.json();
  return ticket;
}
