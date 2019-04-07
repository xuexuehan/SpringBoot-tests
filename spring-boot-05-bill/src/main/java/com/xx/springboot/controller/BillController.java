package com.xx.springboot.controller;

import com.sun.org.apache.bcel.internal.generic.RET;
import com.xx.springboot.dao.BillDao;
import com.xx.springboot.entities.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.ext.MacArabic;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * 账单的控制层
 * */
@Controller
public class BillController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    BillDao billDao;

    //账单列表查询
    @GetMapping("/bills")
    public String list(Map<String, Object> map, @RequestParam(value = "bill", required = false) Bill bill, HttpSession session) {
        logger.info("账单列表查询");
        Collection<Bill> bills = billDao.getAll(bill);
        map.put("bills", bills);
        map.put("bill", bill);
        return "bill/list";
    }

    //查看单个账单列表/进入修改界面
    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid,
                       Map<String, Object> map,
                       @RequestParam(value = "type", defaultValue = "view") String type) {
        logger.info("查询" + bid + "详情---");
        Bill bill = billDao.get(bid);
        map.put("bill", bill);
        //return "bill/view";
        return "bill/" + type;
    }

    //修改账单信息
    @PutMapping("/bill")
    public String update(Bill bill) {
        logger.info("更新账单操作---" + bill);
        billDao.save(bill);
        return "redirect:/bills";
    }

    //前往添加界面
    @GetMapping("/bill")
    public String toAddPage() {
        return "bill/add";
    }

    //添加账单信息
    @PostMapping("/bill")
    public String add(Bill bill) {
        logger.info("添加账单信息" + bill);
        billDao.save(bill);
        return "redirect:/bills";
    }

    //删除账单信息
    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable("bid") Integer bid) {
        logger.info("删除账单信息" + bid);
        billDao.delete(bid);
        return "redirect:/bills";
    }
}
