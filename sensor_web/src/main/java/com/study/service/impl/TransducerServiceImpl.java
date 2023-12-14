package com.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.dao.TransducerMapper;
import com.study.entity.Transducer;
import com.study.service.TransducerService;
import org.springframework.stereotype.Service;


@Service
public class TransducerServiceImpl extends ServiceImpl<TransducerMapper, Transducer> implements TransducerService {
}
