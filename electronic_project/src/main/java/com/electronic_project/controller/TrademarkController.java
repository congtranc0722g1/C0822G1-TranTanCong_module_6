package com.electronic_project.controller;

import com.electronic_project.dto.product.ITrademarkListDto;
import com.electronic_project.model.product.Trademark;
import com.electronic_project.service.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/trademark")
public class TrademarkController {

    @Autowired
    private ITrademarkService trademarkService;

    @GetMapping("/list")
    private ResponseEntity<List<ITrademarkListDto>> showAll(){
        List<ITrademarkListDto> trademarkList = trademarkService.showAll();
        return new ResponseEntity<>(trademarkList, HttpStatus.OK);
    }
}
