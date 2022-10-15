package cn.beichenhpy.redisdbsample.service;


import cn.beichenhpy.redisdbsample.entity.Address;
import cn.beichenhpy.redisdbsample.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final RedisTemplate<String, Address> addressRedisTemplate;
    private static final String REDIS_KEY = "address";

    @Transactional
    public void saveAddress(Address address) {
        addressRepository.insert(address);
        boolean transaction = TransactionSynchronizationManager.isSynchronizationActive();
        log.info("当前事务状态: {}", transaction);
        addressRedisTemplate.delete(REDIS_KEY);
    }



    public Map<String, Address> getAllAddresses() {
        Boolean hasKey = addressRedisTemplate.hasKey(REDIS_KEY);
        if (Boolean.TRUE.equals(hasKey)) {
            return addressRedisTemplate.<String, Address>opsForHash().entries(REDIS_KEY);
        }
        List<Address> addresses = addressRepository.selectList(null);
        Map<String, Address> map = addresses.stream()
                .collect(Collectors.toMap(i -> i.getId().toString(), i -> i));
        addressRedisTemplate.<String, Address>opsForHash().putAll(REDIS_KEY, map);
        return map;
    }


    @Cacheable(value = REDIS_KEY)
    public Map<String, Address> getAllAddressWithCache() {
        List<Address> addresses = addressRepository.selectList(null);
        return addresses.stream()
                .collect(Collectors.toMap(i -> i.getId().toString(), i -> i));
    }


    /**
     * 相关类
     * @see org.springframework.cache.interceptor.CacheInterceptor
     * @see org.springframework.transaction.interceptor.TransactionInterceptor
     * order 越小 优先级越高，越在动态代理的最外层
     */
    @Transactional
    @CacheEvict(value = REDIS_KEY, allEntries = true)
    public void saveAddressWithCache(Address address) {
        addressRepository.insert(address);
    }

}
