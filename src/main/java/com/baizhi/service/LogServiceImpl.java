package com.baizhi.service;

import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.entity.LogExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional


public class LogServiceImpl implements LogService {
    @Resource
    public LogMapper logMapper;


    @Override
    public HashMap<String, Object> queryAllPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();// new出一个哈希map

        //当前页   page
        map.put("page", page);

        //总条数   records
        LogExample logExample = new LogExample();
        int records = logMapper.selectCountByExample(logExample);
        map.put("records", records);


        //总页数   total
        Integer total = records / rows == 0 ? records / rows : records / rows + 1; //总页数=总条数/每页展示条数   有余数加一页
        map.put("total", total);

        //数据    rows   参数：略过几条，要几条
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Log> logs = logMapper.selectByRowBounds(new Log(), rowBounds);
        map.put("rows", logs);

        return map;
    }
}
