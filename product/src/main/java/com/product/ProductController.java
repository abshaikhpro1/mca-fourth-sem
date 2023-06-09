package com.product;

import com.product.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("productDeleted")
public class ProductController {

    @Autowired
    ProductService  productService;
    private List<Product> products = new ArrayList<>();

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("productDeleted" , false);
        return "home";
    }


    @GetMapping("/products")
    public String getAllProducts(Model model) {


        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("products", allProducts);
        return "product-list";
    }


    @GetMapping("/search")
    public String searchProduct(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "search-product";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("product") Product product) {
        // Save the product to the database or perform other operations
        productService.createProduct(product);
        return "redirect:/products";
    }

    // Add other methods for updating, deleting, etc.
// Mapping for displaying the update form
    @GetMapping("/products/update")
    public String viewshowUpdateForm( Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list-view-update";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm( @PathVariable("id") Long id,Model model) {
        Product product =   productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-update";
    }


    // Mapping for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute("product") Product updatedProduct) {
        productService.updateProduct(id , updatedProduct);
        return "redirect:/products/update";
    }


    @GetMapping("/products/delete")
    public String viewshowDeleteUpdateForm( Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list-view-delete";
    }

    @GetMapping("/deleteView/{id}")
    public String showEditDeleteUpdateForm( @PathVariable("id") Long id,Model model) {
        Product product =   productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-delete";
    }



    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.deleteProduct(id);
        model.addAttribute("productDeleted" , true);
        return "redirect:/products/delete";
    }

}
