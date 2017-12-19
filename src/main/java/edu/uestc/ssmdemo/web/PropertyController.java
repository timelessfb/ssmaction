package edu.uestc.ssmdemo.web;

import com.github.pagehelper.PageHelper;
import edu.uestc.ssmdemo.entity.Category;
import edu.uestc.ssmdemo.service.CategoryService;
import edu.uestc.ssmdemo.service.PropertyService;
import edu.uestc.ssmdemo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page) {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        return super.toString();
    }
}
