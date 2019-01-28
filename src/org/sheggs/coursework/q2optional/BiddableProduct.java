package org.com1027.coursework.q2optional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiddableProduct extends Product {
  /* Setting up all my fields. */

  private double    reservedPrice;
  private List<Bid> bids = new ArrayList<Bid>();

  /**
   *
   * @param productID
   *          The id of the product
   * @param productName
   *          The name of the product
   * @param reservedPrice
   *          The reserved price of the product.
   */
  public BiddableProduct(int productID, String productName, double reservedPrice) throws IllegalArgumentException {
    // This super initialises the fields inside the parent class.
    super(productID, productName);
    if (productName == null) {
      throw new IllegalArgumentException("The product name is not properly initalised");
    }
    else {
      // Initialises my field with a variable.
      this.reservedPrice = reservedPrice;
    }
  }

  /**
   *
   * @param user
   *          The user that wants to purchase the product.
   * @param bidValue
   *          The value of the bid.
   * @return A boolean value whether the purchase has been successful.
   * @throws IllegalArgumentException
   */

  public boolean attemptToPurchase(User user, double bidValue) throws IllegalArgumentException {
    // Checking the currentPrice is less than the bid value and if there is a highest bidder.
    if ((this.getHighestBid() != null) && (this.getCurrentPrice() < bidValue)) {
      // Inserts the bid into the bids ArrayList.
      this.bids.add(new Bid(user, bidValue));
      // Returns true because the bid has been successfully placed.
      return true;
    }
    else if (this.getHighestBid() == null) {
      // Inserts the bid into the bids ArrayList.
      this.bids.add(new Bid(user, bidValue));
      // Returns true because the bid has been successfully placed.
      return true;
    }
    // Returns false as the purchase has failed.
    return false;
  }

  /**
   * @return Returns a string containing the bid history.
   */
  @Override
  public String displayHistory() {
    // Initialises a variable that stores the data.
    String data = "";
    // Checks if the bid size is 0 so we can display that there is no bids.
    if (this.bids.size() == 0) {
      data = this.getProductId() + ": " + this.getProductName() + " = no bids";
    }
    else {
      // Adds the product ID and the product name into the data variable.
      data = this.getProductId() + ": " + this.getProductName() + " = \n";
      // Loops through the bids arraylist.
      for (int i = 0; i < this.bids.size(); i++) {
        // Adds each bid into the data variable.
        data += this.bids.get(i).getBuyer().toString() + " bid £" + this.bids.get(i).getBidValue() + "\n";
      }
    }
    return data;
  }

  /**
   * @return A string that contains the highest bidders information.
   */
  @Override
  public String displayUserInfoForProduct() {
    // Checks if there is a highest bidder.
    if (this.getHighestBid() != null) {
      // Returns the highest bid.
      return this.getHighestBid().getBuyer().toString() + " bid £" + this.getHighestBid().getBidValue();
    }
    else {
      // Returns empty string since there is no bids.
      return "";
    }
  }

  /**
   * @return The current bid value.
   */
  @Override
  public double getCurrentPrice() {
    // Checks if there is no current bids.
    if (this.getHighestBid() == null) {
      // Returns 0 as there are no bids
      return 0;
    }
    else {
      // Returns the highest bidders value.
      return this.getHighestBid().getBidValue();
    }
  }

  /**
   *
   * @return The highest valued bid object.
   */
  private Bid getHighestBid() {
    // Checks if there are any bids placed.
    if (this.bids.size() == 0) {
      // Returns null if there are no bids placed.
      return null;
    }
    else {
      // Sorts the arraylist.
      Collections.sort(this.bids);
      // Returns the first index of the arraylist since that contains the highest bidder.
      return this.bids.get(0);
    }
  }

  /**
   * @return a boolean value whether the product has been sold.
   */
  @Override
  public boolean isProductSold() {
    // Checks if a highest bidder exists
    if (this.getHighestBid() != null) {
      // Checks if the highest bid is greater than the reserved price
      if (this.getHighestBid().getBidValue() > this.reservedPrice) {
        // Returns true as the product is sold.
        return true;
      }
      else {
        // Returns false as the product has not been sold.
        return false;
      }
    }
    else {
      // Returns false as the product has not been sold.
      return false;
    }
  }

}
