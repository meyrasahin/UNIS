package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Product.Category.CategoryRequestDTO;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.ProductRequestDTO;
import com.egeuniversity.Tez.Service.Product.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController implements Serializable {
    private final ProductService productService;

    List<Product> products;

    @PostMapping(path = "/addProduct")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        productService.addProduct(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    @ResponseBody
    public Product getById(@RequestParam Integer id){
        Product product = productService.getProduct(id);
        return product;
    }

    @GetMapping("/listByUni")
    @ResponseBody
    public List<Product> listByUni(@RequestParam Integer id){
        List<Product> productList = productService.listProductsByUniversity(id);
        products = productList;
        return null;
    }

    @DeleteMapping(path= "/delete")
    public ResponseEntity<Void> deleteProduct(@RequestBody Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/addCategory")
    public ResponseEntity<Void> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        productService.addCategory(categoryRequestDTO);
        return ResponseEntity.ok().build();
    }
}
