package com.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.dao.RegionMapper;
import com.study.entity.Region;
import com.study.service.RegionService;
import org.springframework.stereotype.Service;


@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {
}
