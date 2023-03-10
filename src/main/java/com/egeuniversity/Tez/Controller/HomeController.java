package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Product.Category.Category;
import com.egeuniversity.Tez.Service.Product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController implements Serializable {

    private final ProductService productService;

    @GetMapping(path = "/categories")
    public List<Category> getCategories() {
        return productService.getCategories();
    }


}
