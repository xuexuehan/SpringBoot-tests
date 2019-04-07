package com.xx.springboot.controller;

import com.sun.org.apache.bcel.internal.generic.RET;
import com.xx.springboot.dao.BillDao;
import com.xx.springboot.entities.Bill;
import com.xx.springboot.entities.BillProvider;
import com.xx.springboot.entities.Provider;
import com.xx.springboot.mapper.BillMapper;
import com.xx.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.MacArabic;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 账单的控制层
 * */
@Controller
public class BillController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    BillMapper billMapper;

    @Autowired
    ProviderMapper providerMapper;

    //账单列表查询
    @GetMapping("/bills")
    public String list(Map<String, Object> map, Bill bill) {
        logger.info("账单列表查询" + bill);
        List<BillProvider> billProviders = billMapper.getBill(bill);

        //获取供应商列表
        List<Provider> providers = providerMapper.getProvider(null);

        map.put("billProviders", billProviders);
        map.put("providers", providers);
        //用于搜索回显数据
        map.put("billName", bill.getBillName());
        map.put("pid", bill.getPid());
        map.put("pay", bill.getPay());
        return "bill/list";
    }

    //查看单个账单列表/进入修改界面
    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid,
                       Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        logger.info("查询" + bid + "详情---");
        BillProvider billProvider = billMapper.getBillByBid(bid);
        map.put("billProvider", billProvider);

        if("update".equals(type)) {
            List<Provider> providers = providerMapper.getProvider(null);
            map.put("providers", providers);
        }
        //return "bill/view";
        return "bill/" + type;
    }

    //修改账单信息
    @PutMapping("/bill")
    public String update(Bill bill) {
        logger.info("更新账单操作---" + bill);
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    //前往添加界面
    @GetMapping("/bill")
    public String toAddPage(Map<String, Object> map) {

        List<Provider> providers = providerMapper.getProvider(null);
        map.put("providers", providers);

        return "bill/add";
    }

    //添加账单信息
    @PostMapping("/bill")
    public String add(Bill bill) {
        logger.info("添加账单信息" + bill);
        billMapper.addBill(bill);
        return "redirect:/bills";
    }

    //删除账单信息
    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid) {
        logger.info("删除账单信息" + bid);
        billMapper.deleteBillByBid(bid);
        return "redirect:/bills";
    }
}
