package dev.hugosiu.taxCalculationSystem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxRuleExtractorTest {
  @Test
  public void shouldReturnExemptCategories_GivenLocation() {
    String location = "NY";
    List<String> expectedCategories = new ArrayList<>() {{
      add("food");
      add("clothing");
    }};

    TaxRuleExtractor extractor = new TaxRuleExtractor();
    assertEquals(expectedCategories, extractor.extractExemptCategories(location));
  }

  @Test
  public void shouldReturnExemptItems_GivenExemptCategories() {
    List<String> exemptCategories = new ArrayList<>() {{
      add("food");
      add("clothing");
    }};

    List<String> expectedItems = new ArrayList<>() {{
      add("shirt");
      add("potato-chips");
    }};

    TaxRuleExtractor extractor = new TaxRuleExtractor();
    assertEquals(expectedItems, extractor.extractExemptItems(exemptCategories));
  }

  @Test
  public void shouldReturnTaxRate_GivenLocation() {
    String location = "CA";
    BigDecimal expectedRate = new BigDecimal("0.0975");

    TaxRuleExtractor extractor = new TaxRuleExtractor();
    assertEquals(expectedRate, extractor.findTaxRate(location));
  }
}
