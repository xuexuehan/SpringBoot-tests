package com.xx.springboot.mapper;


import com.xx.springboot.entities.Bill;
import com.xx.springboot.entities.BillProvider;

import java.util.List;

public interface BillMapper {

    List<BillProvider> getBill(Bill bill);

    BillProvider getBillByBid(Integer bid);

    int addBill(Bill bill);

    int deleteBillByBid(Integer bid);

    int updateBill(Bill bill);
}
