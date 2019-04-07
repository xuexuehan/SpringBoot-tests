package com.xx.springboot.controller;

import com.xx.springboot.entities.Provider;
import com.xx.springboot.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    ProviderMapper providerMapper;

    @GetMapping("/provider/{pid}")
    public Provider getProvider(@PathVariable("pid") Integer pid) {
        Provider provider = providerMapper.getProviderByPid(pid);
        return provider;
    }

    @GetMapping("/provider")
    public Provider addProvider(Provider provider) {
        providerMapper.add(provider);
        return provider;
    }
}
