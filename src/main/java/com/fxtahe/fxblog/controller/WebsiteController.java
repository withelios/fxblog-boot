package com.fxtahe.fxblog.controller;


import com.fxtahe.fxblog.config.annotation.OperationLog;
import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import com.fxtahe.fxblog.entity.Website;
import com.fxtahe.fxblog.service.WebsiteService;
import com.fxtahe.fxblog.util.Const;
import com.fxtahe.fxblog.vo.wrapper.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fxtahe
 * @since 2020-08-26
 */
@Slf4j
@ResponseWrapper
@RestController
@RequestMapping("/website")
public class WebsiteController {


    @Resource
    private WebsiteService websiteService;

    /**
     * 获取网站信息
     * @return Result
     */
    @GetMapping("/get")
    public Website getWebsite(){
        return websiteService.getById(1);
    }


    /**
     * 更新网站信息
     * @param website
     * @return
     */
    @OperationLog(operationModule = Const.WEBSITE_TYPE,operationType = Const.OPERA_TYPE_UPDATE,operationDesc = "更新网站信息")
    @PutMapping("/admin/update")
    public Result updateWebsite(@RequestBody Website website){
        websiteService.updateById(website.setId(1));
        return Result.success();
    }


}

