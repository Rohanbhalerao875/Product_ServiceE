package com.example.productservice1.Controllers;

import com.example.productservice1.Model.Product;
import com.example.productservice1.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Test class to add 50 products to the database.
 * Run this test manually when you need to populate the database.
 */
@SpringBootTest
@ActiveProfiles("test") // Optional: use if you have a test profile
public class ProductDataTest {

    @Autowired
    @Qualifier("selfProductService")
    private ProductService productService;

    private final Random random = new Random();

    // Sample data for generating product information
    private final List<String> productCategories = Arrays.asList(
            "Electronics", "Clothing", "Books", "Home & Kitchen", "Sports",
            "Beauty", "Toys", "Food", "Furniture", "Office Supplies"
    );

    private final List<String> productTitlePrefixes = Arrays.asList(
            "Premium", "Deluxe", "Essential", "Advanced", "Basic",
            "Professional", "Ultimate", "Classic", "Modern", "Compact"
    );

    private final List<String> productItems = Arrays.asList(
            "Laptop", "Smartphone", "Headphones", "T-shirt", "Jeans",
            "Novel", "Cookbook", "Coffee Maker", "Blender", "Basketball",
            "Tennis Racket", "Face Cream", "Shampoo", "Action Figure", "Doll",
            "Chocolate", "Snacks", "Sofa", "Chair", "Desk", "Pen Set"
    );

    @Test
    public void addFiftyProducts() {
        System.out.println("Adding 50 products to database...");

        for (int i = 1; i <= 50; i++) {
            Product product = createSampleProduct(i);
            productService.createProduct(product);
            System.out.println("Added product #" + i + ": " + product.getTitle());
        }

        System.out.println("Successfully added 50 products!");
    }

    private Product createSampleProduct(int index) {
        Product product = new Product();

        // Select random elements for product title
        String prefix = productTitlePrefixes.get(random.nextInt(productTitlePrefixes.size()));
        String item = productItems.get(random.nextInt(productItems.size()));
        String category = productCategories.get(random.nextInt(productCategories.size()));

        // Set product properties
        product.setTitle(prefix + " " + item + " " + index);
        product.setDescription("This is a high-quality " + item.toLowerCase() +
                " perfect for everyday use. Product #" + index);

        // Generate random price between $10 and $1000
        double price = 10 + (random.nextDouble() * 990);
        product.setPrice(Math.round(price * 100.0) / 100.0); // Round to 2 decimal places


        // Set quantity between 1 and 100
        product.setQuantity(random.nextInt(100) + 1);

        return product;
    }
}