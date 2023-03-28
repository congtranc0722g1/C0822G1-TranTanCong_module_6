package com.electronic_project.service.impl;

import com.electronic_project.dto.cart.ICartDto;
import com.electronic_project.repository.ICartRepository;
import com.electronic_project.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public List<ICartDto> getCart(Integer id) {
        return cartRepository.getCart(id);
    }
}
