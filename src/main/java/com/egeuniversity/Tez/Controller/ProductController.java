package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Product.ProductRequestDTO;
import com.egeuniversity.Tez.Service.Product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

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
        return productList;
    }

}
