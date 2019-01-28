package org.com1027.coursework.q3;

public class Purchase {

  /* Setting up the fields */
  private User buyer;
  private int  quantityPurchased;

  /**
   *
   * @param buyer
   *          The user that is purchasing the product.
   * @param quantityPurchased
   *          The quantity being purchased.
   * @throws IllegalArgumentException
   */
  public Purchase(User buyer, int quantityPurchased) throws IllegalArgumentException {
    // Checking if the product has been initialised or the quantity is greater than 0
    if ((buyer != null) || (quantityPurchased < 0)) {
      /* Initialising my fields */
      this.buyer = buyer;
      this.quantityPurchased = quantityPurchased;
    }
    else {
      // Throws an exception.
      throw new IllegalArgumentException("Your user object is not properly initalised or your quantity purchased is less than 0.");
    }
  }

  /**
   *
   * @return A user object of the buyer.
   */
  public User getBuyer() {
    return this.buyer;
  }

  /**
   *
   * @return The quantity purchased.
   */
  public int getQuantityPurchased() {
    return this.quantityPurchased;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }

}
