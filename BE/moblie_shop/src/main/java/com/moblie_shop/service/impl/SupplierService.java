package com.moblie_shop.service.impl;

import com.moblie_shop.model.Supplier;
import com.moblie_shop.repository.ISupplierRepository;
import com.moblie_shop.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public void addSupplier(String code, String name, String address, String phoneNumber, String email) {
        supplierRepository.addSupplier(code, name, address, phoneNumber, email);
    }

    @Override
    public Supplier findSupplier(Integer id) {
        return supplierRepository.findSupplier(id);
    }

    @Override
    public void updateSupplier(String name, String address, String phoneNumber, String email, Integer id) {
        supplierRepository.updateSupplier(name, address, phoneNumber, email, id);
    }
}
