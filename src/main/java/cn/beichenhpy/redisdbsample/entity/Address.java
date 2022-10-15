package cn.beichenhpy.redisdbsample.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("address")
public class Address implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField
    private String address;

    @TableField
    private String name;
}
