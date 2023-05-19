package com.product;

import com.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {


    @Autowired
    ProductService  productService;
    private List<Product> products = new ArrayList<>();


    @GetMapping("/home")
    public String home() {
        return "home";
    }


    @GetMapping("/products")
    public String getAllProducts(Model model) {


        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("products", allProducts);
        return "product-list";
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
        return "redirect:/products";
    }


    @GetMapping("/products/delete")
    public String viewshowDeleteUpdateForm( Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list-view-delete.html";
    }

    @GetMapping("/deleteView/{id}")
    public String showEditDeleteUpdateForm( @PathVariable("id") Long id,Model model) {
        Product product =   productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-delete";
    }

    // Mapping for deleting a product
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/delete";
    }

}
