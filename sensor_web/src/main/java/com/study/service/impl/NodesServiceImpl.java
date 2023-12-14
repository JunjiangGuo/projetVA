package com.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.dao.NodesMapper;
import com.study.entity.Nodes;
import com.study.service.NodesService;
import org.springframework.stereotype.Service;


@Service
public class NodesServiceImpl extends ServiceImpl<NodesMapper, Nodes> implements NodesService {
}
