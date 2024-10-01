package dev.hugosiu.taxCalculationSystem;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
  private final String name;
  private final int qty;
  private final BigDecimal price;
  private boolean countTax;

  public Product(String name, int qty, BigDecimal price) {
    this.name = name;
    this.qty = qty;
    this.price = price;
    this.countTax = true;
  }

  public boolean isCountTax() {
    return countTax;
  }

  public void setCountTax(boolean countTax) {
    this.countTax = countTax;
  }

  public String getName() {
    return name.intern();
  }

  public int getQty() {
    return qty;
  }

  public BigDecimal getPrice() {
    return new BigDecimal(String.valueOf(price));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return qty == product.qty && countTax == product.countTax && Objects.equals(name, product.name) && Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, qty, price, countTax);
  }
}
