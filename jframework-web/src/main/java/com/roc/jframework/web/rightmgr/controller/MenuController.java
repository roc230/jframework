package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.web.rightmgr.entity.SysMenu;
import com.roc.jframework.web.rightmgr.service.IMenuService;
import com.roc.jframework.web.rightmgr.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/menu")
@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/all")
    @ResponseBody
    public List<SysMenu> getAllEnable(){
        return this.menuService.getAllEnable();
    }

    @RequestMapping("/by/{loginName}")
    @ResponseBody
    public List<MenuVO> getMenusByLoginName(@PathVariable("loginName") String loginName){
        List<SysMenu> menus = this.menuService.getMenusByLoginName(loginName);
        List<MenuVO> menuVOs = new ArrayList<>();
        for(SysMenu menu : menus){
            MenuVO vo = new MenuVO.Builder()
                    .id(menu.getId())
                    .enable(menu.getEnable())
                    .icon(menu.getIcon())
                    .description(menu.getDescription())
                    .name(menu.getName())
                    .parentId(menu.getParent() == null ? null : menu.getParent().getId())
                    .systemId(null)
                    .type(menu.getType().name())
                    .url(menu.getUrl())
                    .build();
            menuVOs.add(vo);
        }
        return menuVOs;
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public SysMenu getById(@PathVariable("id") String id){
        return this.menuService.getById(id);
    }
}
