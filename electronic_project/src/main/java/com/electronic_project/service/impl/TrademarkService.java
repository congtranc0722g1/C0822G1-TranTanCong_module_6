package com.electronic_project.service.impl;

import com.electronic_project.model.product.Trademark;
import com.electronic_project.repository.ITrademarkRepository;
import com.electronic_project.service.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrademarkService implements ITrademarkService {

    @Autowired
    private ITrademarkRepository trademarkRepository;

    @Override
    public List<Trademark> showAll() {
        return trademarkRepository.showAll();
    }
}
