import { checkout } from "./api";
import { formatReceipt } from "./receipt";
import { threeBeveragesDiscountCart, aboveFiftyEurosCart, allConflictingDiscountsCart, fidelityCart } from "./scenarios";

async function main() {
  const carts = [threeBeveragesDiscountCart, aboveFiftyEurosCart, allConflictingDiscountsCart, fidelityCart];
  let points = 0;

  for (const cart of carts) {
    const ticket = await checkout(cart, points);
    console.log(formatReceipt(ticket));
    points = ticket.fidBalance; 

  }
}

main();
