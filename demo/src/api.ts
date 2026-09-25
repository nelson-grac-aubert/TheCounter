import type { Cart } from "./domain/Cart";
import type { Ticket } from "./domain/Ticket";

export async function checkout(cart: Cart, fidelityPoints: number): Promise<Ticket> {
  const response = await fetch("http://localhost:8080/checkout", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({cart, fidelityPoints}),
  });

  const ticket: Ticket = await response.json();
  return ticket;
}
