# 🛒 AI-Powered E-Commerce Product Management System

## 📘 Executive Summary

The **AI-Powered E-Commerce Product Management System** is a robust and scalable full-stack backend solution built with **Spring Boot 3.3.3** and **Java 21**, designed to support modern online retail operations. It features a powerful REST API to manage product data and digital assets with seamless AI integration for **automatic product description generation** and **product image generation** using **Spring AI** and **OpenAI GPT-4o**. This backend integrates effortlessly with a **React.js frontend**, making it ideal for high-performance, intelligent e-commerce platforms.

---

## 📑 Table of Contents

* [Project Objective](#project-objective)
* [Core Features](#core-features)
* [Technologies Used](#technologies-used)
* [Dependencies](#dependencies)
* [System Architecture](#system-architecture)
* [API Specifications](#api-specifications)
* [AI Integrations](#ai-integrations)
* [Installation Guide](#installation-guide)
* [Folder Structure (Backend)](#folder-structure-backend)

---

## 🎯 Project Objective

To develop a performant, intelligent, and extensible product management backend for e-commerce platforms that automates content creation and asset management using advanced AI technologies.

---

## 🔧 Core Features

### ✅ User/Product Module

* Create, update, and delete product entries
* View product details including images
* Upload product images manually or generate them using AI
* Autogenerate product descriptions using GPT-4o
* Keyword-based product search

### 🔄 Functional Features

* Complete product lifecycle management
* Cloudinary-based image storage
* Secure REST API endpoints with full CORS support for frontend integration
* AI-enhanced product listing experience

---

## 💡 Technologies Used

| Component      | Technology                      |
| -------------- | ------------------------------- |
| Backend        | Spring Boot 3.3.3               |
| ORM            | Hibernate, Spring Data JPA      |
| Database       | PostgreSQL                      |
| AI Integration | Spring AI, OpenAI GPT-4o        |
| Image Storage  | Cloudinary                      |
| Frontend       | React.js (optional)             |
| Tools          | IntelliJ IDEA, VS Code, Postman |
| Runtime        | Java 21                         |
| Build Tool     | Apache Maven                    |

---

## 📦 Dependencies

| Group ID                 | Artifact                       | Scope    |
| ------------------------ | ------------------------------ | -------- |
| org.springframework.boot | spring-boot-starter-web        | compile  |
| org.springframework.boot | spring-boot-starter-data-jpa   | compile  |
| org.springframework.boot | spring-boot-starter-test       | test     |
| org.postgresql           | postgresql                     | runtime  |
| org.projectlombok        | lombok                         | optional |
| org.springframework.ai   | spring-ai-starter-model-openai | compile  |
| com.cloudinary           | cloudinary-http44              | compile  |

---

## 🧱 System Architecture

The application follows a **monolithic, layered architecture**:

* **Controller Layer** – Exposes REST endpoints
* **Service Layer** – Handles business logic and AI service integration
* **Repository Layer** – Manages database interaction via Spring Data JPA
* **Entity Layer** – Java classes mapped to PostgreSQL tables

---

## 🔗 API Specifications

| Endpoint                                | Method | Description                    | Status Codes |
| --------------------------------------- | ------ | ------------------------------ | ------------ |
| `/api/products`                         | GET    | Retrieve all products          | 200          |
| `/api/product/{id}`                     | GET    | Get product by ID              | 200, 404     |
| `/api/product/{productId}/image`        | GET    | Retrieve product image         | 200, 404     |
| `/api/product`                          | POST   | Add a new product (AI support) | 201, 500     |
| `/api/product/{id}`                     | PUT    | Update existing product        | 200, 500     |
| `/api/product/{id}`                     | DELETE | Delete product by ID           | 200, 404     |
| `/api/products/search?keyword=yourterm` | GET    | Search products by keyword     | 200          |

---

## 🤖 AI Integrations

### 🔍 Product Description (Spring AI + GPT-4o)

Automatically generate rich, customer-friendly product descriptions when missing using `ChatClient` from Spring AI.

### 🖼️ Product Image (Spring AI + OpenAI DALL·E + Cloudinary)

If no image is provided, the system generates a realistic image using OpenAI and uploads it to Cloudinary.

---

## 🛠 Installation Guide

### 📌 Backend Setup with IntelliJ IDEA

1. Clone the repository
2. Open project in IntelliJ IDEA
3. Ensure Java 21 is installed
4. Configure PostgreSQL and update `application.properties`
5. Run `SpringEcomApplication`

---

## 📂 Folder Structure (Backend)

```
com.telusko.springecom
├── config               # Configuration for Cloudinary & ChatClient
├── controller           # REST API Controllers
├── model                # Entity classes (Product.java)
├── repo                 # Spring Data JPA Repositories
├── service              # Business logic, AI integration, image upload
└── SpringEcomApplication.java # Main entry point
```

---

## ✅ Environment Configuration (`application.properties`)

```properties
# OpenAI Configuration
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o

# Cloudinary Configuration
cloudinary.cloud_name=${CLOUD_NAME}
cloudinary.api_key=${CLOUDINARY_API_KEY}
cloudinary.api_secret=${CLOUDINARY_SECRET_KEY}
```

---

## 👨‍💻 Author

Developed and maintained by **Telusko Team**.

---

> ⚙️ **Scalable. Intelligent. AI-Powered.** Ready for the next-gen E-Commerce experience.
