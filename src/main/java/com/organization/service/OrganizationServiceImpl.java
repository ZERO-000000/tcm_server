package com.organization.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.organization.entity.OrganizationEntity;
import com.organization.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper,OrganizationEntity> implements OrganizationService{
}