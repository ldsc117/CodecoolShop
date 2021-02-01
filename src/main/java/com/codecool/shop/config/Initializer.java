package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier nokia = new Supplier("Nokia", "Smartphones");
        supplierDataStore.add(nokia);
        Supplier hp = new Supplier("HP", "Computers");
        supplierDataStore.add(hp);
        Supplier apple = new Supplier("Apple", "Smartphones");
        supplierDataStore.add(apple);
        Supplier nvidia = new Supplier("Nvidia", "Graphic Cards");
        supplierDataStore.add(nvidia);
        Supplier asus = new Supplier("Asus", "computer and phone hardware and electronics");
        supplierDataStore.add(asus);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory smartphone = new ProductCategory("SmartPhone", "Hardware", "A smartphone is a mobile device that combines cellular and mobile computing functions into one unit.");
        productCategoryDataStore.add(smartphone);
        ProductCategory computer = new ProductCategory("Computers", "Hardware", "An electronic device that manipulates information, or data. It has the ability to store, retrieve, and process data. You may already know that you can use a computer to type documents, send email, play games, and browse the Web.");
        productCategoryDataStore.add(computer);
        ProductCategory gpu = new ProductCategory("Graphic Cards", "Hardware", "A video card (also called a graphics card, display card, graphics adapter, or display adapter) is an expansion card which generates a feed of output images to a display device (such as a computer monitor).");
        productCategoryDataStore.add(gpu);
        ProductCategory laptop = new ProductCategory("Laptops", "Hardware", "A laptop or laptop computer, is a small, portable personal computer (PC) with a \"clamshell\" form factor, typically having a thin LCD or LED computer screen mounted on the inside of the upper lid of the clamshell and an alphanumeric keyboard on the inside of the lower lid.");
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));//1
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));//2
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));//3
        productDataStore.add(new Product("Nokia  8.3", 499, "USD", "Cel mai nou smartfon ", smartphone, nokia));//4
        productDataStore.add(new Product("OMEN 30L Desktop GT13-0702ng", 1299.9f, "USD", "This will be gaming love at first sight. The OMEN 30L desktop PC has the look and technology that you want. With high processor and graphics performance, you can get started right away. And thanks to simple upgrades and the OMEN Command Center, you will have a lot of fun with this computer. ", computer, hp));//5
        productDataStore.add(new Product("Iphone 6s Plus", 299.9f, "USD", "Apple iPhone 6s Plus smartphone comes with a 5.50-inch touchscreen display with a resolution of 1080x1920 pixels at a pixel density of 401 pixels per inch (ppi).", smartphone, apple));//6
        productDataStore.add(new Product("Nvidia RTX 3080", 1599, "USD", "The GeForce 30 series is a family of graphics processing units developed by Nvidia, succeeding the GeForce 20 series.", gpu, nvidia));
        productDataStore.add(new Product("Ipad Air 2020", 699, "USD", "New Ipad launched by Apple", tablet, apple));
        productDataStore.add(new Product("Asus ZenBook 14", 699, "USD", "ZenBook are a family of ultrabooks – low-bulk laptop computers – produced by Asus. ", tablet, asus));
        productDataStore.add(new Product("Asus Tuf RTX 3080 ", 900, "USD", "GeForce 30 series custom gpu made by Asus", gpu, asus));
        productDataStore.add(new Product("Laptop IdeaPad 5 ", 9999, "USD", "The IdeaPad 5 (14\",AMD) has everything you need for a powerful combination of performance, connectivity, and entertainment on-the-go, ", laptop, lenovo));

    }
}
