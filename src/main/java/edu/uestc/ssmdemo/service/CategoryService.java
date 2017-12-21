package edu.uestc.ssmdemo.service;

import edu.uestc.ssmdemo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category c);
}
