package com.role.controller;

import com.base.controller.BaseController;
import com.role.entity.RoleEntity;
import com.role.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RoleService, RoleEntity> {
}