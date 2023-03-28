package com.electronic_project.service;

import com.electronic_project.dto.cart.ICartDto;

import java.util.List;

public interface ICartService {

    List<ICartDto> getCart(Integer id);
}
