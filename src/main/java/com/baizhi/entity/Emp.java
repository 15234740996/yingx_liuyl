package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

    @Excel(name = "ID")
    private String id;

    @Excel(name = "头像",type = 2)
    private String name;
    @Excel(name="年龄")
    private Integer age;
    @Excel(name = "生日",exportFormat="yyyy-MM-dd",importFormat="yyyy-MM-dd")
    private Date birthday;
}
