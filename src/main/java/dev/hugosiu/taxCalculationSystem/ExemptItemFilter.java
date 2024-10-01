package dev.hugosiu.taxCalculationSystem;

import java.util.List;

public class ExemptItemFilter {
  public void detect(List<Product> orderItems, List<String> exemptItems) {
    for (Product item : orderItems) {
      if (exemptItems.contains(item.getName())) {
        item.setCountTax(false);
      }
    }
  }
}
