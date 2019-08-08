package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.entity.ResultCode;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.service.ILoginService;
import com.roc.jframework.web.utils.JWTUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private ISysAccountDAO sysAccountDAO;

    @Override
    public ExeResult<Boolean> existsLoginName(String loginName) {
        return null;
    }

    @Override
    public ExeResult<String> login(String loginName, String password) {
        if(StringUtils.isAnyNullOrEmpty(loginName, password)){
            return ExeResult.of(ResultCode.EMPTY_LOGIN_NAME_OR_PASSWORD, "");
        }
        SysAccount account = this.sysAccountDAO.getByLoginName(loginName);
        if(account == null){
            return ExeResult.of(ResultCode.NO_QUERY_RESULT, null);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enPwd = passwordEncoder.encode(password);
        //login success
        if(enPwd.equals(account.getPassword())){
            //7 days token
            String jwtoken = JWTUitls.createJWT(account.getId(), account.getLoginName(), 60*1000*60*24*7);
            return ExeResult.of(ResultCode.SUCCESS, null, jwtoken);
        }else{
            return ExeResult.of(ResultCode.WRONG_PASSWORD, "wrong password");
        }
    }
}
