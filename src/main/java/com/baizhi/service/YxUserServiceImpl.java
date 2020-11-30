package com.baizhi.service;

import com.baizhi.dao.YxUserDao;
import com.baizhi.entity.YxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("yxuserService")
@Transactional
public class YxUserServiceImpl implements YxUserService {


    @Autowired
    private YxUserDao yxUserDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<YxUser> showAll(Integer page, Integer rows) {
        int start = (page - 1) * rows;

        return yxUserDao.showAll(start, rows);
    }

    @Override
    public Long count() {
        return yxUserDao.count();
    }

    @Override
    public void updateStatus(String start, String id) {
        yxUserDao.updateStatus(start,id);
    }





    @Override//导出查所有
    public List<YxUser> findAll() {
        return yxUserDao.findAll();
    }


}
