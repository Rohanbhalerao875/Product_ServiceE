package com.example.productservice1.Services;

import com.example.productservice1.Models.Category;
import com.example.productservice1.Models.Product;
import com.example.productservice1.dtos.FakeStoreProductDto;
import com.example.productservice1.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service

public class FakeStoreProductService implements ProductService {


    RestTemplate restTemplate ;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtoList = restTemplate.getForObject("https://fakestoreapi.com/products/",FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto1 : fakeStoreProductDtoList){
            productList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto1));
        }
        return productList;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(100L,"Product not found for id "+id);
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }



    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDesc());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());


        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto,FakeStoreProductDto.class);
        fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor).getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }



    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDesc(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        // Convert category if needed
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
