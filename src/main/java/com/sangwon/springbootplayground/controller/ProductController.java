package com.sangwon.springbootplayground.controller;

import com.sangwon.springbootplayground.dto.request.ProductRequestDTO;
import com.sangwon.springbootplayground.dto.response.ProductResponseDTO;
import com.sangwon.springbootplayground.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductRequestDTO request
    ) {
        ProductResponseDTO created = productService.createProduct(request);
        // Return 201 Created with Location header
        return ResponseEntity
                .created(URI.create("/api/products/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> all = productService.getAllProducts();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable Long id
    ) {
        ProductResponseDTO dto = productService.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO request
    ) {
        ProductResponseDTO updated = productService.updateProduct(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
