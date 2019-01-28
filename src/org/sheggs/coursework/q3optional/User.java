package org.com1027.coursework.q3optional;

import java.util.HashMap;
import java.util.Map;

public class User {

  /** Setting up the fields. */

  private String                name;
  private Map<Integer, Integer> purchases      = new HashMap<Integer, Integer>();
  private Map<Integer, Double>  successfulBids = new HashMap<Integer, Double>();

  /**
   *
   * @param name
   *          This would be the name of the user.
   */
  public User(String name) throws IllegalArgumentException {
    // Check if the name is not null.
    if (name != null) {
      // Initialising the fields.
      this.name = name;
    }
    else {
      throw new IllegalArgumentException("The name is not properly created.");
    }
  }

  /**
   *
   * @param productID
   *          The product's ID.
   * @param quantity
   *          The quantity purchased
   * @throws IllegalArgumentException
   */
  public void buy(int productID, int quantity) throws IllegalArgumentException {
    // Checks if the product ID is less than 0 or the quantity is 0 or less.
    if ((productID < 0) || (quantity <= 0)) {
      throw new IllegalArgumentException("Your productID is less than 0 or the quantity is 0 or less.");
    }
    else {
      // Adds the purchase into the purchases hashmap.
      this.purchases.put(productID, quantity);
    }
  }

  /**
   *
   * @return displays both the successful bids and the purchases.
   */
  public String displayAllPurchases() {
    // Sets up the purchaseHistory variable.
    String purchaseHistory = "All Purchased Products: \n";
    // Adds the lists of all the purchases and bids together.
    purchaseHistory += this.displayPurchases() + this.displaySuccessfulBids();
    return purchaseHistory;
  }

  /**
   *
   * @return displays a list of all the purchases.
   */
  public String displayPurchases() {
    // Sets up the purchaseHistory variable.
    String purchaseHistory = "Purchases: \n";
    // Loops through the hashmap of the purchases.
    for (Map.Entry<Integer, Integer> pairs : this.purchases.entrySet()) {
      // Adds the purchase into the purchaseHistory variable.
      purchaseHistory += pairs.getKey() + " with quantity " + pairs.getValue() + "\n";
    }
    return purchaseHistory;
  }

  /**
   *
   * @return display a list of all the successful bids.
   */
  public String displaySuccessfulBids() {
    // Sets up the purchaseHistory variable.
    String purchaseHistory = "Successful Bids: \n";
    // Loops through the hashmap of the successful bids.
    for (Map.Entry<Integer, Double> pairs : this.successfulBids.entrySet()) {
      // Adds the bid into the purchaseHistory variable.
      purchaseHistory += pairs.getKey() + " at a cost of " + pairs.getValue() + "\n";
    }
    return purchaseHistory;
  }

  /**
   * Overriding the toString() function to instead return the name of the user.
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.name.toCharArray()[0] + "***" + this.name.toCharArray()[this.name.length() - 1];
  }

  /**
   *
   * @param productID
   *          The product's ID.
   * @param winningPrice
   *          The winning price of the auction
   * @throws IllegalArgumentException
   */
  public void wonAuction(int productID, double winningPrice) throws IllegalArgumentException {
    // Checks if the product ID is less than 0 or the winning price is 0 or less.
    if ((productID < 0) || (winningPrice <= 0.0)) {
      throw new IllegalArgumentException("Your productID is less than 0 or the winning price is 0 or less.");
    }
    else {
      // Adds the winning bid into the successfulBids hashmap.
      this.successfulBids.put(productID, winningPrice);
    }
  }

}
