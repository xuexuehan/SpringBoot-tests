package com.xx.springboot.mapper;

import com.xx.springboot.entities.Provider;

import java.util.List;

public interface ProviderMapper {

    List<Provider> getProvider(Provider provider);

    Provider getProviderByPid(Integer pid);

    int addProvider(Provider provider);

    int deleteProviderByPid(Integer pid);

    int updateProvider(Provider provider);
}
