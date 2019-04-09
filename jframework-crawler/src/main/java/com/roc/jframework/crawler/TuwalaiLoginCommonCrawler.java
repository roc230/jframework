package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.FileUtils;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.crawler.entity.Chapter;
import com.roc.jframework.crawler.entity.Directory;
import com.roc.jframework.crawler.entity.Novel;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TuwalaiLoginCommonCrawler extends AbstractCrawler{

    public static TuwalaiLoginCommonCrawler create(){
        return new TuwalaiLoginCommonCrawler();
    }

    /**
     * 设置开始章节，从0开始算
     * @param start
     * @return
     */
    public TuwalaiLoginCommonCrawler start(Integer start){
        this.start = start;
        return this;
    }

    /**
     * 设置抓取的章节数量
     * @param max
     * @return
     */
    public TuwalaiLoginCommonCrawler max(Integer max){
        this.max = max;
        return this;
    }

    /**
     * 设置是否以追加的方式保存内容
     * @param append
     * @return
     */
    public TuwalaiLoginCommonCrawler append(Boolean append){
        this.append = append;
        return this;
    }

    /**
     * 执行抓取
     * @param url
     */
    public void execute(String url){
        WebDriver driver = null;
        Novel novel;
        List<Directory> directories;
        List<Chapter> chapters;
        try {
            driver = WebDriverBuilder.create()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .headless(true)
                    .loadImg(false)
                    .userAgent(UserAgent.CHROME)
                    .buildChrome();

            if(login){
                if(StringUtils.isAnyNullOrEmpty(username, password)){
                    System.out.println("请设置登陆用户名和密码");
                    return ;
                }
                login(driver, username, password);
            }

            driver.get(url);
            TimerUtils.sleep(2000);

            String novelName = null;
            try {
                WebElement p = driver.findElement(By.cssSelector("#story-content p[title]"));
                novelName = p.getAttribute("title");
            } catch (Exception e) {
                e.printStackTrace();
            }

            novel = new Novel();
            novel.setName(novelName);
            novel.setUrl(url);

            directories = getDirectories(driver);

            chapters = getChapters(driver, directories);
        } finally {
            if(driver != null){
                driver.quit();
            }
        }

        novel.setDirectories(directories);
        novel.setChapters(chapters);

//        String json = JsonUtils.toString(novel);
//        FileUtils.saveAsFile(json, "d:/novel1/" + novel.getName() + ".json", true);

        saveAsJson(novel, new File("d:/novel1/" + novel.getName() + ".json"));
//        saveAsTxt(novel);
        saveAsTxt(novel, new File("d:/novel1/" + novel.getName() + ".txt"));

    }

    public void login(WebDriver driver, String username, String password){
        String loginUrl = "https://accounts.ookbee.com/ServiceLogin?AppCode=TUNWALAI_209&RedirectUrl=http%3a%2f%2fwww.tunwalai.com%2fhome%2flogin%3fReturnUrl%3dhttp%253A%252F%252Fwww.tunwalai.com%252F";
        driver.get(loginUrl);
        TimerUtils.sleep(200);
        WebElement userNameElement = SeleniumUtils.findElement(driver, By.cssSelector("#UserName"));
        if(userNameElement == null){
            System.out.println("找不到帐号输入框!");
            return ;
        }
        WebElement passwordElement = SeleniumUtils.findElement(driver, By.cssSelector("#Password"));
        if(passwordElement == null){
            System.out.println("找不到密码输入框!");
            return ;
        }
        WebElement submitBtn = SeleniumUtils.findElement(driver, By.cssSelector("button[type=submit]"));
        if(submitBtn == null){
            System.out.println("找不到提交按钮!");
            return ;
        }

        userNameElement.sendKeys(username);
        TimerUtils.sleep(200);
        passwordElement.sendKeys(password);
        TimerUtils.sleep(200);
        submitBtn.click();
        TimerUtils.sleep(500);
    }


/*
    public void saveAsTxt(Novel novel){
        StringBuffer sb = new StringBuffer();
        for(Chapter chapter : novel.getChapters()){
            //章节标题
            String name = chapter.getTitle();
            sb.append(name).append("\r\n").append("\r\n");
            //内容段落
            List<String> paragraphs = chapter.getParagraphs();
            for(String p : paragraphs){
                sb.append("\0\0\0\0\0\0\0\0")
                        .append(p)
                        .append("\r\n")
                        .append("\r\n");
            }
        }
        FileUtils.saveAsFile(sb.toString(), "d:/novel1/" + novel.getName() + ".txt", !append);
    }*/

    /**
     * 获取章节内容
     * @param driver
     * @param directories
     * @return
     */
    public List<Chapter> getChapters(WebDriver driver, List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
            return null;
        }
        List<Chapter> chapters = ListUtils.newArrayList();

        for(int i = start; i < max; i++){
            Directory dir = directories.get(i);
            driver.get(dir.getUrl());
            TimerUtils.sleep(2000);

            List<WebElement> plist = SeleniumUtils.findElements(driver, By.cssSelector("#story-detail p"));
            if(ListUtils.isNullOrEmpty(plist)){
                return null;
            }
            List<String> paragraphs = ListUtils.newArrayList();
            for(WebElement p : plist){

                String ptxt = p.getText();
                ptxt = ptxt.trim();
//                if(ptxt.startsWith("\"")){
//                    ptxt = ptxt.substring(1);
//                }
//                if(ptxt.endsWith("\"")){
//                    ptxt = ptxt.substring(0, ptxt.length() -1);
//                }

                WebElement em = SeleniumUtils.findElement(p, By.cssSelector("em"));
                if(em != null){
                    String txt = em.getText();
                    ptxt += txt;
                }

                paragraphs.add(ptxt);
            }

            Chapter chapter = new Chapter();
            chapter.setParagraphs(paragraphs);
            chapter.setTitle(dir.getName());
            chapters.add(chapter);
            System.out.println("-->已完成：" + chapter.getTitle());
        }
        return chapters;
    }

    /**
     * 获取章节目录
     * @param driver
     * @return
     */
    public List<Directory> getDirectories(WebDriver driver){

        try {
            List<WebElement> alist = driver.findElements(By.cssSelector("#tbody a"));
            if(alist == null){
                return null;
            }

            List<Directory> directories = ListUtils.newArrayList();
            for(WebElement a : alist){
                String href = a.getAttribute("href");
                String title = a.getAttribute("title");

                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);
                directories.add(dir);
            }
            return directories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
