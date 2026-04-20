package com.amazon_lite.product.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.amazon_lite.product.dto.ProductRequestDTO;
import com.amazon_lite.product.dto.ProductResponseDTO;
import com.amazon_lite.product.entity.Product;
import com.amazon_lite.product.mapper.ProductMapper;
import com.amazon_lite.product.repository.ProductRepository;
import com.amazon_lite.user.entity.User;
import com.amazon_lite.user.repository.UserRepository;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final UserRepository userRepo;

    public ProductService(ProductRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @CacheEvict(value = { "products", "productById" }, allEntries = true)
    public ProductResponseDTO create(ProductRequestDTO dto) {

        User owner = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = ProductMapper.toEntity(dto);
        product.setOwner(owner);

        return ProductMapper.toDTO(repo.save(product));
    }

    @Cacheable(value = "products", key = "'all'")
    public List<ProductResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @Cacheable(value = "productById", key = "#id")
    public ProductResponseDTO getById(Long id) {
        Product p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDTO(p);
    }

    @CacheEvict(value = { "products", "productById" }, allEntries = true)
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
