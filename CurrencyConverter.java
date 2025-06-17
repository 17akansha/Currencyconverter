import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    // Simulated exchange rates (base: USD)
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    private static final Map<String, String> currencySymbols = new HashMap<>();

    static {
        // Simulated exchange rates (as of some date)
        exchangeRates.put("USD", 1.0);  // Base currency
        exchangeRates.put("EUR", 0.85); // 1 USD = 0.85 EUR
        exchangeRates.put("INR", 83.0); // 1 USD = 83 INR
        exchangeRates.put("GBP", 0.73); // 1 USD = 0.73 GBP

        // Currency symbols
        currencySymbols.put("USD", "$");
        currencySymbols.put("EUR", "€");
        currencySymbols.put("INR", "₹");
        currencySymbols.put("GBP", "£");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display available currencies
        System.out.println("Available currencies: " + exchangeRates.keySet());
        
        // Get base currency
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        if (!exchangeRates.containsKey(baseCurrency)) {
            System.out.println("Invalid base currency!");
            return;
        }

        // Get target currency
        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();
        if (!exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid target currency!");
            return;
        }

        // Get amount to convert
        System.out.print("Enter amount to convert: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount < 0) {
                System.out.println("Amount cannot be negative!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered!");
            return;
        }

        // Perform conversion
        double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);
        String targetSymbol = currencySymbols.get(targetCurrency);

        // Display result
        System.out.printf("%.2f %s = %.2f %s%n", amount, currencySymbols.get(baseCurrency), convertedAmount, targetSymbol);

        scanner.close();
    }

    private static double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        // Convert amount to USD first (if base is not USD)
        double amountInUSD = amount / exchangeRates.get(baseCurrency);
        // Convert from USD to target currency
        return amountInUSD * exchangeRates.get(targetCurrency);
    }
}
