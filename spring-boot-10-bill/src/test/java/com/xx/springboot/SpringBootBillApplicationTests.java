package com.xx.springboot;

import com.xx.springboot.entities.Bill;
import com.xx.springboot.entities.BillProvider;
import com.xx.springboot.entities.Provider;
import com.xx.springboot.mapper.BillMapper;
import com.xx.springboot.mapper.ProviderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBillApplicationTests {

	@Autowired
	ProviderMapper providerMapper;

	@Test
	public void contextLoads() {
		Provider provider = new Provider();
		provider.setProviderName("A");
        List<Provider> providers = providerMapper.getProvider(provider);
        System.out.println(providers.get(0));

        Provider p = providerMapper.getProviderByPid(2);
        System.out.println(p);

		p.setProviderName("B货域名供应商...");
		int i = providerMapper.updateProvider(p);
		System.out.println(i);

		//providerMapper.addProvider(new Provider(null, "PR-AA", "Promissing供应商111", "小张", "18888666981", "深圳软件园", "0911-0123456", "品质A"));

		providerMapper.deleteProviderByPid(8);

	}


	@Autowired
	BillMapper billMapper;
	@Test
	public void testBill() {
		Bill bill = new Bill();
		bill.setBillName("com");
		List<BillProvider> bills = billMapper.getBill(bill);
		System.out.println(bills.get(0));

		BillProvider billProvider = billMapper.getBillByBid(2);
		System.out.println(billProvider);

		billProvider.setBillName("ESC包月云服务");
		Bill b = (Bill) billProvider;
		billMapper.updateBill(b);

		//billMapper.addBill( new Bill(null, "Bi-AA11", "粮油aaa", "斤", 80,480.8, new Provider(2002, "PR-BB", "Promissing供应商111", "小李", "18888666982", "深圳软件园", "0911-0123453", "品质B"), 1));

		billMapper.deleteBillByBid(7);
	}

}
