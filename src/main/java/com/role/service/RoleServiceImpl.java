package com.role.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.role.entity.RoleEntity;
import com.role.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService{
}
