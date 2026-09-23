# The Counter

A lightweight Java checkout engine with a TS demo client to learn about fast and firty coding & technical debt, audit, refactoring and design patterns

## 1. Project and team

The Counter allows you to : 
- Calculate the total of a cart. 
- Apply a 10% discount for a cart over 50€.
- Offer the cheapest of 3+ drinks. 
- Add VAT, depending on the product category. 
- Apply a fidelity discount of 5€ on the next checkout after 100€ spent. 

The team : 

- [**Hugo Belaloui**](https://github.com/hugo-belaloui)
- [**Nelson Grac-Aubert**](https://github.com/nelson-grac-aubert)

## 2. Prerequisites and setup

You will need:
- Java 21+
- Maven (or use the wrapper included in `backend/`, no local install needed)
- Node.js 18+ and npm

### Engine

First, launch the engine using : 

```
cd engine
mvn compile exec:java
```

The API starts on `http://localhost:8080`.

### Demo Client

```
cd demo
npm install
npm run dev
```

This will send three carts to illustrate the engine functionning and applying the right business rules. 

## 3. Technical choices and difficulties

TODO





