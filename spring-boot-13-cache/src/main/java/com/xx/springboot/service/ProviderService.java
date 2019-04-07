package com.xx.springboot.service;


import com.xx.springboot.entity.Provider;
import com.xx.springboot.mapper.ProviderMapper;
import com.xx.springboot.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    RedisClient redisClient;

    @Autowired
    ProviderMapper providerMapper;

    public Provider getProviderByPid(Integer pid) {
        //1、先查询缓存中是否有数据
        Object obj = redisClient.get(pid);
        if(obj != null) {
            return (Provider)obj;
        }
        //2、如果缓存中没有，则查数据库，然后添加到缓存中
        Provider provider = providerMapper.getProviderByPid(pid);

        redisClient.set(pid, provider);

        return provider;
    }

    public Integer updateProvider(Provider provider) {
        int i = providerMapper.updateProvider(provider);
        if(i > 0) {
            //更新缓存数据
             redisClient.set(provider.getPid(), provider);
        }
        return i;
    }

    public Integer deleteUserByPid(Integer pid) {
        int i = providerMapper.deleteProviderByPid(pid);
        if(i > 0) {
            redisClient.del(pid);//清楚key = pid的缓存
        }
        return i;
    }

    public Provider addProvider(Provider provider) {
        int i = providerMapper.addProvider(provider);
        if(i > 0) {
            redisClient.set(provider.getPid(), provider);
        }
        return provider;
    }
}
