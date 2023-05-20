package groovy

import com.product.Product
import com.product.ProductController
import com.product.ProductService
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.ui.Model
import spock.lang.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.*


class ProductControllerTest extends Specification {
    @Mock
    ProductService productService
    @Mock
    List<Product> products

    @Mock Model model;

    @InjectMocks
    ProductController productController

    def setup() {
        MockitoAnnotations.openMocks(this)
    }

    def "test home"() {
        when:
        String result = productController.home(model)

        then:
        result == "home"
    }

    def "test get All Products"() {
        given:
        when(productService.getAllProducts()).thenReturn([new Product()])

        when:
        String result = productController.getAllProducts(model)

        then:
        result == "product-list"
    }

    def "test search Product"() {
        given:
        when(productService.getAllProducts()).thenReturn([new Product()])

        when:
        String result = productController.searchProduct(model)

        then:
        result == "search-product"
    }

    def "test show Create Form"() {
        when:
        String result = productController.showCreateForm(model)

        then:
        result == "product-form"
    }

    def "test create Product"() {
        given:
        when(productService.createProduct(any())).thenReturn(new Product())

        when:
        String result = productController.createProduct(new Product())

        then:
        result == "redirect:/products"
    }

    def "test viewshow Update Form"() {
        given:
        when(productService.getAllProducts()).thenReturn([new Product()])

        when:
        String result = productController.viewshowUpdateForm(model)

        then:
        result == "product-list-view-update"
    }

    def "test show Update Form"() {
        given:
        when(productService.getProductById(anyLong())).thenReturn(new Product())

        when:
        String result = productController.showUpdateForm(1l, model)

        then:
        result == "product-update"
    }

    def "test update Product"() {
        given:
        when(productService.updateProduct(anyLong(), any())).thenReturn(new Product())

        when:
        String result = productController.updateProduct(1l, new Product())

        then:
        result == "redirect:/products/update"
    }

    def "test viewshow Delete Update Form"() {
        given:
        when(productService.getAllProducts()).thenReturn([new Product()])

        when:
        String result = productController.viewshowDeleteUpdateForm(model)

        then:
        result == "product-list-view-delete"
    }

    def "test show Edit Delete Update Form"() {
        given:
        when(productService.getProductById(anyLong())).thenReturn(new Product())

        when:
        String result = productController.showEditDeleteUpdateForm(1l, model)

        then:
        result == "product-delete"
    }

    def "test delete Product"() {
        given:
        when(productService.deleteProduct(anyLong())).thenReturn(true)

        when:
        String result = productController.deleteProduct(1l, model)

        then:
        result == "redirect:/products/delete"
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme