package com.spenditure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.ArrayList;

public class ReceiptParser {
    public Receipt parseReceipt(String text) {
        List<Item> items = new ArrayList<>();
    
        // Parse items
        Pattern itemPattern = Pattern.compile("(.*?)(\\d+\\.\\d{2})", Pattern.CASE_INSENSITIVE);
        Matcher itemMatcher = itemPattern.matcher(text);
        while (itemMatcher.find()) {
            String item = itemMatcher.group(1).trim();
            String price = itemMatcher.group(2);

            // Skip unwanted items
            if (item.equals("SUBTDTRL") || item.equals("THX1") || item.equals("0 X") || item.equals("Tax") || item.equals("") || item.equals("Total")|| item.equals("TOTHL") || item.equals( "DEBIT TEND") || item.equals( "CHHNEE DUE") || item.equals( "Remarks / Instructions:   SUBTOTAL") || item.equals("THANK YOU TOTAL")){
                continue;
            }

            String currency = "CAD"; // Set default currency as CAD

            // Create a new Item object and add it to the list
            Item newItem = new Item(item, Double.parseDouble(price), currency, "Unknown");
            items.add(newItem);
        }
    
        // Create a new Receipt object with the list of items
        Receipt receipt = new Receipt(items);
    
        // Parse total
        Pattern totalPattern = Pattern.compile("(TOTAL|TOTHL)\\s*(\\d+\\.\\d{2})", Pattern.CASE_INSENSITIVE);
        Matcher totalMatcher = totalPattern.matcher(text);
        if (totalMatcher.find()) {
            String total = totalMatcher.group(2); // get the second group, which is the total price
            receipt.setTotal(Double.parseDouble(total));
        }
    
        return receipt;
    }
}
