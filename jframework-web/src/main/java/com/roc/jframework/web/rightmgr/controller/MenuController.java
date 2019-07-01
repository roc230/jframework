package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.basic.ext.KeyValue;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.entity.MenuType;
import com.roc.jframework.web.rightmgr.entity.SysMenu;
import com.roc.jframework.web.rightmgr.service.IMenuService;
import com.roc.jframework.web.rightmgr.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/tree")
    @ResponseBody
    public List<MenuVO> getMenuTree(){
        List<SysMenu> all = this.menuService.getAllEnable();

        //root
        List<MenuVO> root = new ArrayList<>();
        for(SysMenu m : all){
            if(m.getParent() == null){
                MenuVO vo = new MenuVO.Builder()
                        .url(m.getUrl())
                        .type(m.getType().name())
                        .parentId(m.getParent() != null ? m.getParent().getId(): null)
                        .name(m.getName())
                        .description(m.getDescription())
                        .icon(m.getIcon())
                        .enable(m.getEnable())
                        .id(m.getId())
                        .index(m.getIndexNo())
                        .build();
                root.add(vo);
            }
        }
        //level second
        List<MenuVO> levelSecond = new ArrayList<>();
        for(MenuVO vo : root){
            for(SysMenu m : all){
                if(m.getParent() != null && vo.getId().equals(m.getParent().getId())){
                    MenuVO child = new MenuVO.Builder()
                            .description(m.getDescription())
                            .enable(m.getEnable())
                            .icon(m.getIcon())
                            .id(m.getId())
                            .index(m.getIndexNo())
                            .name(m.getName())
                            .parentId(m.getParent().getId())
                            .type(m.getType().name())
                            .url(m.getUrl())
                            .parentId(m.getParent().getId())
                            .parentName(m.getParent().getName())
                            .build();
                    vo.addChild(child);
                    levelSecond.add(child);
                }
            }
        }
        //level three
        for(MenuVO vo : levelSecond){
            for(SysMenu m : all){
                if(m.getParent() != null && vo.getId().equals(m.getParent().getId())){
                    MenuVO child = new MenuVO.Builder()
                            .description(m.getDescription())
                            .enable(m.getEnable())
                            .icon(m.getIcon())
                            .id(m.getId())
                            .index(m.getIndexNo())
                            .name(m.getName())
                            .parentId(m.getParent().getId())
                            .type(m.getType().name())
                            .url(m.getUrl())
                            .build();
                    vo.addChild(child);
                }
            }
        }
        levelSecond.clear();
        levelSecond = null;
        return root;
    }

    @RequestMapping("/parentTree")
    @ResponseBody
    public List<MenuVO> getParentTree(){
        List<MenuVO> root = this.getMenuTree();
        for(MenuVO m : root){
            removeActionChild(m);
            if(m.getChildren() != null){
                for(MenuVO m1 : m.getChildren()){
                    removeActionChild(m1);
                }
            }
        }
        return root;
    }

    private void removeActionChild(MenuVO menu){
        if(menu != null && menu.getChildren() != null){
            List<MenuVO> tmp = new ArrayList<>();
            for(MenuVO m : menu.getChildren()){
                if(!m.getType().equals(MenuType.ACTION.name())){
                    tmp.add(m);
                }
            }
            menu.setChildren(tmp);
        }
    }

    /**
     * 获取所有菜单类型
     * @return
     */
    @RequestMapping("/menutypes")
    @ResponseBody
    public List<KeyValue> getAllMenuType(){
        List<KeyValue> kvs = new ArrayList<>();
        for(MenuType mt : MenuType.values()){
            KeyValue kv = new KeyValue();
            if(mt.name().equals("MENU")){
                kv.setKey(mt.name());
                kv.setValue("菜单");
                kvs.add(kv);
            }else if(mt.name().equals("ACTION")){
                kv.setKey(mt.name());
                kv.setValue("操作");
                kvs.add(kv);
            }else if(mt.name().equals("SUB_MENU")){
                kv.setKey(mt.name());
                kv.setValue("子菜单");
                kvs.add(kv);
            }else if(mt.name().equals("MODULE")){
                kv.setKey(mt.name());
                kv.setValue("模块");
                kvs.add(kv);
            }else if(mt.name().equals("SYSTEM")){
                kv.setKey(mt.name());
                kv.setValue("系统");
                kvs.add(kv);
            }

        }
        return kvs;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody MenuVO menuVO){
        SysMenu menu = new SysMenu();
        menu.setId(null);
        menu.setName(menuVO.getName());
        menu.setType(MenuType.valueOf(menuVO.getType()));
        menu.setUrl(menuVO.getUrl());
        this.menuService.add(menu, menuVO.getParentId());
        return "success";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody MenuVO menuVO){
        SysMenu menu = new SysMenu();
        menu.setId(menuVO.getId());
        menu.setUrl(menuVO.getUrl());
        menu.setType(MenuType.valueOf(menuVO.getType()));
        menu.setName(menuVO.getName());
        menu.setIcon(menuVO.getIcon());
        menu.setDescription(menuVO.getDescription());
        menu.setIndexNo(menuVO.getIndex());

        this.menuService.update(menu, menuVO.getParentId());
        return "success";
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id") String id){
        this.menuService.delete(id);
        return "success";
    }
}
