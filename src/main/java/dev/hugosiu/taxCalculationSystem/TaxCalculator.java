package dev.hugosiu.taxCalculationSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TaxCalculator {
  public BigDecimal calcTaxAmount(List<Product> products, BigDecimal rate) {
    BigDecimal sum = BigDecimal.ZERO;

    for (Product p : products) {
      if (p.isCountTax()) {
        BigDecimal tax = (p.getPrice().multiply(BigDecimal.valueOf(p.getQty())).multiply(rate))
                .multiply(BigDecimal.valueOf(20))
                .setScale(0, RoundingMode.CEILING)
                .divide(BigDecimal.valueOf(20));
        sum = sum.add(tax);
      }
    }

    return sum.setScale(2, RoundingMode.HALF_UP);
  }

  public BigDecimal sum(List<Product> products) {
    BigDecimal sum = BigDecimal.ZERO;

    for (Product p : products) {
      sum = sum.add(p.getPrice().multiply(BigDecimal.valueOf(p.getQty())));
    }

    return sum;
  }

  public BigDecimal sum(BigDecimal taxAmount, BigDecimal subtotal) {
    return taxAmount.add(subtotal);
  }
}
