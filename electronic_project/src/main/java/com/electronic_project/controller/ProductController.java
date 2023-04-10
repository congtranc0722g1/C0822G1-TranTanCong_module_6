package com.electronic_project.controller;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.dto.cart.UpdateQuantityCartDto;
import com.electronic_project.dto.product.IRevenueProductDto;
import com.electronic_project.dto.product.ProductDto;
import com.electronic_project.model.product.Product;
import com.electronic_project.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/latest-product")
    private ResponseEntity<List<Product>> showLatestProductList(){
        List<Product> latestProductList = productService.showLatestProductList();
        return new ResponseEntity<>(latestProductList, HttpStatus.OK);
    }

    @GetMapping("/list-product")
    private ResponseEntity<Page<Product>> showSaleProductList(@RequestParam(value = "categoryId") Integer categoryId,
                                                              @RequestParam(value = "trademarkId") Integer trademarkId,
                                                              @RequestParam(value = "name", defaultValue = "") String name,
                                                              @PageableDefault(size = 9)Pageable pageable){

        Page<Product> productList = null;

        if (categoryId == -1 && trademarkId == -1){
            productList = productService.showAll(name, pageable);
        }

        if (categoryId != -1 && trademarkId != -1){
            productList = productService.getAllProduct(categoryId, trademarkId, name, pageable);
        }

        if (categoryId == -1 && trademarkId != -1){
            productList = productService.getTrademarkProduct(trademarkId, name, pageable);
        }

        if (categoryId != -1 && trademarkId == -1){
            productList = productService.getCategoryProduct(categoryId, name, pageable);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> findProduct(@PathVariable("id") Integer id){
        Product product = productService.findProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        productDto.setCreateDay(formattedDateTime);
        productDto.setPrice(productDto.getEntryPrice() + productDto.getEntryPrice() * 0.2);
        productDto.setFlagDelete(true);
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);

        productService.addProduct(product.getCode(), product.getName(), product.getCreateDay(), product.getDescription(),
                product.getPrice(), product.getEntryPrice(), product.getQuantity(), product.getFlagDelete(),
                product.getCategory().getId(), product.getTrademark().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count-product")
    private ResponseEntity<Integer> countProduct(){
        Integer countProduct = productService.countProduct();
        return new ResponseEntity<>(countProduct, HttpStatus.OK);
    }

    @PutMapping("/update-quantity-product")
    private ResponseEntity<?> updateQuantity(@RequestBody List<UpdateQuantityCartDto> updateQuantityCartDtoList){
        List<Product> productList = productService.showAll();
        for (int i = 0; i < updateQuantityCartDtoList.size(); i++) {
            for (int j = 0; j < productList.size(); j++) {
                if (updateQuantityCartDtoList.get(i).getId() == productList.get(j).getId()){
                    productService.updateQuantityProduct(productList.get(j).getQuantity() - updateQuantityCartDtoList.get(i).getQuantity(), productList.get(j).getId());
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/product-by-category")
    private ResponseEntity<List<Product>> findProductByCategory(@RequestParam("categoryId") Integer categoryId){
        List<Product> productList = productService.findProductByCategory(categoryId);
        if (productList.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/revenue-product")
    private ResponseEntity<List<IRevenueProductDto>> showRevenueProduct(){
        List<IRevenueProductDto> revenueProductDtoList = productService.showRevenueProduct();
        return new ResponseEntity<>(revenueProductDtoList, HttpStatus.OK);
    }

    @GetMapping("/list-product-manager")
    private ResponseEntity<Page<Product>> showSaleProductManagerList(@RequestParam(value = "categoryId") Integer categoryId, @RequestParam(value = "trademarkId") Integer trademarkId, @RequestParam(value = "name", defaultValue = "") String name,  @PageableDefault(size = 5)Pageable pageable){

        Page<Product> productList = null;

        if (categoryId == -1 && trademarkId == -1){
            productList = productService.showAll(name, pageable);
        }

        if (categoryId != -1 && trademarkId != -1){
            productList = productService.getAllProduct(categoryId, trademarkId, name, pageable);
        }

        if (categoryId == -1 && trademarkId != -1){
            productList = productService.getTrademarkProduct(trademarkId, name, pageable);
        }

        if (categoryId != -1 && trademarkId == -1){
            productList = productService.getCategoryProduct(categoryId, name, pageable);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        productDto.setPrice(productDto.getEntryPrice() + productDto.getEntryPrice() * 0.2);
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        productService.updateProduct(product.getName(), product.getDescription(), product.getPrice(), product.getEntryPrice(), product.getQuantity(), product.getCategory().getId(), product.getTrademark().getId(), product.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/delete")
    private ResponseEntity<?> deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
