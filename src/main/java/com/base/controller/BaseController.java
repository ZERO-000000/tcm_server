package com.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.entity.AbstractEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author smalldeng
 * @param <BaseService>
 * @param <T>
 */
public class BaseController<BaseService extends IService,T extends AbstractEntity>{
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected BaseService baseService;



    /**
     *
     * @param t
     * @return
     */
    @RequestMapping("/selectByMap")
    public List<T> selectByMap(@Param("entity") T t){
        t.setVersion(null);
        QueryWrapper<T> queryWrapper=new QueryWrapper();
        queryWrapper.setEntity(t);
        List<T> ts=baseService.list(queryWrapper);
        return ts;
    }

    /**
     *
     * @param t
     * @return
     */
    @RequestMapping("/save")
    public String insert(@Param("entity") T t){
        try{
            baseService.saveOrUpdate(t);
            return "单条插入成功";
        }catch(Exception e){
            e.printStackTrace();
            return "单条插入失败";
        }
    }

    @RequestMapping("/removeById")
    public String removeById(@Param("id") Long id){
        try{
            baseService.removeById(id);
            return "单条删除成功";
        }catch(Exception e){
            e.printStackTrace();
            return "单条删除失败";
        }
    }

    /**
     * 将HttpServletRequest和response封装到ServletWebRequest中
     * @return
     */
    public ServletWebRequest getServletWebRequest(){
        return new ServletWebRequest(request,response);
    }
}
