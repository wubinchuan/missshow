package com.niu.service.impl;

import com.niu.model.Banner;
import com.niu.repository.BannerRepository;
import com.niu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    @Override
    public Banner getByName(String name) {
        Banner banner=bannerRepository.findBannersByName(name);
        return banner;
    }
}
