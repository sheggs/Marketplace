package org.com1027.coursework.q1;

public class Bid implements Comparable<Bid> {

  /** Declaring my fields */
  private User   buyer;
  private double bidValue;

  /**
   *
   * @param buyer
   *          This would be the person who places this bid.
   * @param bidValue
   *          The value of the bid.
   *
   */
  public Bid(User buyer, double bidValue) throws IllegalArgumentException {
    super();
    // Checks if the buyer object is null or if the bid value is less than or equal to zero.
    if ((buyer == null) || (bidValue <= 0)) {
      // Throws an exception with a custom message as the buyer object is null.
      throw new IllegalArgumentException("Your buyer object is null or your bidValue is less than 0.");
    }
    else {
      // Initialises my fields.
      this.buyer = buyer;
      this.bidValue = bidValue;
    }
  }

  /**
   * Here I am changing the original compareTo function since this is a custom object. I am going to make the compareTo function
   * compare the objects by their bid value.
   *
   * @return the integer returned is the comparison between the two objects.
   */

  @Override
  public int compareTo(Bid o) throws NullPointerException {
    // Here I check if the bid object is null
    if (o == null) {
      // Throws an exception since the object is null
      throw new NullPointerException("The comparable object is null. There is a null object inside your array");
    }
    return ((int) o.getBidValue() - (int) this.getBidValue());
  }

  /**
   * @return the current bid value
   */

  public double getBidValue() {
    return this.bidValue;
  }

  /**
   * @return the buyer
   */
  public User getBuyer() {
    return this.buyer;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.buyer.toString() + " bid £" + this.bidValue;
  }

}
