package com.organization.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.controller.BaseController;
import com.organization.entity.OrganizationEntity;
import com.organization.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/org")
public class OrganizationController extends BaseController<OrganizationService,OrganizationEntity> {
    public Logger logger= LoggerFactory.getLogger(getClass());
    @Resource
    public OrganizationService organizationService;

    /**
     * 组装组织机构树数据
     * @return
     */
    @RequestMapping("/findOrgTreeInfo")
    public List findOrgTreeInfo(){
        Long rootParentSid=-999L;
        List treeData=createTreeData(rootParentSid);
        logger.info(treeData.toString());
        return treeData;
    }
    private List createTreeData(Long parentSid){
        List treeDataList=new ArrayList();
        QueryWrapper<OrganizationEntity> queryWrapper=new QueryWrapper();
        queryWrapper.eq("PARENT_SID",parentSid);
        List<OrganizationEntity> orgs=organizationService.list(queryWrapper);
        if (orgs.size()>0){
            for(OrganizationEntity org:orgs){
                Map<String,Object> treeData=new HashMap<String,Object>();
                treeData.put("label",org.getOrgName());
                treeData.put("id",org.getSid());
                List children=createTreeData(org.getSid());
                if (children.size()>0){
                    treeData.put("children",children);
                }
                treeDataList.add(treeData);
            }
        }
        return treeDataList;
    }
    @RequestMapping("/deleteOrgCascade")
    private String deleteOrgCascade(Long sid){
        try{
            QueryWrapper<OrganizationEntity> queryWrapper=new QueryWrapper();
            queryWrapper.eq("PARENT_SID",sid);
            List<OrganizationEntity> orgs=organizationService.list(queryWrapper);
            if(orgs.size()>0){
                for(OrganizationEntity org:orgs){
                    deleteOrgCascade(org.getSid());
                }
            }else{
                organizationService.removeById(sid);
            }
        }catch(Exception e){
            return "节点包括子节点全部删除失败";
        }
        return "节点包括子节点全部删除成功";
    }

}
