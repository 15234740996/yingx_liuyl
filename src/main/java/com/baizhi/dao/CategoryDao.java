package com.baizhi.dao;

import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.po.CategoryPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryDao  {

    //员工查询所有
    List<Category> findAll(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询总条数
    Long count();

    //保存员工信息
    void add(Category category);

    //修改员工信息
    void update(Category category);

    //根据id删除员工信息
    void delete(String id);

    //根据id查询一个
    Category showid(@Param("id") String id);




    /*二级类别*/
    //员工查询所有二级类别
    List<Category> findAlltwo(@Param("start") Integer start, @Param("rows") Integer rows,@Param("id")String id);
    //查询总条数
    Long counttwo(String id);



    /*一级类别下拉列表*/
    List<Category> show();


    /*前台-------------------------------------------------------------------------------------------------*/
    List<CategoryPo> queryAllCategory();




}
