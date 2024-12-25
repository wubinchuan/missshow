package com.niu.service;

import com.niu.model.Banner;
import org.springframework.stereotype.Service;


public interface BannerService {
    Banner getByName(String name);
}
