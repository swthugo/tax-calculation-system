package dev.hugosiu.taxCalculationSystem;

import java.math.BigDecimal;
import java.util.List;

public class Orchestrator {
  private final TaxRuleExtractor extractor;
  private final ExemptItemFilter exemptItemFilter;
  private final TaxCalculator calculator;
  private final ReceiptPrinter printer;

  public Orchestrator(
          TaxRuleExtractor extractor,
          ExemptItemFilter exemptItemFilter,
          TaxCalculator calculator,
          ReceiptPrinter printer
  ) {
    this.extractor = extractor;
    this.exemptItemFilter = exemptItemFilter;
    this.calculator = calculator;
    this.printer = printer;
  }

  public String process(String location, List<Product> orderItems) {
    List<String> exemptCategories = extractor.extractExemptCategories(location);
    List<String> exemptItems = extractor.extractExemptItems(exemptCategories);
    BigDecimal taxRates = extractor.findTaxRate(location);

    exemptItemFilter.detect(orderItems, exemptItems);

    BigDecimal taxAmount = calculator.calcTaxAmount(orderItems, taxRates);
    BigDecimal subtotal = calculator.sum(orderItems);
    BigDecimal total = calculator.sum(taxAmount, subtotal);

    return printer.display(orderItems, taxAmount, subtotal, total);
  }
}
