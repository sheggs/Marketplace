package org.com1027.coursework.q2optional;


public class Purchase {
  private User buyer;
  private int quantityPurchased;
  
  
  public Purchase(User buyer,int quantityPurchased) {
    this.buyer = buyer;
    this.quantityPurchased = quantityPurchased;
  }
  
  public User getBuyer() {
    return buyer;
  }
  
  public int getQuantityPurchased() {
    return quantityPurchased;
  }
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
    
}
