import type { Product } from "./domain/Product";
import type { CartLine } from "./domain/CartLine";
import type { Cart } from "./domain/Cart";

// PRODUCTS
const fruit: Product = {
    reference: "REF000",
    label: "Strawberries 2kg",
    unitPrice: 12.55,
    category: "FOOD",
};

const cheese: Product = {
    reference: "REF001",
    label: "Cheddar cheese 150g",
    unitPrice: 2.29,
    category: "FOOD",
};

const meat: Product = {
    reference: "REF002",
    label: "Minced beef 1kg",
    unitPrice: 17.99,
    category: "FOOD",
};

const lemonade: Product = {
    reference: "REF003",
    label: "Lemonade 1.5L",
    unitPrice: 3.56,
    category: "DRINKS",
};

const beer: Product = {
    reference: "REF004",
    label: "Beer 24 x 33cL",
    unitPrice: 12.79,
    category: "DRINKS",
};

const wine: Product = {
    reference: "REF007",
    label: "White wine Coteaux d'Aix",
    unitPrice: 9.79,
    category: "DRINKS",
};

const sirup: Product = {
    reference: "REF009",
    label: "Almond Sirup 1.5L",
    unitPrice: 2.56,
    category: "DRINKS",
};

const socks: Product = {
    reference: "REF005",
    label: "Cotton socks x3 pairs",
    unitPrice: 12.79,
    category: "OTHER",
};

const gardenChair: Product = {
    reference: "REF006",
    label: "Forged Iron Garden Chair",
    unitPrice: 79.99,
    category: "OTHER",
};

// CARTLINES

const oneFruitLine : CartLine = { product : fruit, quantity: 1 };
const twoFruitsLine : CartLine = { product : fruit, quantity: 2 };
const oneCheeseLine : CartLine = { product : cheese, quantity: 1 };
const threeCheesesLine : CartLine = { product : cheese, quantity: 3 };
const oneMeatLine : CartLine = { product : meat, quantity: 1 };
const oneLemonadeLine : CartLine = { product : lemonade, quantity: 1 };
const fiveLemonadesLine : CartLine = { product : lemonade, quantity: 5 };
const oneBeerLine : CartLine = { product : beer, quantity: 1 };
const fourWinesLine : CartLine = { product : wine, quantity : 4};
const oneSirupLine : CartLine = { product : sirup, quantity: 1 };
const twoSirupsLine : CartLine = { product : sirup, quantity: 2 };
const oneSockLine : CartLine = { product : socks, quantity : 1 }; 
const threeSocksLine : CartLine = { product : socks, quantity : 3}; 
const oneChairLine : CartLine = { product : gardenChair, quantity : 1}; 

// CARTS 

export const threeBeveragesDiscountCart : Cart = [
    oneFruitLine,
    oneCheeseLine,
    fiveLemonadesLine, 
    oneSirupLine,
    oneSockLine
]

export const aboveFiftyEurosCart : Cart = [
    oneChairLine, 
    threeSocksLine, 
    twoSirupsLine, 
    oneMeatLine
]

export const allConflictingDiscountsCart : Cart = [ 
    twoFruitsLine, 
    oneLemonadeLine, 
    oneBeerLine, 
    fourWinesLine,
    threeCheesesLine
]