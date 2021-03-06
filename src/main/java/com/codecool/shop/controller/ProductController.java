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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    HashMap<Product , Integer> cartList = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDaoMem allSuppliers = SupplierDaoMem.getInstance();



        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        String add = req.getParameter("add");
        String removeItem = req.getParameter("removeItem");
        String addItem = req.getParameter("addItem");




        if(add!=null){
            int addInt = Integer.parseInt(add);
            Product itemName = productDataStore.find(addInt);
            if(cartList.containsKey(itemName)){
                cartList.put(itemName,cartList.get(itemName) + 1);
            }
            else {
                cartList.put(itemName,1);
            }

        }

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



        float totalPrice = 0;
        for(Map.Entry<Product,Integer> entry:cartList.entrySet()){
            totalPrice += entry.getValue()*entry.getKey().getDefaultPrice();

        }

        CartController cart  =  new CartController();
        cart.setListOfCart(cartList);





        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (category == null && supplier == null) {
            context.setVariable("products", productDataStore.getAll());

        } else if(category !=null){
            int categoryId=Integer.parseInt(category);
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        } else{
            int supplierId = Integer.parseInt(supplier);
            context.setVariable("products", productDataStore.getBy(allSuppliers.find(supplierId)));
        }

        context.setVariable("totalPrice", totalPrice);
        context.setVariable("cartList", cartList);
        context.setVariable("cart", cart.getListOfCart());
        context.setVariable("supplier", allSuppliers.getAll());
        context.setVariable("category", productCategoryDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

}


