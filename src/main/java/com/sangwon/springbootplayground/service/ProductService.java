package com.sangwon.springbootplayground.service;

import com.sangwon.springbootplayground.dto.request.ProductRequestDTO;
import com.sangwon.springbootplayground.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProductById(Long id);
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long id);

}
