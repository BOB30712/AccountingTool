package org.bob.service;

import org.bob.dto.ExpnseResponseDTO;
import org.bob.entity.Expense;
import org.bob.repo.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    public ExpenseRepository expenseRepository;

    @Transactional
    public void create(){
        String dateString = "2007-12-03";
        LocalDate parsedDate = LocalDate.parse(dateString);

        Expense expense = new Expense();
        expense.setDescription("IS TEST");
        expense.setAmount(new BigDecimal("100.1"));
        expense.setDate(LocalDate.now());
//        expense.setCategory("test");
        expenseRepository.save(expense);
    }

    @Transactional
    public Expense save(Expense expense){
        return expenseRepository.save(expense);
    }


    @Transactional(readOnly = true)
    public List<ExpnseResponseDTO> all(){

        List<Expense> expenseList = expenseRepository.findAll();



        return expenseList.stream().map(ExpnseResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public Expense findById(Long id){
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.orElseGet(expense::get);
    }
}
