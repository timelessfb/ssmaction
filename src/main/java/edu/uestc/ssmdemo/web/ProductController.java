package edu.uestc.ssmdemo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uestc.ssmdemo.entity.Category;
import edu.uestc.ssmdemo.entity.Product;
import edu.uestc.ssmdemo.service.CategoryService;
import edu.uestc.ssmdemo.service.ProductService;
import edu.uestc.ssmdemo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page) {
        Category c = categoryService.get(cid);
        List<Product> ps = productService.list(cid);

        //设置分页对象page的属性
        PageHelper.offsetPage(page.getStart(), page.getCount());
        int total = (int) new PageInfo<Product>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + cid);

        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
        model.addAttribute("ps", ps);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        productService.delete(id);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id, Model model) {
        Product p = productService.get(id);
        p.setCategory(categoryService.get(p.getCid()));
        model.addAttribute("p", p);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product p) {
        productService.update(p);
        return "redirect:admin_product_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_product_add")
    public String add(Product p) {
        productService.add(p);
        System.out.println(p.getCid());
        return "redirect:admin_product_list?cid=" + p.getCid();
    }
}
