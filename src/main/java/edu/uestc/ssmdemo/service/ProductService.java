package edu.uestc.ssmdemo.service;

import edu.uestc.ssmdemo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(int cid);
}
