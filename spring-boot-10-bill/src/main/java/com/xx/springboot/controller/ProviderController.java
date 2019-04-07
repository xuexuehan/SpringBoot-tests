package com.xx.springboot.controller;

import com.xx.springboot.dao.ProviderDao;
import com.xx.springboot.entities.Provider;
import com.xx.springboot.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *供应商的控制层
 *
 */
@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderMapper providerMapper;

    //@RequestMapping(value = "/providers", method = RequestMethod.GET)
    @GetMapping("/providers")
    public String list(Map<String, Object> map, Provider provider) {
        logger.info("供应商列表查询..." + provider);
        List<Provider> providers = providerMapper.getProvider(provider);
        map.put("providers", providers);
        map.put("providerName", provider.getProviderName());
        return "provider/list";
    }

    //进入查看/修改供应商界面
    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid,
                       @RequestParam(value = "type", defaultValue = "view") String type,
                       Map<String, Object> map) {
        logger.info("查询" + pid + "详情---");
        Provider provider = providerMapper.getProviderByPid(pid);
        map.put("provider", provider);

        //return "provider/view";
        return "provider/" + type;
    }

    //修改供应商信息
    @PutMapping("/provider")
    public String update(Provider provider) {
        logger.info("更新供应商操作---");
        providerMapper.updateProvider(provider);

        return "redirect:/providers";
    }

    //前往添加界面
    @GetMapping("/provider")
    public String toAddPage() {
        return "provider/add";
    }

    //添加数据
    @PostMapping("/provider")
    public String add(Provider provider) {
        logger.info("添加供应商信息" + provider);
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }

    //删除供应商信息
    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable("pid") Integer pid) {
        logger.info("删除供应商id" + pid);
        providerMapper.deleteProviderByPid(pid);
        return "redirect:/providers";
    }
}
