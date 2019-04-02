package com.roc.jframework.web.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@PathVariable("name") String name){
        return "hi: " + name;
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String a(){
        return "test";
    }

   @PostMapping(path = "/post")
   @ResponseBody
    public String testpost(Info info){
        return info.getUsername() + " : " + info.getPassword();
    }

    public class Info{
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
