package org.com1027.coursework.q3optional;

import java.util.ArrayList;
import java.util.List;

public class BuyNowProduct extends Product {

  /* Setting up my fields */
  private double         price;
  private int            quantity;
  private List<Purchase> purchases = new ArrayList<Purchase>();

  /**
   *
   * @param id
   *          The product id.
   * @param ProductName
   *          The name of the product.
   * @param price
   *          The price of the product.
   * @param quantity
   *          The quantity of the product.
   * @throws IllegalArgumentException
   */
  public BuyNowProduct(int id, String ProductName, double price, int quantity) throws IllegalArgumentException {
    // Initialises the variables in the super class' constructor.
    super(id, ProductName);
    // Checks if the price or the quantity is 0 or less.
    if ((price <= 0) || (quantity <= 0)) {
      // Returns an custom error message.
      throw new IllegalArgumentException("The price of this product or the quantity cannot be 0 or less");
    }
    else {
      // Initialising my fields.
      this.price = price;
      this.quantity = quantity;
    }
  }

  /**
   *
   * @param user
   *          The user attempting to purchase the products.
   * @param quantity
   *          The quantity of the product the user wants to purchase.
   * @return A boolean value whether the product has been purchased.
   * @throws IllegalArgumentException
   */
  public boolean attemptToPurchase(User user, int quantity) throws IllegalArgumentException {
    // Checks if the product is not sold
    if (!this.isProductSold()) {
      // Checks if the quantity requested is less than or equal to the total quantity.
      if ((this.howManyPurchased() + quantity) <= this.quantity) {
        // Adds the product into the purchases arraylist.
        this.purchases.add(new Purchase(user, quantity));
        // Returns true as the product has been purchased.
        return true;
      }
      else {
        // Quantity requested exceeds the total quantity so false is returned
        return false;
      }
    }
    // If the product has been sold false is returned.
    return false;
  }

  /**
   * @return The entire purchase history of the product
   */
  @Override
  public String displayHistory() {
    // Initialise the data variable with the product name and the total quantity.
    String data = this.getProductId() + ": teddy quantity: " + this.getQuantity() + "\n";
    // Checks if there aren't any purchases.
    if (this.purchases.size() == 0) {
      // Add "no purchases" to the data variable.
      data += "no purchases";
    }
    else {
      // Adds "buy now history" to the data variable
      data += "buy now history: \n";
      // Loops through the purchases arraylist
      for (Purchase people : this.purchases) {
        // Adds each purchase into the data variable.
        data += people.getBuyer().toString() + " bought " + people.getQuantityPurchased() + "\n";
      }
    }
    return data;
  }

  /**
   * @return The most recent purchase.
   */
  @Override
  public String displayUserInfoForProduct() {
    // Initialise a variable to store the information.
    String data = "";
    // Loops through the purchases ArrayList.
    for (Purchase people : this.purchases) {
      // Adds the recent purchase into the data variable.
      data = people.getBuyer().toString() + " bought " + people.getQuantityPurchased();

    }
    return data;
  }

  /**
   * @return the price of the product.
   */
  @Override
  public double getCurrentPrice() {
    return this.price;
  }

  /**
   *
   * @return the quantity of the product.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   *
   * @return how many units of the product have been purchased.
   */
  public int howManyPurchased() {
    // Initialises the totalPurchases variable to 0.
    int totalPurchases = 0;
    // Loops through the purchases arraylist.
    for (int i = 0; i < this.purchases.size(); i++) {
      // Adds the quantity of each purchase to the totalPurchases variable.
      totalPurchases += this.purchases.get(i).getQuantityPurchased();
    }
    return totalPurchases;
  }

  /**
   * @return boolean value if the product has been sold.
   */
  @Override
  public boolean isProductSold() {
    // Checks if the the number of purchased units exceeds or is equal to the total quantity.
    if (this.howManyPurchased() >= this.quantity) {
      // true is returned as the product has been sold as there aren't any units left.
      return true;
    }
    else {
      // Returns false as all the units have not been sold yet.
      return false;
    }
  }

}
