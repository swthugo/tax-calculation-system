package dev.hugosiu.taxCalculationSystem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorTest {

  @Test
  public void shouldReturnCorrectValue_GivenTwoBigDecimal() {
    TaxCalculator calculator = new TaxCalculator();

    BigDecimal a = new BigDecimal("2.99");
    BigDecimal b = new BigDecimal("1.49");

    BigDecimal expectAnswer = new BigDecimal("4.48");
    assertEquals(expectAnswer, calculator.sum(a, b));
  }

  @Test
  public void shouldReturnTotalOfProduct_GivenListOfProduct() {
    TaxCalculator calculator = new TaxCalculator();

    Product product1 = new Product("book", 1, new BigDecimal("17.99"));
    Product product2 = new Product("potato-chips", 1, new BigDecimal("3.9"));
    Product product3 = new Product("pencil", 2, new BigDecimal("2.99"));
    product2.setCountTax(false);

    List<Product> productlist = new LinkedList<>() {{
      add(product1);
      add(product2);
      add(product3);
    }};

    BigDecimal expectedOutput = new BigDecimal("27.87");
    assertEquals(expectedOutput, calculator.sum(productlist));
  }

  @Test
  public void shouldReturnTotalOfProduct_GivenListOfProductAndTaxRate() {
    TaxCalculator calculator = new TaxCalculator();

    BigDecimal taxRate = new BigDecimal("0.0975");
    Product product1 = new Product("book", 1, new BigDecimal("17.99"));
    Product product2 = new Product("potato-chips", 1, new BigDecimal("3.9"));
    Product product3 = new Product("pencil", 1, new BigDecimal("2.99"));
    Product product4 = new Product("shirt", 1, new BigDecimal("29.99"));
    product1.setCountTax(false);
    product2.setCountTax(false);

    List<Product> productlist = new LinkedList<>() {{
      add(product1);
      add(product2);
      add(product3);
      add(product4);
    }};

    BigDecimal expectedOutput = new BigDecimal("3.25");
    assertEquals(expectedOutput, calculator.calcTaxAmount(productlist, taxRate));


    BigDecimal taxRateTest = new BigDecimal("0.01");
    List<Product> productionTest = new LinkedList<>() {{
      add(new Product("book", 1, new BigDecimal("12.34")));
    }};

    BigDecimal expectedRoundUp = new BigDecimal("0.15");
    assertEquals(expectedRoundUp, calculator.calcTaxAmount(productionTest, taxRateTest));
  }
}
