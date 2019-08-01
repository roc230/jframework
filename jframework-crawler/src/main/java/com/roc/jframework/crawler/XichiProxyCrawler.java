package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.core.component.myhttpclient.HttpRequests;
import com.roc.jframework.core.utils.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 西刺代理-高匿代理爬虫
 */
public class XichiProxyCrawler {

    public List<Proxy> execute(String url){
        String html = HttpRequests.create()
                .url("https://www.xicidaili.com/nn")
                .userAgent(UserAgent.CHROME)
                .method("get")
                .execute()
                .getContent();
//        System.out.println(html);

        Document doc = JsoupUtils.parse(html);
//        Element iplist = doc.selectFirst("#ip_list");
//        System.out.println(iplist.html());

        Elements trlist = doc.select("#ip_list >tbody > tr");
        List<Proxy> plist = new ArrayList<>();
        for(int i = 1; i < trlist.size(); i++){
           Elements tdlist = trlist.get(i).select("td");
           if(tdlist.size() < 10){
               continue;
           }
           Proxy p = new Proxy();
           p.ip = tdlist.get(1).text();
           p.port = tdlist.get(2).text();
           p.serverAddr = tdlist.get(3).text();
           p.type = tdlist.get(5).text();
           p.speed = tdlist.get(6).selectFirst("div").attr("title");
           p.cnnTime = tdlist.get(7).selectFirst("div").attr("title");
           p.aliveTime = tdlist.get(8).text();
           p.validateTime = tdlist.get(9).text();
           plist.add(p);
        }
        return plist;
    }

    public static class Proxy{
        String ip;
        String port;
        String serverAddr;
        String type;
        String speed;
        String cnnTime;
        String aliveTime;
        String validateTime;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getServerAddr() {
            return serverAddr;
        }

        public void setServerAddr(String serverAddr) {
            this.serverAddr = serverAddr;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getCnnTime() {
            return cnnTime;
        }

        public void setCnnTime(String cnnTime) {
            this.cnnTime = cnnTime;
        }

        public String getAliveTime() {
            return aliveTime;
        }

        public void setAliveTime(String aliveTime) {
            this.aliveTime = aliveTime;
        }

        public String getValidateTime() {
            return validateTime;
        }

        public void setValidateTime(String validateTime) {
            this.validateTime = validateTime;
        }
    }
}
