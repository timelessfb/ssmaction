package edu.uestc.ssmdemo.serviceimpl;

import edu.uestc.ssmdemo.dao.ProductMapper;
import edu.uestc.ssmdemo.dao.PropertyMapper;
import edu.uestc.ssmdemo.entity.Product;
import edu.uestc.ssmdemo.entity.ProductExample;
import edu.uestc.ssmdemo.entity.Property;
import edu.uestc.ssmdemo.entity.PropertyExample;
import edu.uestc.ssmdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }
}
