package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.basic.utils.StringUtils;
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
                    .index(menu.getIndexNo())
                    .build();
            menuVOs.add(vo);
        }
        //选出顶级菜单
        List<MenuVO> top = new ArrayList<>();
        for(MenuVO m : menuVOs){
            if(StringUtils.isNullOrEmpty(m.getParentId())){
                top.add(m);
            }
        }
        //设置子菜单
        for(MenuVO p : top){
            for(MenuVO m : menuVOs){
                if(StringUtils.isNullOrEmpty(m.getParentId())){
                    continue;
                }
                if(m.getParentId().equals(p.getId())){
                    p.addChild(m);
                }
            }
        }
        return top;
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public SysMenu getById(@PathVariable("id") String id){
        return this.menuService.getById(id);
    }

    @RequestMapping("/children/{id}")
    @ResponseBody
    public List<SysMenu> getChildren(@PathVariable("id") String menuId){
        return this.menuService.getChildren(menuId);
    }

    @RequestMapping("/root")
    @ResponseBody
    public List<SysMenu> getRootMenus(){
        return this.menuService.getRootMenus();
    }
}
