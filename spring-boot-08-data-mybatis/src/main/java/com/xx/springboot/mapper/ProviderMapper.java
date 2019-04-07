package com.xx.springboot.mapper;

import com.xx.springboot.entities.Provider;
import org.apache.ibatis.annotations.*;

/**
 * 使用mybatis注解版本
 * */

//@Mapper //指定这是操作数据的Mapper
public interface ProviderMapper {
    @Select("select * from provider where pid=#{pid}")
    Provider getProviderByPid(Integer pid);

    @Options(useGeneratedKeys = true, keyProperty = "pid")//是否使用自增组件
    @Insert("insert into provider(providerName) values(#{providerName})")
    int add(Provider provider);

    @Delete("delete from provider where pid=#{pid}")
    int deteProviderByPid(Integer pid);

    @Update("update provider set providerName=#{providerName} where pid=#{pid}")
    int updateProvider(Provider provider);
}
