package com.moblie_shop.service;


import com.moblie_shop.model.Supplier;

public interface ISupplierService {

    void addSupplier(String code, String name, String address, String phoneNumber, String email);

    Supplier findSupplier(Integer id);

    void updateSupplier(String name, String address, String phoneNumber, String email, Integer id);
}
