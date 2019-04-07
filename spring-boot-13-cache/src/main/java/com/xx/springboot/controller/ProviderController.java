package com.xx.springboot.controller;

import com.xx.springboot.entity.Provider;
import com.xx.springboot.entity.User;
import com.xx.springboot.mapper.ProviderMapper;
import com.xx.springboot.service.ProviderService;
import com.xx.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("/provider/{pid}")
    public Provider getUserById(@PathVariable("pid") Integer pid) {
        Provider provider = providerService.getProviderByPid(pid);
        return provider;
    }

    @GetMapping("/updateProvider")
    public Provider updateProvider(Provider provider) {
        providerService.updateProvider(provider);
        return provider;
    }

    @GetMapping("/deleteProvider/{pid}")
    public Integer deleteProviderById(@PathVariable("pid") Integer pid) {
        providerService.deleteUserByPid(pid);
        return pid;
    }

    @GetMapping("/addProvider")
    public Provider addProvider(Provider provider) {
        providerService.addProvider(provider);
        return provider;
    }

}
