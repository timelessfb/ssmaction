package edu.uestc.ssmdemo.serviceimpl;

import edu.uestc.ssmdemo.dao.CategoryMapper;
import edu.uestc.ssmdemo.entity.Category;
import edu.uestc.ssmdemo.entity.CategoryExample;
import edu.uestc.ssmdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> list() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("id desc");
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;
    }
}
