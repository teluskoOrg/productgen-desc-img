package com.telusko.springecom.service;

import com.telusko.springecom.model.Product;
import com.telusko.springecom.repo.ProductRepo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private AIImageGeneratorService aiImageGeneratorService;

    @Autowired
    private ChatClient chatClient;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
       if(image!=null && !image.isEmpty()) {
           product.setImageName(image.getOriginalFilename());
           product.setImageType(image.getContentType());
           product.setProductImage(image.getBytes());
       }

        return productRepo.save(product);
    }

    public String generateDescription(String name,String category){
        String descPrompt = String.format("""
            Write a concise and professional product description for an e-commerce listing.

            Product Name: %s
            Category: %s

            Keep it simple, engaging, and highlight its primary features or benefits.
            Avoid technical jargon and keep it customer-friendly.
            """, name, category);

        // Call AI chat model to generate product description
        return Objects.requireNonNull(chatClient.prompt(descPrompt)
                        .call()
                        .chatResponse())
                .getResult()
                .getOutput()
                .getText();

    }

    public byte[] generateImage(String name,String category,String description) {
        String imagePrompt = String.format("""
                        Generate a highly realistic, professional-grade e-commerce product image.

                        Product Details:
                        - Category: %s
                        - Name: '%s'
                        - Description: %s

                        Requirements:
                          - Use a clean, minimalistic, white or very light grey background.
                          - Ensure the product is well-lit with soft, natural-looking lighting.
                          - Add realistic shadows and soft reflections to ground the product naturally.
                          - No humans, brand logos, watermarks, or text overlays should be visible.
                          - Showcase the product from its most flattering angle that highlights key features.
                          - Ensure the product occupies a prominent position in the frame, centered or slightly off-centered.
                          - Maintain a high resolution and sharpness, ensuring all textures, colors, and details are clear.
                          - Follow the typical visual style of top e-commerce websites like Amazon, Flipkart, or Shopify.
                          - Make the product appear life-like and professionally photographed in a studio setup.
                          - The final image should look immediately ready for use on an e-commerce website without further editing.
                        """, category, name, description);

        // Call AI image model to generate product image
        byte[] aiImage = aiImageGeneratorService.generateImage(imagePrompt);

        return aiImage;
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
