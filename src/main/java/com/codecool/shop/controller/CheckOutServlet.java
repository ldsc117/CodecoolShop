package com.codecool.shop.controller;



import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.applet.Applet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="checkout", urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {
    HashMap<Product, Integer> cartList = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem allSuppliers = SupplierDaoMem.getInstance();

        HttpSession session = req.getSession();

        String removeItem = req.getParameter("removeItem");
        String addItem = req.getParameter("addItem");
        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        String add = req.getParameter("add");



        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        cartList = CartController.getListOfCart();

        if(addItem != null){
            int addItemInt = Integer.parseInt(addItem);
            Product itemName = productDataStore.find(addItemInt);
            cartList.put(itemName, cartList.get(itemName)+1);
        }

        if(removeItem != null){
            int removeItemInt = Integer.parseInt(removeItem);
            Product itemName = productDataStore.find(removeItemInt);
            if(cartList.get(itemName) == 1){
                cartList.remove(itemName);
            } else{
                cartList.put(itemName, cartList.get(itemName) - 1);
            }

        }

        CartController cart = new CartController();
        float totalPrice = cart.getTotalPrice();
        CartController.setListOfCart(cartList);

        context.setVariable("total", totalPrice);
        context.setVariable("cart", cart.getListOfCart());
        context.setVariable("cartList", cartList);
        context.setVariable("supplier", allSuppliers.getAll());
        context.setVariable("category", productCategoryDataStore.getAll());


        engine.process("product/checkout.html", context, resp.getWriter());
    }

}
