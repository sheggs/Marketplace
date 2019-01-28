package org.com1027.coursework.q3;

public abstract class Product {

  /*
   * Here I declare all my fields.
   */
  private int    productID;
  private String productName;

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
  public Product(int id, String ProductName) throws IllegalArgumentException {
    super();
    // Here I check if the string object productName is null.
    if (ProductName != null) {
      // Here we initialise all the fields.
      this.productID = id;
      this.productName = ProductName;
    }
    else {
      // Throws an exception since the product name is null.
      throw new IllegalArgumentException("The product name is invalid");
    }
  }

  /* Setting up abstract functions to be inherited */
  abstract public String displayHistory();

  abstract public String displayUserInfoForProduct();

  abstract public double getCurrentPrice();

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

  abstract public boolean isProductSold();

  @Override
  public String toString() {
    return this.productID + " - " + this.productName;
  }

}
