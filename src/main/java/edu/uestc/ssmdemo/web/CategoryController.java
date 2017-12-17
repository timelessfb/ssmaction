package edu.uestc.ssmdemo.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.uestc.ssmdemo.entity.Category;
import edu.uestc.ssmdemo.service.CategoryService;
import edu.uestc.ssmdemo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int) new PageInfo<Category>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, MultipartFile image) throws IOException {
        //数据库增加记录
        categoryService.add(c);
        //保存图片
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));//取得文件夹
        File file = new File(imageFolder, c.getId() + ".jpg");//创建图片文件
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);//向磁盘写文件
        return "redirect:/admin_category_list";
    }

}
