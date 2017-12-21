package edu.uestc.ssmdemo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uestc.ssmdemo.entity.Category;
import edu.uestc.ssmdemo.entity.Property;
import edu.uestc.ssmdemo.service.CategoryService;
import edu.uestc.ssmdemo.service.PropertyService;
import edu.uestc.ssmdemo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page) {
        //查询jsp页面所需的model:c和ps
        Category c = categoryService.get(cid);
        List<Property> ps = propertyService.list(cid);
        System.out.println("ps");
        System.out.println(ps);
        //设置分页对象page的属性
        PageHelper.offsetPage(page.getStart(), page.getCount());
        int total = (int) new PageInfo<Property>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + c.getId());

        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property p, Model model) {
        propertyService.add(p);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        Property p = propertyService.get(id);
        propertyService.delete(p.getId());
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(int id, Model model) {
        Property p = propertyService.get(id);
        //在controller层做联合查询的操作
        Category category = categoryService.get(p.getCid());
        p.setCategory(category);
        model.addAttribute("p", p);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p) {
        propertyService.update(p);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }
}
