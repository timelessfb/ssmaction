package edu.uestc.ssmdemo.service;

import edu.uestc.ssmdemo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(int cid);

    void delete(int id);

    Product get(int id);

    void update(Product p);

    void add(Product p);
}
