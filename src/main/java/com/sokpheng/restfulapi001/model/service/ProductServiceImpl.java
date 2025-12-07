package com.sokpheng.restfulapi001.model.service;

import com.sokpheng.restfulapi001.mapper.ProductMapstruct;
import com.sokpheng.restfulapi001.model.dto.CreateProductDto;
import com.sokpheng.restfulapi001.model.dto.ResponseProductDto;
import com.sokpheng.restfulapi001.model.dto.UpdateProductDto;
import com.sokpheng.restfulapi001.model.entities.Category;
import com.sokpheng.restfulapi001.model.entities.Product;
import com.sokpheng.restfulapi001.model.repository.CategoryRepository;
import com.sokpheng.restfulapi001.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapstruct productMapstruct;
    @Override
    public ResponseProductDto createNewProduct(CreateProductDto createProductDto) {
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setProductName(createProductDto.productName());
        product.setCreatedDate(Date.from(Instant.now()));
        // convert from LocalDate to Date
        Date expireDate = Date.from(createProductDto.expireDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        product.setExpireDate(expireDate);
        product.setIsDeleted(false);
        // get all categories from client to add into the product
        Set<Category> categories = new HashSet<>();
        for(String categoryName: createProductDto.categoriesNames()){
            // search for the product category inside the database
            Category category = categoryRepository
                    .findCategoryByCategoryName(categoryName).orElseThrow();
            // check the category is deleted or not before adding to the Product
            if(category.getIsDeleted().equals(true)){
                throw new RuntimeException("Category Drink does exist");
            }
            categories.add(category);
        }
        product.getCategories().addAll(categories);
        productRepository.save(product);
        return productMapstruct
                .mapFromProductToResponseProductDto(product);
    }

    @Override
    public Page<ResponseProductDto> getAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseProductDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto) {
        return null;
    }

    @Override
    public String deleteProductByUuid(String uuid) {
        return "";
    }
}
