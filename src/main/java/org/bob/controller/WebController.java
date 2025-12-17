package org.bob.controller;


import jakarta.validation.Valid;
import org.bob.entity.Expense;
import org.bob.record.ApiResponse;
import org.bob.record.CreateExpenseRequest;
import org.bob.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Expense>> createExpense(
            @Valid @RequestBody CreateExpenseRequest request
    ){
        Expense expense = new Expense();
        expense.setDescription(request.description());
        expense.setAmount(request.amount());
        expense.setDate(request.date());
        expense.setAmount(request.amount());

        ApiResponse<Expense> response = new ApiResponse<>(
            "success",
            expenseService.save(expense),
            "Expense created successfully",
            LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
