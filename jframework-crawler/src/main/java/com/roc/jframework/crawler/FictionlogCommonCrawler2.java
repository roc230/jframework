package com.roc.jframework.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.core.component.httpclient.RequestsBuilder;
import com.roc.jframework.core.utils.JsoupUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FictionlogCommonCrawler2 extends AbstractCrawler {

    public static FictionlogCommonCrawler2 create(){
        return new FictionlogCommonCrawler2();
    }

    @Override
    public void execute(String url) {

        WebDriver driver = WebDriverBuilder.create()
                .userAgent(UserAgent.CHROME)
                .loadImg(true)
                .headless(false)
                .driverPath(DriverPath.CHROME_DRIVER_PATH)
                .buildChrome();

        login(driver, "roc230", "czp840527");

        Novel novel = getDirectories(driver, url);


        System.out.println("end!");
    }

    private void login(WebDriver driver, String userName, String password){
        driver.get("https://fictionlog.co/");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginBtn = SeleniumUtils.findElement(driver, By.cssSelector(".WithoutLoginNavbar__LoginButton-sc-1wcn44i-1"));
        if(loginBtn == null){
            System.out.println("查找登陆按钮失败!");
            return ;
        }

        loginBtn.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement userNameInput = SeleniumUtils.findElement(driver, By.cssSelector("#login-username"));
        if(userNameInput == null){
            System.out.println("查找用户名输入框失败！");
            return ;
        }

        WebElement passwordInput = SeleniumUtils.findElement(driver, By.cssSelector("#login-password"));
        if(passwordInput == null){
            System.out.println("查找密码输入框失败！");
            return ;
        }

        WebElement submitBtn = SeleniumUtils.findElement(driver, By.linkText("เข้าสู่ระบบ"));
        if(submitBtn == null){
            System.out.println("查找登陆提交按钮失败！");
            return ;
        }

        userNameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        submitBtn.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private List<Chapter> getChapters(List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
        }

        List<Chapter> chapters = ListUtils.newArrayList();
        for(int i = 0; i < max; i++){
            Directory directory = directories.get(i);
            System.out.println("\n开始抓取章节: " + directory.getName() + "\n");
            String html = RequestsBuilder.create()
                    .userAgent(UserAgent.CHROME)
                    .url(directory.getUrl())
                    .getAsString();
            Document doc = JsoupUtils.parse(html);

            Element js = doc.selectFirst("#__NEXT_DATA__");
            String json = js.html();
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            JsonObject je = obj.get("props").getAsJsonObject()
                    .get("serverState").getAsJsonObject();

            JsonObject first = null;
            if(je.entrySet().size() > 0){
                first = je.entrySet().iterator().next().getValue().getAsJsonObject();
            }

            String title = first.get("title").getAsString();
            List<String> parapraghs = ListUtils.newArrayList();
            JsonArray blocks = first.getAsJsonObject("contentRawState")
                    .getAsJsonObject("json")
                    .getAsJsonArray("blocks");
            for(JsonElement e : blocks){
                JsonObject jo = e.getAsJsonObject();
                String p = jo.get("text").getAsString();

                if(p.startsWith("----------")){
                    break;
                }
                if(!StringUtils.isNullOrEmpty(p)){
                    parapraghs.add(p);
                }
            }

            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setParagraphs(parapraghs);

            chapters.add(chapter);

        }

        return chapters;
    }

    private Novel getDirectories(WebDriver driver, String url){
        driver.get(url);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement novelNameElement = SeleniumUtils.findElement(driver, By.cssSelector(".BookHeader__BookTitle-sc-7bc82j-5"));
        if(novelNameElement == null){
            return null;
        }
        String novelName = novelNameElement.getText();

        return null;

/*
        String html = RequestsBuilder.create()
                .userAgent(UserAgent.CHROME)
                .url(url)
                .getAsString();

        Document doc = JsoupUtils.parse(html);

        String novelName = doc.selectFirst(".BookHeader__BookTitle-sc-7bc82j-5").text();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        List<Directory> directories = ListUtils.newArrayList();
        Elements alist = doc.select(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a");
        for(Iterator<Element> iter = alist.iterator(); iter.hasNext(); ){
            Element a = iter.next();
            String href = schemaAndDomain + a.attr("href");
            String title = a.selectFirst("div").text();

            Directory dir = new Directory();
            dir.setUrl(href);
            dir.setName(title);

            directories.add(dir);
        }
        novel.setDirectories(directories);
        return novel;*/
    }
}
