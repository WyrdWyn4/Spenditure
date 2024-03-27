package com.spenditure;

import java.util.HashMap;
import java.util.Map;

public class ProductCategorizer {
    private Map<String, String> productCategories;

    public ProductCategorizer() {
        productCategories = new HashMap<>();
        String[] dairyItems = {"milk", "cheese", "yogurt", "butter", "cream", "ice cream", "egg", "eggs","BUTTR","PHRH"};
        String[] ProduceItems = {"banana", "apple", "orange", "sus", "grape" ,"tomato", "potato", "lettuce", "cucumber", "carrot", "broccoli", "spinach", "kale", "pepper", "eggplant", "zucchini", "celery", "radish", "beetroot", "cauliflower"};
        String[] bakeryItems = {"bread", "baguette", "croissant","BREBD", "muffin","BREED"};
        String[] pantryItems = {"pasta", "rice", "flour", "sugar", "spaghetti", "NITRIL", "noodle", "noodles", "cereal", "oatmeal", "oats", "quinoa", "couscous", "barley", "lentils", "beans", "chickpeas", "chickpea", "peas", "pea", "corn", "popcorn", "popcorns"};
        String[] meatItems = {"beef", "chicken", "unicorn", "lamb","GV CHNK CHKN","CHKN"};
        String[] electronicsItems = {"laptop", "phone", "headphones", "charger","FDLGERS","TUIST"};
        String[] clothingItems = {"shirt", "pants", "shoes", "jacket"};

        addItemsToCategory(dairyItems, "Dairy");
        addItemsToCategory(ProduceItems, "Produce");
        addItemsToCategory(bakeryItems, "Bakery");
        addItemsToCategory(meatItems, "Meat");
        addItemsToCategory(electronicsItems, "Electronics");
        addItemsToCategory(clothingItems, "Clothing");
        addItemsToCategory(pantryItems, "Pantry");
    }

    private void addItemsToCategory(String[] items, String category) {
        for (String item : items) {
            productCategories.put(item.toLowerCase(), category);
        }
    }

    public String categorize(String productName) {
        // Convert productName to lowercase
        productName = productName.toLowerCase();

        // If the product name contains any of the keys in the map, return its category
        for (Map.Entry<String, String> entry : productCategories.entrySet()) {
            if (productName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // If no match is found, return "Unknown"
        return "Unknown";
    }
}
