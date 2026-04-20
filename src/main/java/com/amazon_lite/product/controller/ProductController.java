package com.amazon_lite.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon_lite.product.dto.ProductRequestDTO;
import com.amazon_lite.product.dto.ProductResponseDTO;
import com.amazon_lite.product.service.ProductService;

@RestController()
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ProductResponseDTO create(@RequestBody ProductRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
