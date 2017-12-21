package edu.uestc.ssmdemo.serviceimpl;

import edu.uestc.ssmdemo.dao.ProductMapper;
import edu.uestc.ssmdemo.dao.ProductimageMapper;
import edu.uestc.ssmdemo.entity.Product;
import edu.uestc.ssmdemo.entity.ProductExample;
import edu.uestc.ssmdemo.entity.Productimage;
import edu.uestc.ssmdemo.entity.ProductimageExample;
import edu.uestc.ssmdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductimageMapper productimageMapper;

    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);

        for (Product product : products) {
            ProductimageExample productimageExample = new ProductimageExample();
            ProductimageExample.Criteria criteria1 = productimageExample.createCriteria();
            criteria1.andPidEqualTo(product.getId());
            List<Productimage> productimages = productimageMapper.selectByExample(productimageExample);
            product.setFirstProductImage(productimages.get(0));
        }
        System.out.println(products);

        return products;
    }
}
