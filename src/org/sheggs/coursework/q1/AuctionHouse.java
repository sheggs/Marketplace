package org.com1027.coursework.q1;

import java.util.HashMap;
import java.util.Map;

public class AuctionHouse {

  /** Declaring my fields */
  private Map<Product, User> forSaleProducts = new HashMap<Product, User>();
  private Map<Product, User> soldProducts    = new HashMap<Product, User>();
  private Map<Product, User> unsoldProducts  = new HashMap<Product, User>();

  public AuctionHouse() {
    super();
  }

  /**
   *
   * @param product
   * @return a boolean value if the product exists in the forSaleProducts hash map.
   */
  public boolean checkExistence(Product product) {
    // Initialising a boolean variable to false. This value will change to true once we find the product inside the hashmap.
    boolean existance = false;
    // Loop through the keys inside the hash map.
    for (Product keys : this.forSaleProducts.keySet()) {
      // Checks if the value keys is the same as the product.
      if (keys == product) {
        // Changes the existence value to true because we have found the product inside the hashmap.
        existance = true;
      }
    }
    return existance;
  }

  /**
   *
   * @return a string value containing all the sold products
   */

  public String displaySoldProducts() {
    // Here I initialised a variable that stores the data.
    String listProducts = "";
    // Loop through both the key and value inside the soldProducts hashmap.
    for (Map.Entry<Product, User> mapPairs : this.soldProducts.entrySet()) {
      // I add the data into the listProducts variable.
      listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().getHighestBid().getBuyer().toString() + " bid £"
          + mapPairs.getKey().getHighestBid().getBidValue() + "\n";
    }
    return listProducts;
  }

  /**
   *
   * @return a string value containing all the unsold products
   */
  public String displayUnsoldProducts() {
    // Here I initialised a variable that stores the data.
    String listProducts = "";
    // Loop through both the key and value inside the unsoldProducts hashmap.
    for (Map.Entry<Product, User> mapPairs : this.unsoldProducts.entrySet()) {
      // I add the data into the listProducts variable
      listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().getProductName() + "\n";
    }
    return listProducts;
  }

  /**
   *
   * @param product
   *          The product I want to end the auction for.
   */
  public void endAuction(Product product) {
    // Checks if the product is has been registered.
    if (this.checkExistence(product)) {
      // Checks if there is a bidder.
      if (product.getHighestBid() != null) {
        // Checks if the bid is greater than the reserved price.
        if (product.getHighestBid().getBidValue() > product.getReservedPrice()) {
          // Here I put the product into the sold product hashmap
          this.soldProducts.put(product, this.forSaleProducts.get(product));
          // Remove the product from the forSaleProducts hashmap since the product is now unsold.
          this.forSaleProducts.remove(product);
        }
        else {
          // Here I put the product into the unsold product hashmap
          this.unsoldProducts.put(product, this.forSaleProducts.get(product));
          // Remove the product from the forSaleProducts hashmap since the product is now unsold.
          this.forSaleProducts.remove(product);
        }
      }
      else {
        // Here I put the product into the unsold product hashmap
        this.unsoldProducts.put(product, this.forSaleProducts.get(product));
        // Remove the product from the forSaleProducts hashmap since the product is now unsold.
        this.forSaleProducts.remove(product);
      }
    }
  }

  /**
   *
   * @param product
   *          The product that you want to bid for.
   * @param user
   *          The user bidding for the product.
   * @param bidValue
   *          The bid value.
   * @return A boolean value whether the bid has been placed.
   * @throws IllegalArgumentException
   */
  public boolean placeBid(Product product, User user, double bidValue) throws IllegalArgumentException {
    // Checks if the product and user has not been initialised. And if so we throw an illegal argument exception.
    if ((product == null) || (user == null)) {
      throw new IllegalArgumentException("Your objects aren't properly intialised!");
    }
    else {
      // Checks if the product has been registered.
      if (this.checkExistence(product)) {
        // Places a bid and checks if it has been placed.
        if (product.placeBid(user, bidValue)) {
          // Returns true as the bid has been placed
          return true;
        }
        else {
          // Returns false as the bid has not been placed.
          return false;
        }
      }
      else {
        // Returned since the item has not been registered
        return false;
      }
    }

  }

  /**
   *
   * @param product
   *          The product you want to register
   * @param user
   *          The user registering the product.
   * @return a boolean value if the product has been registered
   * @throws IllegalArgumentException
   */
  public boolean register(Product product, User user) throws IllegalArgumentException {
    // Here we check if the product and user has been properly initialised.
    if ((product == null) || (user == null)) {
      // If the objects are not properly initialised we throw a exception with a custom message to tell the user what is wrong.
      throw new IllegalArgumentException(" Your objects aren't properly intialised!");
    }
    else {
      // We check if the product exists already. If it doesn't we will add the product into the hash map.
      if (!this.checkExistence(product)) {
        this.forSaleProducts.put(product, user);
        // We returned true as the product has been registered.
        return true;
      }
      else {
        // return false since the product already exists
        return false;
      }
    }

  }

}
