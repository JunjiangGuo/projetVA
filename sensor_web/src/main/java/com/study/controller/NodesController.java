package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.entity.Nodes;
import com.study.service.NodesService;
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
public class NodesController {


    @Autowired
    private NodesService NodesService;
    @Autowired
    private RegionService regionService;

    //删除节点
    @RequestMapping("/deleteNodes")
    @ResponseBody
    public String deleteNodes(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        NodesService.removeById(id);
        message = "yes";
        return message;
    }

    //新增节点页面
    @RequestMapping("/toAddNodes")
    public String toAddNodess( HttpServletRequest request){
        request.setAttribute("regionList",regionService.list());
        return "nodes/add";
    }

    //新增节点
    @RequestMapping("/addNodes")
    @ResponseBody
    public String addNodes(Nodes data, Model model, HttpSession httpSession){
        String message = "no";
        NodesService.save(data);
        message = "yes";
        return message;
    }

    //修改节点
    @RequestMapping("/updateNodes")
    @ResponseBody
    public String updateNodes(Nodes data, Model model, HttpSession httpSession){
        String message = "no";
        NodesService.updateById(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateNodes")
    public String toUpdateNodes(Integer id, Model model, HttpServletRequest request){
        Nodes data =NodesService.getById(id);
        request.setAttribute("data",data);
        return "nodes/update";
    }

    //查询节点
    @RequestMapping("/NodesList")
    public String NodesList(Model model, String key,
                              @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                              HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Nodes> queryWrapper = new QueryWrapper<Nodes>();
        queryWrapper.like(StringUtils.hasText(key),"name",key);
        List<Nodes> list = NodesService.list(queryWrapper);
        for (Nodes nodes : list) {
            nodes.setRegion(regionService.getById(nodes.getRid()));
        }

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","NodesList");
        model.addAttribute("key",key);
        return "nodes/list";

    }



}
