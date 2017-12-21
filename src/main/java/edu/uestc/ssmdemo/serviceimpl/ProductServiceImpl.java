package edu.uestc.ssmdemo.serviceimpl;

import edu.uestc.ssmdemo.dao.ProductImageMapper;
import edu.uestc.ssmdemo.dao.ProductMapper;
import edu.uestc.ssmdemo.entity.Product;
import edu.uestc.ssmdemo.entity.ProductExample;
import edu.uestc.ssmdemo.entity.ProductImage;
import edu.uestc.ssmdemo.entity.ProductImageExample;
import edu.uestc.ssmdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageMapper productImageMapper;

    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCidEqualTo(cid);
        productExample.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(productExample);

        for (Product product : products) {
            System.out.println(product.getId());
            ProductImageExample productimageExample = new ProductImageExample();
            productimageExample.createCriteria().andPidEqualTo(product.getId());
            System.out.println(productimageExample);
            List<ProductImage> productimages = productImageMapper.selectByExample(productimageExample);
            product.setFirstProductImage(productimages.get(0));
        }
        System.out.println(products);

        return products;
    }
}
