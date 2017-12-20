package edu.uestc.ssmdemo.serviceimpl;

import edu.uestc.ssmdemo.dao.PropertyMapper;
import edu.uestc.ssmdemo.entity.Property;
import edu.uestc.ssmdemo.entity.PropertyExample;
import edu.uestc.ssmdemo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    public void add(Property p) {
        propertyMapper.insert(p);
    }

    public void delete(int id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    public void update(Property p) {
        propertyMapper.updateByPrimaryKeySelective(p);
    }

    public Property get(int id) {
        Property property = propertyMapper.selectByPrimaryKey(id);
        return property;
    }

    public List<Property> list(int cid) {
        PropertyExample example = new PropertyExample();
        PropertyExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Property> properties = propertyMapper.selectByExample(example);
        return properties;
    }
}
