package com.xx.springboot.mapper;

import com.xx.springboot.entities.Bill;

public interface BillMapper {

    Bill getBillByBid(Integer bid);

    int addBill(Bill bill);
}
