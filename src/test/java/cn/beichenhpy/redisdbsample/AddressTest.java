package cn.beichenhpy.redisdbsample;

import cn.beichenhpy.redisdbsample.entity.Address;
import cn.beichenhpy.redisdbsample.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@SpringBootTest
public class AddressTest {

    @Resource
    private AddressService addressService;

    private Address address;

    @BeforeEach
    public void prepare() {
        address = new Address();
        address.setAddress("陕西省西安市雁塔区");
        address.setName("眼影华庭");
    }


    @Test
    public void getAll() {
        addressService.getAllAddresses();
    }

    @Test
    public void testDbAndRedisInSameMethod() {
        addressService.saveAddress(address);
    }

    @Test
    public void getAllCache() {
        Map<String, Address> allAddressWithCache = addressService.getAllAddressWithCache();
        log.info("address: {}", allAddressWithCache);
    }

    @Test
    public void testDbAndRedisInSameMethodCache() {
        addressService.saveAddressWithCache(address);
    }
}
