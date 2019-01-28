package org.com1027.coursework.q3optional;

import java.util.HashMap;
import java.util.Map;

public class AuctionHouse {

  /* Initialising all my fields. */
  private Map<Product, User> forSaleProducts = new HashMap<Product, User>();
  private Map<Product, User> soldProducts    = new HashMap<Product, User>();
  private Map<Product, User> unsoldProducts  = new HashMap<Product, User>();

  /* Default Constructor */
  public AuctionHouse() {
    super();
  }

  /**
   *
   * @param product
   *          The product being brought
   * @param buyer
   *          The user buying the product
   * @param quantity
   *          The quantity the user wants to purchase.
   * @return a boolean value whether the sale has been successful.
   */
  public boolean buyNow(BuyNowProduct product, User buyer, int quantity) {
    // Checks if the product has been registered
    if (this.checkExistence(product)) {
      // Calls the attemptToPurchase function. This checks if the product can be brought.
      if (product.attemptToPurchase(buyer, quantity)) {
        // Records this purchase into the buyers records
        buyer.buy(product.getProductId(), quantity);
        // Returns true as the product has been purchased
        return true;
      }
      else {
        // Return false as the product cannot be purchased
        return false;
      }
    }
    else {
      // Returns false as the product has not been registered
      return false;
    }
  }

  /**
   *
   * @param product
   * @return a boolean value if the product exists in the forSaleProducts hash map.
   */
  public boolean checkExistence(Product product) {
    // Initialising a boolean variable to false. This value will change to true we find the product inside the hashmap.
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
      // Checking if the product is a buynow product.
      listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().displayUserInfoForProduct() + "\n";

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
      // Checks if the product is a instance of the BuyNowProducts class.
      if (mapPairs.getKey() instanceof BuyNowProduct) {
        // Casts the key into a BuyNowProduct
        BuyNowProduct product = (BuyNowProduct) mapPairs.getKey();
        // Checks if nothing has been purchased
        if (product.howManyPurchased() == 0) {
          // Add the Product ID and name into the listProducts variable.
          listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().getProductName() + "\n";
        }
        else {
          // Adds the product ID, name and the quantity remaining.
          listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().getProductName() + " with "
              + (product.getQuantity() - product.howManyPurchased()) + " remaining\n";
        }
      }
      // This is else statement only occurs when the key is a biddable product.
      else {

        // Add the Product ID and name into the listProducts variable.
        listProducts += mapPairs.getKey().getProductId() + " - " + mapPairs.getKey().getProductName() + "\n";
      }
    }
    return listProducts;
  }

  /**
   *
   * @param product
   *          : The product I want to end the auction for.
   */
  public void endAuction(Product product) {
    // Loops through the key and value pairs in the forSaleProducts hashmap.
    for (Map.Entry<Product, User> pairs : this.forSaleProducts.entrySet()) {
      // Checks if the key is the same as the product we want to end the auction for
      if (product == pairs.getKey()) {
        // Checks if the product has been sold.
        if (product instanceof BiddableProduct) {
          if (pairs.getKey().isProductSold()) {
            // Inserts the product and the seller into the sold products hashmap.
            this.soldProducts.put(product, pairs.getValue());
            // Removes the product from the forsaleProducts hashmap.
            this.forSaleProducts.remove(product);
            // Breaks to stop the loop.
            break;
          }
          else {
            // Inserts the product and the seller into the sold products hashmap.
            this.unsoldProducts.put(product, pairs.getValue());
            // Removes the product from the forSaleProducts hashmap.
            this.forSaleProducts.remove(product);
            // Breaks to stop the loop.
            break;
          }
        }
        else {
          BuyNowProduct buyproduct = (BuyNowProduct) pairs.getKey();
          if (buyproduct.isProductSold()) {
            // Inserts the product and the seller into the sold products hashmap.
            this.soldProducts.put(product, pairs.getValue());
            // Removes the product from the forsaleProducts hashmap.
            this.forSaleProducts.remove(product);
            // Breaks to stop the loop.
            break;
          }
          else if (buyproduct.howManyPurchased() == 0) {
            // Inserts the product and the seller into the unsold products hashmap.
            this.unsoldProducts.put(product, pairs.getValue());
            // Removes the product from the forSaleProducts hashmap.
            this.forSaleProducts.remove(product);
            // Breaks to stop the loop.
            break;
          }
          else if (buyproduct.howManyPurchased() < buyproduct.getQuantity()) {
            // Inserts the product and the seller into the sold products hashmap.
            this.soldProducts.put(product, pairs.getValue());
            // Inserts the product and the seller into the unsold products hashmap.
            this.unsoldProducts.put(product, pairs.getValue());
            // Removes the product from the forsaleProducts hashmap.
            this.forSaleProducts.remove(product);
            break;
          }

        }

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
  public boolean placeBid(BiddableProduct product, User user, double bidValue) throws IllegalArgumentException {
    // Check if the product or the user has been initialised properly.
    if ((product == null) || (user == null)) {
      // Returns a custom error message to tell the user what is wrong.
      throw new IllegalArgumentException("Your product or user is not properly intialised");
    }
    else {
      // Checks if the product has been registered.
      if (this.checkExistence(product)) {
        // Calls the attemptToPurchase function(). This will allow the user to bid on the product.
        if (product.attemptToPurchase(user, bidValue)) {
          // Now checks if the product has been sold. This checks if the bidvalue has exceeded the reserved price.
          if (product.isProductSold()) {
            // If the product is sold we will record this in the users history.
            user.wonAuction(product.getProductId(), bidValue);
          }
          // Return true if the attemptToPurchase function has been successful.
          return true;
        }
      }
      // Here the product has not been registered so false is returned
      else {
        return false;
      }
    }
    // Returns false if no other conditions are met.
    return false;
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
    // Checking if the product and user is being properly initialised
    if ((product == null) || (user == null)) {
      // Throws a custom error message to tell the user what is wrong.
      throw new IllegalArgumentException("Your product or user is not properly intialised");
    }
    else {
      // Checking if the product has been registered.
      if (!this.checkExistence(product)) {
        // Inserts the product into the hashmap.
        this.forSaleProducts.put(product, user);
        // Returns true as the product has been successfully registered
        return true;
      }
      else {
        // This is a duplicate register so return false.
        return false;
      }
    }
  }

}