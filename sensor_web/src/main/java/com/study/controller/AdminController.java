package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.entity.Admin;
import com.study.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private AdminService AdminService;

    //删除用户
    @RequestMapping("/deleteAdmin")
    @ResponseBody
    public String deleteAdmin(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        AdminService.removeById(id);
        message = "yes";
        return message;
    }

    //新增用户页面
    @RequestMapping("/toAddAdmin")
    public String toAddAdmins( HttpServletRequest request){
        return "admin/add";
    }

    //新增用户
    @RequestMapping("/addAdmin")
    @ResponseBody
    public String addAdmin(Admin data, Model model, HttpSession httpSession){
        String message = "no";
        QueryWrapper<Admin> queryWrapper = new  QueryWrapper<>();
        queryWrapper.eq("username",data.getUsername());
        Admin Admin = AdminService.getOne(queryWrapper);
        if(Admin == null){
            AdminService.save(data);
            message = "yes";
        }else{
            message = "is";
        }
        return message;
    }

    //修改用户
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public String updateAdmin(Admin data, Model model, HttpSession httpSession){
        String message = "no";
        AdminService.updateById(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(Integer id, Model model, HttpServletRequest request){
        Admin data =AdminService.getById(id);
        request.setAttribute("data",data);
        return "admin/update";
    }

    //查询用户
    @RequestMapping("/AdminList")
    public String AdminList(Model model, String key,
                              @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                              HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        queryWrapper.like(StringUtils.hasText(key),"username",key);
        List<Admin> list = AdminService.list(queryWrapper);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","AdminList");
        model.addAttribute("key",key);
        return "admin/list";

    }



}
