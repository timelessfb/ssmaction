package edu.uestc.ssmdemo.service;

import edu.uestc.ssmdemo.entity.Property;

import java.util.List;

public interface PropertyService {
    void add(Property p);

    void delete(int id);

    void update(Property p);

    Property get(int id);

    List<Property> list(int cid);
}
