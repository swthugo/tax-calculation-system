package dev.hugosiu.taxCalculationSystem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExemptItemFilterTest {
  @Test
  public void shouldReturnCorrectProductList_GivenExemptList() {
    ExemptItemFilter filter = new ExemptItemFilter();

    Product productA = new Product("book", 1, new BigDecimal("17.99"));
    Product productB = new Product("potato-chips", 1, new BigDecimal("3.9"));
    List<Product> productLists = new LinkedList<>() {{
      add(productA);
      add(productB);
    }};

    List<String> exemptItems = new ArrayList<>() {{
      add("book");
      add("candy");
      add("water");
    }};

    filter.detect(productLists, exemptItems);


    Product expectedProductA = new Product("book", 1, new BigDecimal("17.99"));
    Product expectedProductB = new Product("potato-chips", 1, new BigDecimal("3.9"));
    List<Product> expectedProductLists = new LinkedList<>() {{
      add(expectedProductA);
      add(expectedProductB);
    }};
    expectedProductA.setCountTax(false);

    assertEquals(expectedProductLists, productLists);
  }
}
