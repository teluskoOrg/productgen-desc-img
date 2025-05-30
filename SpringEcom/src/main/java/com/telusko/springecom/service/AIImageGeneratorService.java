package com.telusko.springecom.service;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;

@Service
public class AIImageGeneratorService {

    @Autowired
    private ImageModel imageModel;

    public byte[] generateImage(String prompt) {
        try {
            // Create image generation options using OpenAI (size, quality, number of images)
            OpenAiImageOptions options = OpenAiImageOptions.builder()
                    .withN(1)                 // generate 1 image
                    .withWidth(1024)            // image width
                    .withHeight(1024)           // image height
                    .withQuality("standard")    // image quality
                    .build();

            // Send prompt to the AI model and receive the image response
            ImageResponse response = imageModel.call(new ImagePrompt(prompt, options));

            // Extract the generated image URL from the response
            String imageUrl = response.getResult().getOutput().getUrl();

            // Download the image using the URL
            try (InputStream in = new URL(imageUrl).openStream()) {
                return in.readAllBytes();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
