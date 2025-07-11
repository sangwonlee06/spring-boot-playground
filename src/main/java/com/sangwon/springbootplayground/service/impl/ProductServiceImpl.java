package com.sangwon.springbootplayground.service.impl;

import com.sangwon.springbootplayground.dto.request.ProductRequestDTO;
import com.sangwon.springbootplayground.dto.response.ProductResponseDTO;
import com.sangwon.springbootplayground.entity.Product;
import com.sangwon.springbootplayground.repository.ProductRepository;
import com.sangwon.springbootplayground.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return toResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return toResponseDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

        existingProduct.setName(productRequestDTO.getName());
        existingProduct.setDescription(productRequestDTO.getDescription());
        existingProduct.setPrice(productRequestDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return toResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Map Product entity to ProductResponseDTO
    private ProductResponseDTO toResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Map ProductRequestDTO to Product entity
    private Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        product.setId(null);
        return product;
    }
}
