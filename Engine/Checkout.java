package Engine;

public class Checkout {
    
    public void print(Cart cart)
    {
        System.out.println(cart.checkout());
    }
    public static void main(String[] args)
    {
        Checkout checkout = new  Checkout();
        Cart cart = new Cart();
        checkout.print(cart);
    }
}


