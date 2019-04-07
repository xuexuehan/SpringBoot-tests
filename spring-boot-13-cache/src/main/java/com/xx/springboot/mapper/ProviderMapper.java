package com.xx.springboot.mapper;

import com.xx.springboot.entity.Provider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProviderMapper {

    @Select("SELECT * FROM provider where pid = #{pid}")
    Provider getProviderByPid(Integer pid);

    @Insert("INSERT INTO provider(`provider_code`, `providerName`, `people`, `phone`, `address`, `fax`, `describe`, `create_date`)" +
            "VALUES (#{providerCode}, #{providerName}, #{people}, #{phone}, #{address}, #{fax}, #{describe}, now())")
    int addProvider(Provider provider);

    @Delete("DELETE FROM provider WHERE pid = #{pid}")
    int deleteProviderByPid(Integer pid);

    @Update(" UPDATE provider SET" +
            "`provider_code` = #{providerCode}, `providerName` = #{providerName}, `people` = #{people}," +
            "`phone` = #{phone}, `address` = #{address}, `fax` = #{fax}, `describe` = #{describe}, `create_date` = now()" +
            " WHERE `pid` = #{pid}")
    int updateProvider(Provider provider);
}
