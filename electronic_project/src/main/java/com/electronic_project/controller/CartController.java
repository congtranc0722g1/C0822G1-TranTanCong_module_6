package com.electronic_project.controller;

import com.electronic_project.dto.cart.CartDto;
import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.model.product.Product;
import com.electronic_project.model.purchase.PurchaseDetail;
import com.electronic_project.service.ICartService;
import com.electronic_project.service.IProductService;
import com.electronic_project.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IPurchaseService purchaseService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    private ResponseEntity<List<ICartDto>> getCart(@RequestParam(value = "id") Integer id) {
        List<ICartDto> cartList = cartService.getCart(id);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("/add")
    private ResponseEntity<?> addCart(@RequestBody CartDto cartDto) {
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        PurchaseDetail purchaseDetail = cartService.findPurchase(cartDto.getProductId(), purchaseId);
        Product product = productService.findProduct(cartDto.getProductId());
        if (purchaseId != -1) {
            if (purchaseDetail == null){
                if (product.getQuantity()>=1){
                    if (cartDto.getQuantity() == null){
                        return new ResponseEntity<>("errorFormatQuantity", HttpStatus.BAD_REQUEST);
                    }else if (cartDto.getQuantity() > product.getQuantity()){
                        return new ResponseEntity<>("exceedTheAmount", HttpStatus.BAD_REQUEST);
                    } else if (cartDto.getQuantity() < 1) {
                        return new ResponseEntity<>("errorInputQuantity", HttpStatus.BAD_REQUEST);
                    }else {
                        cartService.addCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseId);
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                }else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }
            if (purchaseDetail != null) {
                Integer newQuantity = purchaseDetail.getQuantity() + cartDto.getQuantity();
                if (newQuantity < 1 ){
                    return new ResponseEntity<>("errorQuantity",HttpStatus.BAD_REQUEST);
                } else if (newQuantity > product.getQuantity()){
                    cartService.updateCart(purchaseDetail.getQuantity(), cartDto.getProductId(), purchaseId);
                    return new ResponseEntity<>("exceedTheAmount", HttpStatus.BAD_REQUEST);
                }else {
                    cartService.updateCart(newQuantity, cartDto.getProductId(), purchaseId);
                    return new ResponseEntity<>(HttpStatus.OK);
                }

            }
        }
        if (purchaseId == -1) {
            String code = purchaseService.checkCode();
            purchaseService.addPurchase(code, 1, cartDto.getUserId());
            Integer purchaseIdNew = cartService.checkPurchase(cartDto.getUserId());
            if (cartDto.getQuantity() == null){
                return new ResponseEntity<>("errorFormatQuantity", HttpStatus.BAD_REQUEST);
            } else if (cartDto.getQuantity()> product.getQuantity()){
                return new ResponseEntity<>("exceedTheAmount", HttpStatus.BAD_REQUEST);
            }else {
                cartService.addCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseIdNew);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/total-payment")
    private ResponseEntity<Double> getTotalPayment(@RequestParam(value = "id") Integer id) {
        Double totalPayment = cartService.getTotalPayment(id);
        return new ResponseEntity<>(totalPayment, HttpStatus.OK);
    }

    @PostMapping("/delete")
    private ResponseEntity<?> deletePurchaseDetail(@RequestBody CartDto cartDto){
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        cartService.deletePurchaseDetail(cartDto.getProductId(), purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCart(@RequestBody CartDto cartDto){
        Integer purchaseId = cartService.checkPurchase(cartDto.getUserId());
        PurchaseDetail purchaseDetail = cartService.findPurchase(cartDto.getProductId(), purchaseId);
        Product product = productService.findProduct(cartDto.getProductId());
        if (cartDto.getQuantity()== null){
            cartService.updateCart(purchaseDetail.getQuantity(), cartDto.getProductId(), purchaseId);
            return new ResponseEntity<>("errorQuantityFormat",HttpStatus.BAD_REQUEST);
        }else if (cartDto.getQuantity()< 1){
//            cartService.deletePurchaseDetail(cartDto.getProductId(), purchaseId);
            return new ResponseEntity<>("errorQuantity", HttpStatus.BAD_REQUEST);
        }else if (cartDto.getQuantity() > product.getQuantity()){
            cartService.updateCart(purchaseDetail.getQuantity(), cartDto.getProductId(), purchaseId);
            return new ResponseEntity<>("exceedTheAmount", HttpStatus.BAD_REQUEST);
        } else {
            cartService.updateCart(cartDto.getQuantity(), cartDto.getProductId(), purchaseId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
