package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Excel(name = "教师ID",needMerge = true)
    private String id;

    @Excel(name = "教师姓名",needMerge = true)
    private String name;

    @ExcelCollection(name = "对应学生")
    private ArrayList<Emp> empList;

}
