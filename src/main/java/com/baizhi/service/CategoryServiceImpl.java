package com.baizhi.service;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.annotcation.RedisLog;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import com.baizhi.po.VideoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @RedisLog
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findAll(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        int end = page * rows;
        return categoryDao.findAll(start, end);
    }

    @Override
    public Long count() {
        return categoryDao.count();
    }


    @AddLog("添加类别")
    @DelCahe
    @Override
    public void add(Category category) {
        category.setId(UUID.randomUUID().toString());
        if (category.getParent_id() == null) {
            category.setLevels("1");
            category.setParent_id("");
        } else {
            category.setLevels("2");
        }
        categoryDao.add(category);

    }

    @AddLog("修改类别")

    @DelCahe
    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @AddLog("删除类别")
    @DelCahe
    @Override
    public String delete(String id) {
        String message = null;
        Category showid = categoryDao.showid(id);
        System.out.println("-----------------------------------" + showid);
        if (showid.getLevels().equals("1")) {
            Long counttwo = categoryDao.counttwo(showid.getId());
            if (counttwo == 0) {
                message = "删除成功";
                categoryDao.delete(showid.getId());
            } else {
                message = "有子类别,不能删除";
            }
        } else {

            categoryDao.delete(showid.getId());
        }
        return message;


    }

    @Override
    @RedisLog
    public List<Category> findAlltwo(Integer page, Integer rows, String id) {
        int start = (page - 1) * rows;
        int end = page * rows;
        return categoryDao.findAlltwo(start, end, id);


    }

    @Override
    public Long counttwo(String id) {
        System.out.println("------总条数" + categoryDao.counttwo(id));
        return categoryDao.counttwo(id);
    }

    @Override//一级类别下拉列表
    public List<Category> show() {
        return categoryDao.show();
    }

    /*前台---------------------------------------------------------------------------------------*/

    @Override
    public List<CategoryPo> queryAllCategory() {
        return categoryDao.queryAllCategory();


    }
}
