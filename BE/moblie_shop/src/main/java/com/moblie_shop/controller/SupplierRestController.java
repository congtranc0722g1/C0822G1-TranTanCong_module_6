package com.moblie_shop.controller;

import com.moblie_shop.dto.SupplierDto;
import com.moblie_shop.model.Supplier;
import com.moblie_shop.service.ISupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin("*")
public class SupplierRestController {

    @Autowired
    private ISupplierService supplierService;

    @PostMapping("/add")
    private ResponseEntity<?> addSupplier(@RequestBody @Validated SupplierDto supplierDto, BindingResult bindingResult){
        new SupplierDto().validate(supplierDto, bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(supplierDto, supplier);
        supplierService.addSupplier(supplier.getCode(), supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/supplier/{id}")
    private ResponseEntity<Supplier> findSupplier(@PathVariable("id") Integer id){
        Supplier supplier = supplierService.findSupplier(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateSupplier(@RequestBody @Validated SupplierDto supplierDto, BindingResult bindingResult){
        new SupplierDto().validate(supplierDto, bindingResult);
        if (bindingResult.hasErrors()){

        }
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(supplierDto, supplier);
        supplierService.updateSupplier(supplier.getName(), supplier.getAddress(), supplier.getPhoneNumber(), supplier.getEmail(), supplier.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
