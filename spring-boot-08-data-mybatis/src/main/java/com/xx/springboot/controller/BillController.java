package com.xx.springboot.controller;

import com.xx.springboot.entities.Bill;
import com.xx.springboot.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    BillMapper billMapper;

    @GetMapping("/bill/{bid}")
    public Bill getBill(@PathVariable("bid") Integer bid) {
        Bill bill = billMapper.getBillByBid(bid);
        return bill;
    }

    @GetMapping("/bill")
    public Bill addBill(Bill bill) {
        billMapper.addBill(bill);
        return bill;
    }
}
