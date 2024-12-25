package com.niu.api.v1;

import com.niu.model.Banner;
import com.niu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @GetMapping("/name/{name}")
    public void getByName(@PathVariable @NotBlank String name){
        Banner banner=bannerService.getByName(name);
        System.out.println(banner);
    }
}
