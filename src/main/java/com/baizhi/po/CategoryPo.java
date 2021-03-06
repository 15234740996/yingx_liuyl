package com.baizhi.po;

import com.baizhi.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPo {
    private String id;
    private String cateName;
    private String levels;
    private String parentId;

    private List<CategoryPo> categoryList;
}
