package com.xx.springboot.entities;

/**
 * 封装新的实体，账单列表和详情需要供应商名称
 * */
public class BillProvider extends Bill {
    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
