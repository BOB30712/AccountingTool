package org.bob.controller;


import org.bob.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/expense") // 基礎路徑為 /expense
public class WebController {

    @Autowired
    public ExpenseService expenseService;

    public record product(String name, BigDecimal amount){}


    @GetMapping("/create")
    public ResponseEntity<product> gen_data(){
        expenseService.create();

        return new ResponseEntity<>(new product("test",new BigDecimal("100.1")), HttpStatus.OK);
    }
}
