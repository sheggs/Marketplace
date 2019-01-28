package org.com1027.coursework.q2optional;

public class User {

  /** Initialising the variable to store the user's name */

  private String name;

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
   * Overriding the toString() function to instead return the name of the user.
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.name.toCharArray()[0] + "***" + this.name.toCharArray()[this.name.length() - 1];
  }

}
