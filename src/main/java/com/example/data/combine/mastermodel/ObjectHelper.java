package com.example.data.combine.mastermodel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by khan on 10/31/17.
 */

/**
 * Inspired by {@link org.apache.commons.lang3.builder.EqualsBuilder} and
 * {@link org.apache.commons.lang3.builder.HashCodeBuilder}
 */
public class ObjectHelper {
  private ObjectHelper() {
    throw new UnsupportedOperationException("must not be instantiated");
  }

  /**
   * Helper method to implement {@link Object#equals(Object)}. Ignore volatile fields.
   *
   * @param lhs this object
   * @param rhs the other object
   * @return true if the two Objects have tested equals.
   */
  public static boolean equals(Object lhs, Object rhs) {
    return EqualsBuilder.reflectionEquals(lhs, rhs, false);
  }

  /**
   * Helper method to implement {@link Object#equals(Object)}. Ignore volatile fields.
   *
   * @param lhs           this object
   * @param rhs           the other object
   * @param excludeFields array of field names to exclude from testing
   * @return true if the two Objects have tested equals.
   */
  public static boolean equals(Object lhs, Object rhs, String... excludeFields) {
    return EqualsBuilder.reflectionEquals(lhs, rhs, excludeFields);
  }

  /**
   * Helper to implement {@link Object#hashCode()}. Ignore volatile fields.
   *
   * @param object the Object to create a hashCode for
   * @return int hash code
   */
  public static int hashCode(Object object) {
    return HashCodeBuilder.reflectionHashCode(object, false);
  }

  /**
   * Helper to implement {@link Object#hashCode()}. Ignore volatile fields.
   *
   * @param object        the Object to create a hashCode for
   * @param excludeFields array of field names to exclude from use in calculation of hash code
   * @return int hash code
   */
  public static int hashCode(Object object, String... excludeFields) {
    return HashCodeBuilder.reflectionHashCode(object, excludeFields);
  }

  /**
   * Helper to implement {@link Object#hashCode()}. Ignore volatile fields.
   *
   * @param object the Object to create a toString for
   * @return builded String
   */
  public static String toString(Object object) {
    return ToStringBuilder.reflectionToString(object);
  }
}
