package dev.hugosiu.taxCalculationSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class OrchestratorTest {
  @Mock
  private TaxRuleExtractor extractor;
  @Mock
  private ExemptItemFilter exemptItemFilter;
  @Mock
  private TaxCalculator calculator;
  @Mock
  private ReceiptPrinter printer;
  @InjectMocks
  private Orchestrator orchestrator;

  @Test
  public void shouldReturnResult_GrivenValidInput() {
    // Input  = "CA, 1 book at 17.99, 1 potato chips at 3.9";
    String location = "CA";

    Product productA = new Product("book", 1, new BigDecimal("17.99"));
    Product productB = new Product("potato-chips", 1, new BigDecimal("3.9"));
    List<Product> productLists = new LinkedList<>() {{
      add(productA);
      add(productB);
    }};

    List<String> exemptCategories = new LinkedList<>();
    exemptCategories.add("Food");
    List<String> exemptItems = new LinkedList<>();
    exemptItems.add("potato-chips");
    BigDecimal taxRates = new BigDecimal("0.0975");

    when(extractor.extractExemptCategories(location)).thenReturn(exemptCategories);
    when(extractor.extractExemptItems(exemptCategories)).thenReturn(exemptItems);
    when(extractor.findTaxRate(location)).thenReturn(taxRates);

    productB.setCountTax(false);

    BigDecimal taxAmount = new BigDecimal("1.80");
    BigDecimal subtotal = new BigDecimal("21.98");
    BigDecimal total = new BigDecimal("23.78");

    when(calculator.calcTaxAmount(productLists, taxRates)).thenReturn(taxAmount);
    when(calculator.sum(productLists)).thenReturn(subtotal);
    when(calculator.sum(taxAmount, subtotal)).thenReturn(total);

    String result = String.format("item price qty\nbook $17.99 1\npotato chips $3.99 1\nsubtotal: $21.98\ntax: $1.80\nnull");

    when(printer.display(productLists, taxAmount, subtotal, total)).thenReturn(result);

    String output = orchestrator.process(location, productLists);

    assertEquals(result, output);
  }
}
