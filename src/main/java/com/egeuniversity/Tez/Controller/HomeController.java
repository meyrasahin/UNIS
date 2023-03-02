package com.egeuniversity.Tez.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    /*@GetMapping("/")
    public String home(){
        return "home";
    }*/

}
