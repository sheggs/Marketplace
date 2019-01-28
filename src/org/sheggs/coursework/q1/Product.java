package org.com1027.coursework.q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {

  /*
   * Here I declare all my fields.
   */
  private int       productID;
  private String    productName;
  private double    reservedPrice;
  private List<Bid> bids = new ArrayList<Bid>();

  /**
   *
   * @param id
   *          : The products id
   * @param ProductName
   *          : The Products Name
   * @param reservedPrice
   *          : The reserved price for the product
   * @throws IllegalArgumentException
   */
  public Product(int id, String ProductName, double reservedPrice) throws IllegalArgumentException {
    super();
    // Here I check if the string object productName is null.
    if (ProductName != null) {
      // Here we initialise all the fields.
      this.productID = id;
      this.productName = ProductName;
      this.reservedPrice = reservedPrice;
    }
    else {
      // Throws an exception since the product name is null.
      throw new IllegalArgumentException("The product name is invalid");
    }
  }

  /**
   *
   * @return the bid object with largest bid value.
   */
  public Bid getHighestBid() {
    // Here I check if the size of the array list is less than or equal to zero.
    if (!(this.bids.size() <= 0)) {
      // Sorts the ArrayList bids.
      Collections.sort(this.bids);
      // Returns the bid object that has the largest bid value.
      return this.bids.get(0);
    }
    else {
      // Returns null because there is no bid placed on the product.
      return null;
    }
  }

  /**
   *
   * @return the product ID for the product.
   */
  public int getProductId() {
    return this.productID;
  }

  /**
   *
   * @return the product name.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   *
   * @return the reserved price for the product
   */
  public double getReservedPrice() {
    return this.reservedPrice;
  }

  /**
   *
   * @param user
   *          : The user placing the bid
   * @param bidValue
   *          : The value of the bid placed
   * @return A boolean value whether the bid has been placed.
   */
  public boolean placeBid(User user, double bidValue) {
    // We check if the Array List bids has a size greater than zero. The reason I have done this is because I don't want to find the
    // highest bidder if there is no bidder at all. This prevents any NullPointerException errors for the getHighestBidder()
    // function.
    if (this.bids.size() > 0) {
      // This compares the new bid to the current highest bid. (Ensuring the newest bid is larger).
      if (this.getHighestBid().getBidValue() < bidValue) {
        // Creating a bid object.
        Bid newBid = new Bid(user, bidValue);
        // Adds the new bid object into the ArrayList bids.
        this.bids.add(newBid);
        // Returns true since the bid has been placed
        return true;
      }
      else {
        // Returned false here because the new bid is smaller than the current highest bid.
        return false;
      }
    }
    // This else statement only works if the bid being placed is the first bid on the product.
    else {
      // Here I create a new bid object. No need for defensive code since it's already included in the Bid class.
      Bid newBid = new Bid(user, bidValue);
      // Adds the bid into the ArrayList bids.
      this.bids.add(newBid);
      // Returns true because the bid has been placed
      return true;
    }
  }

  @Override
  public String toString() {
    return "Product ID : " + this.productID + " Product Name: " + this.productName + " Reserved Price: " + this.reservedPrice;
  }

}
