package dev.hugosiu.taxCalculationSystem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptPrinterTest {
  @Test
  public void shouldReturnString_GivenAllData() {
    String item1 = "pencil";
    String price1 = "$2.99";
    String qty1 = "2";

    String item2 = "shirt";
    String price2 = "$29.99";
    String qty2 = "1";

    String subtotal = "$35.97";
    String tax = "$0.55";
    String total = "$36.52";


    Product product1 = new Product(
            item1,
            Integer.parseInt(qty1),
            new BigDecimal(String.valueOf(price1.replace("$", ""))));
    Product product2 = new Product(
            item2,
            Integer.parseInt(qty2),
            new BigDecimal(String.valueOf(price2.replace("$", ""))));
    List<Product> productList = new ArrayList<>() {{
      add(product1);
      add(product2);
    }};

    BigDecimal subtotalTest = new BigDecimal(subtotal.replace("$", ""));
    BigDecimal taxTest = new BigDecimal(tax.replace("$", ""));
    BigDecimal totalTest = new BigDecimal(total.replace("$", ""));

    String expectresult =
            String.format("%-15s %15s %15s \n", "Item", "Price", "Qty")
                    + String.format("%-15s %15s %15s\n", item1, price1, qty1)
                    + String.format("%-15s %15s %15s\n", item2, price2, qty2)
                    + String.format("%-30s  %15s\n", "Subtotal:", subtotal)
                    + String.format("%-30s  %15s\n", "Tax:", tax)
                    + String.format("%-30s  %15s\n", "Total:", total);

    ReceiptPrinter printer = new ReceiptPrinter();
    assertEquals(expectresult, printer.display(productList, taxTest, subtotalTest, totalTest));
  }
}
