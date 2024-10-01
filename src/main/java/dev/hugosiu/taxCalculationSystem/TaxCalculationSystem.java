package dev.hugosiu.taxCalculationSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxCalculationSystem {
  static {
    System.out.println("TaxCalculationSystem Example");
    System.out.println("Use case 1: Input: Location: CA, 1 book at 17.99, 1 potato chips at 3.99");
    System.out.println("Use case 2: Input: Location: NY, 1 book at 17.99, 3 pencils at 2.99 ");
    System.out.println("Use case 3: Input: Location: NY, 2 pencils at 2.99, 1 shirt at 29.99 ");
    System.out.println();

    System.out.println("Available Location: CA - California, NY - New York");
    System.out.println("Available product: pencil, shirt, book, potato-chips, etc.");
    System.out.println();
  }

  public static void main(String[] args) {
    List<Product> orders = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter location: ");
    String location = sc.nextLine();

    System.out.println("Enter item details (name, price) or 'done' to finish:");
    int count = 1;
    while (true) {
      System.out.println("\nItem " + count++);

      System.out.print("Item name: ");
      String itemName = sc.nextLine();

      if (itemName.equalsIgnoreCase("done")) {
        break;
      }

      System.out.print("Item price: ");
      BigDecimal itemPrice = sc.nextBigDecimal();
      sc.nextLine();


      System.out.print("Item quantity: ");
      int itemQty = sc.nextInt();
      sc.nextLine();

      Product p = new Product(itemName, itemQty, itemPrice);
      orders.add(p);
    }

    sc.close();
    System.out.println();

    TaxRuleExtractor extractor = new TaxRuleExtractor();
    ExemptItemFilter exemptItemFilter = new ExemptItemFilter();
    TaxCalculator calculator = new TaxCalculator();
    ReceiptPrinter printer = new ReceiptPrinter();

    Orchestrator orchestrator = new Orchestrator(
            extractor,
            exemptItemFilter,
            calculator,
            printer
    );

    System.out.println(orchestrator.process(location, orders));
  }
}
