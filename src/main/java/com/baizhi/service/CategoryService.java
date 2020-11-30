package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;

import java.util.List;

public interface CategoryService {
    //分页查询所有
    List<Category> findAll(Integer page, Integer rows);

    //查询总条数
    Long count();

    //保存员工信息
    void add(Category category);

    //修改员工信息
    void update(Category category);

    //根据id删除员工信息
    String delete(String id);


    /*二级类别*/
    //分页查询所有
    List<Category> findAlltwo(Integer page, Integer rows,String id);

    //查询总条数
    Long counttwo(String id);

    /*一级类别下拉列表*/
    List<Category> show();




    /*前台类别查询*/
    List<CategoryPo> queryAllCategory();
}
