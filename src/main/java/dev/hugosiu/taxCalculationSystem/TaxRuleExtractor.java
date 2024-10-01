package dev.hugosiu.taxCalculationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaxRuleExtractor {
  public List<String> extractExemptCategories(String location) {
    List<String> categories = new ArrayList<>();

    Path path = Paths.get("src/main/java/resources/exempt-items.csv");

    try (BufferedReader br = Files.newBufferedReader(path)) {
      String[] cols;
      String line;
      while ((line = br.readLine()) != null) {
        cols = line.split(",");
        if (cols[0].equals(location)) {
          categories.add(cols[1]);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return categories;
  }

  public List<String> extractExemptItems(List<String> categories) {
    List<String> items = new ArrayList<>();

    Path path = Paths.get("src/main/java/resources/product-categories.csv");

    try (BufferedReader br = Files.newBufferedReader(path)) {
      String[] cols;
      String line;
      while ((line = br.readLine()) != null) {
        cols = line.split(",");
        if (categories.contains(cols[0])) {
          items.add(cols[1]);
        }
      }
    } catch (IOException err) {
      throw new RuntimeException(err.getMessage());
    }

    return items;
  }

  public BigDecimal findTaxRate(String location) {
    BigDecimal rate = BigDecimal.ZERO;

    Path path = Paths.get("src/main/java/resources/tax-rates.csv");

    try (BufferedReader br = Files.newBufferedReader(path)) {
      String[] cols;
      String line;
      while ((line = br.readLine()) != null) {
        cols = line.split(",");
        if (cols[0].equals(location)) {
          rate = BigDecimal.valueOf(Double.parseDouble(cols[1]));
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return rate;
  }
}
