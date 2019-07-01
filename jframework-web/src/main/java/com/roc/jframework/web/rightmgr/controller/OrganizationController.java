package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.basic.ext.KeyValue;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.accountmgr.entity.OrgType;
import com.roc.jframework.web.rightmgr.entity.OrganizationType;
import com.roc.jframework.web.rightmgr.entity.SysOrganization;
import com.roc.jframework.web.rightmgr.service.IOrganizationService;
import com.roc.jframework.web.rightmgr.vo.OrgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/org")
@Controller
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @PostMapping("/save")
    @ResponseBody
    public String saveOrg(@RequestBody OrgVO orgVO){
        SysOrganization o = new SysOrganization();
        o.setId(null);
        o.setDescription(orgVO.getDescription());
        o.setName(orgVO.getName());
        o.setCode(orgVO.getCode());
        o.setType(OrganizationType.valueOf(orgVO.getType()));
        this.organizationService.add(o, orgVO.getParentId(), null);
        return "success";
    }

    @GetMapping("/tree")
    @ResponseBody
    public List<OrgVO> getOrgTree(){
        List<SysOrganization> orglist = this.organizationService.getAllEnable();

        List<OrgVO> volist = toVO(orglist);

        // select root element
        List<OrgVO> roots = new ArrayList<>();
        for(OrgVO vo : volist){
            if(StringUtils.isNullOrEmpty(vo.getParentId())){
                roots.add(vo);
            }
        }
        volist.removeAll(roots);

        //set children
        List<OrgVO> levelvos = new ArrayList<>(roots);
        while(volist.size() > 0){
            List<OrgVO> tmplist = new ArrayList<>();
            for(OrgVO vo : levelvos){
                for(OrgVO v : volist){
                    if(v.getParentId().equals(vo.getId())){
                        vo.getChildren().add(v);
                        tmplist.add(v);
                    }
                }
            }
            volist.removeAll(tmplist);
            levelvos = new ArrayList<>(tmplist);
        }

        return roots;
    }

    private OrgVO toVO(SysOrganization org){
        OrgVO vo = new OrgVO();
        vo.setId(org.getId());
        vo.setName(org.getName());
        vo.setCode(org.getCode());
        vo.setLabel(org.getName());
        vo.setDescription(org.getDescription());
        vo.setType(org.getType().name());
        vo.setParentId(org.getParent() != null ? org.getParent().getId() : null);
        return vo;
    }

    private List<OrgVO> toVO(List<SysOrganization> orgs){
        List<OrgVO> list = new ArrayList<>();
        for(SysOrganization org : orgs){
            list.add(toVO(org));
        }
        return list;
    }

    @GetMapping("/types")
    @ResponseBody
    public List<KeyValue> getAllOrgTypes(){
        List<KeyValue> kvs = new ArrayList<>();
        for(OrganizationType t : OrganizationType.values()){
            if(t.equals(OrganizationType.COMPANY)){
                kvs.add(new KeyValue(t.name(), "公司"));
            }else if(t.equals(OrganizationType.SUB_COMPANY)){
                kvs.add(new KeyValue(t.name(), "子公司"));
            }else if(t.equals(OrganizationType.DEPARTMENT)){
                kvs.add(new KeyValue(t.name(), "部门"));
            }else if(t.equals(OrganizationType.SUB_DEPARTMENT)){
                kvs.add(new KeyValue(t.name(), "子部门"));
            }else if(t.equals(OrganizationType.STATION)){
                kvs.add(new KeyValue(t.name(), "岗位"));
            }
        }
        return kvs;
    }
}
