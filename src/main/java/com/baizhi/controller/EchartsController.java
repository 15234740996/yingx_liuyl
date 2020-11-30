package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.entity.Sc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("echarts")
public class EchartsController {

    @RequestMapping("getUserData")
    public HashMap<String, Object> getUserData(){
        HashMap<String, Object> map = new HashMap<>();

        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
        map.put("boys", Arrays.asList(50, 200, 360, 100, 100, 200));
        map.put("girls", Arrays.asList(50, 200, 160, 100, 100, 200));

        return map;
    }



    /*
    [
  {
    "sex":"小男孩",
    "citys":[
      {"name": "北京","value": 31},
      {"name": "天津","value": 22},
      {"name": "西藏","value": 39},
      {"name": "四川","value": 83},
      {"name": "宁夏","value": 63},
      {"name": "香港","value": 53},
      {"name": "澳门","value": 37}
    ]
  },{
  "sex":"小姑娘",
  "citys":[
    {"name": "北京","value": 31},
    {"name": "天津","value": 22},
    {"name": "河北","value": 39},
    {"name": "山西","value": 83},
    {"name": "河南","value": 63},
    {"name": "山东","value": 53},
    {"name": "黑龙江","value": 53},
    {"name": "云南","value": 37}
  ]
}
]


select city name,count(id) value
from yx_user where sex='女' GROUP BY  city(地区)

    *
    * */
    @RequestMapping("getChinaData")
    public ArrayList<Sc> getChinaData(){

        ArrayList<Sc> scs = new ArrayList<>();

        ArrayList<City> boyCitys = new ArrayList<>();
        boyCitys.add(new City("北京","200"));
        boyCitys.add(new City("山西","500"));
        boyCitys.add(new City("山东","200"));
        boyCitys.add(new City("湖南","700"));
        boyCitys.add(new City("湖北","200"));
        boyCitys.add(new City("河北","900"));

        ArrayList<City> gitlCitys = new ArrayList<>();
        gitlCitys.add(new City("黑龙江","200"));
        gitlCitys.add(new City("辽宁","500"));
        gitlCitys.add(new City("西藏","200"));
        gitlCitys.add(new City("湖南","700"));
        gitlCitys.add(new City("湖北","200"));
        gitlCitys.add(new City("河北","900"));

        scs.add(new Sc("小男孩",boyCitys));
        scs.add(new Sc("小姑娘",gitlCitys));

        return scs;
    }

}
