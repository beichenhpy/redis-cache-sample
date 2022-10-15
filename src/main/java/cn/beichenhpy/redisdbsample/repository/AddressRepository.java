package cn.beichenhpy.redisdbsample.repository;

import cn.beichenhpy.redisdbsample.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressRepository extends BaseMapper<Address> {
}
