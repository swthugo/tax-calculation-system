package dev.hugosiu.taxCalculationSystem;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptPrinter {
  public String display(List<Product> products,
                        BigDecimal taxAmount,
                        BigDecimal subtotal,
                        BigDecimal total) {
    StringBuilder output = new StringBuilder(String.format("%-15s %15s %15s \n", "Item", "Price", "Qty"));

    for (Product p : products) {
      output.append(String.format("%-15s %15s %15s\n", p.getName(), "$" + p.getPrice(), p.getQty()));
    }

    output.append(String.format("%-30s  %15s\n", "Subtotal:", "$" + subtotal))
            .append(String.format("%-30s  %15s\n", "Tax:", "$" + taxAmount))
            .append(String.format("%-30s  %15s\n", "Total:", "$" + total));
    return output.toString();
  }
}
