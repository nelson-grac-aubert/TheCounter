import { checkout } from "./api";
import { threeBeveragesDiscountCart, aboveFiftyEurosCart, allConflictingDiscountsCart } from "./scenarios";

async function main() {
  const carts = [threeBeveragesDiscountCart, aboveFiftyEurosCart, allConflictingDiscountsCart];

  for (const cart of carts) {
    const ticket = await checkout(cart);
    console.log(ticket);
  }
}

main();
