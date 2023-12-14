package com.study.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.entity.Transducer;
import com.study.service.NodesService;
import com.study.service.TransducerService;
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
public class TransducerController {


    @Autowired
    private TransducerService TransducerService;

    @Autowired
    private NodesService nodesService;

    //删除传感器
    @RequestMapping("/deleteTransducer")
    @ResponseBody
    public String deleteTransducer(Integer id, Model model, HttpSession httpSession){
        String message = "no";
        TransducerService.removeById(id);
        message = "yes";
        return message;
    }

    //新增传感器页面
    @RequestMapping("/toAddTransducer")
    public String toAddTransducers( HttpServletRequest request){
        request.setAttribute("nodesList",nodesService.list());
        return "transducer/add";
    }

    //新增传感器
    @RequestMapping("/addTransducer")
    @ResponseBody
    public String addTransducer(Transducer data, Model model, HttpSession httpSession){
        String message = "no";
        TransducerService.save(data);
        message = "yes";
        return message;
    }

    //修改传感器
    @RequestMapping("/updateTransducer")
    @ResponseBody
    public String updateTransducer(Transducer data, Model model, HttpSession httpSession){
        String message = "no";
        TransducerService.updateById(data);
        message = "yes";
        return message;
    }


    //修改页面
    @RequestMapping("/toUpdateTransducer")
    public String toUpdateTransducer(Integer id, Model model, HttpServletRequest request){
        Transducer data =TransducerService.getById(id);
        request.setAttribute("data",data);
        return "transducer/update";
    }

    //查询传感器
    @RequestMapping("/TransducerList")
    public String TransducerList(Model model, String key,
                              @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "8") Integer pageSize,
                              HttpSession httpSession, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Transducer> queryWrapper = new QueryWrapper<Transducer>();
        queryWrapper.like(StringUtils.hasText(key),"name",key);
        List<Transducer> list = TransducerService.list(queryWrapper);
        for (Transducer transducer : list) {
            transducer.setNodes(nodesService.getById(transducer.getNid()));
        }

        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("url","TransducerList");
        model.addAttribute("key",key);
        return "transducer/list";

    }



}
