package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.entity.Region;
import com.study.service.RegionService;
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
public class RegionController {


    @Autowired
    private RegionService RegionService;

    //删除区域
    @RequestMapping("/deleteRegion")
    @ResponseBody
    public String deleteRegion(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        RegionService.removeById(id);
        message = "yes";
        return message;
    }

    //新增区域页面
    @RequestMapping("/toAddRegion")
    public String toAddRegions( HttpServletRequest request){
        return "region/add";
    }

    //新增区域
    @RequestMapping("/addRegion")
    @ResponseBody
    public String addRegion(Region data, Model model, HttpSession httpSession){
        String message = "no";
        QueryWrapper<Region> queryWrapper = new  QueryWrapper<>();
        queryWrapper.eq("name",data.getName());
        Region Region = RegionService.getOne(queryWrapper);
        if(Region == null){
            RegionService.save(data);
            message = "yes";
        }else{
            message = "is";
        }
        return message;
    }

    //修改区域
    @RequestMapping("/updateRegion")
    @ResponseBody
    public String updateRegion(Region data, Model model, HttpSession httpSession){
        String message = "no";
        RegionService.updateById(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateRegion")
    public String toUpdateRegion(Integer id, Model model, HttpServletRequest request){
        Region data =RegionService.getById(id);
        request.setAttribute("data",data);
        return "region/update";
    }

    //查询区域
    @RequestMapping("/RegionList")
    public String RegionList(Model model, String key,
                              @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                              HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Region> queryWrapper = new QueryWrapper<Region>();
        queryWrapper.like(StringUtils.hasText(key),"name",key);
        List<Region> list = RegionService.list(queryWrapper);

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","RegionList");
        model.addAttribute("key",key);
        return "region/list";

    }



}
