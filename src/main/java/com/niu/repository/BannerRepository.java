package com.niu.repository;

import com.niu.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner,Long> {
    Banner findOneById(Long id);
    Banner findBannersByName(String name);
}
