package com.roc.jframework.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.basic.utils.UrlUtils;
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
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class FictionlogLoginCommonCrawler extends AbstractCrawler {

    public static FictionlogLoginCommonCrawler create(){
        return new FictionlogLoginCommonCrawler();
    }

    @Override
    public void execute(String url) {
        WebDriver driver = WebDriverBuilder.create()
                .userAgent(UserAgent.CHROME)
                .loadImg(false)
                .headless(true)
                .maximiazeWindow(false)
                .driverPath(DriverPath.CHROME_DRIVER_PATH)
                .buildChrome();

        if(login){
            login(driver, username, password);
        }

        Novel novel = this.getDirectories(driver, url);

        List<Chapter> chapters = this.getChapters(driver, novel.getDirectories());

        novel.setChapters(chapters);

        //save as json
        saveAsJson(novel, new File("d:/novel1/" + novel.getName() + ".json"));
        //save as txt
        saveAsTxt(novel, new File("d:/novel1/" + novel.getName() + ".txt"));


        System.out.println("end!");
    }

    private void login(WebDriver driver, String username, String password){
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.get("https://fictionlog.co/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".WithoutLoginNavbar__ButtonBox-sc-1wcn44i-0")));

        WebElement loginBtn = SeleniumUtils.findElement(driver, By.cssSelector(".WithoutLoginNavbar__ButtonBox-sc-1wcn44i-0"));
        if(loginBtn == null){
            return ;
        }
        loginBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".Button__StyledButton-n7uws9-0")));

        SeleniumUtils.findElement(driver, By.cssSelector("#login-username")).sendKeys(username);
        SeleniumUtils.findElement(driver, By.cssSelector("#login-password")).sendKeys(password);
        SeleniumUtils.findElement(driver, By.cssSelector("button[data-gtag-type=loginWithEmail]")).click();
        TimerUtils.sleep(1000);
    }


    private List<Chapter> getChapters(WebDriver driver, List<Directory> directories){
        if(start < 0 || max > directories.size()){
            System.out.println("参数错误");
            return null;
        }

        WebDriverWait wait = new WebDriverWait(driver, 30);

        List<Chapter> chapters = ListUtils.newArrayList();
        for(int i = start; i < (start+max); i++){
            Directory directory = directories.get(i);
            System.out.println("\n开始抓取章节: " + directory.getName() + "\n");
            driver.get(directory.getUrl());
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".RenderContentRawState__ContentRawStateElement-sc-197d66b-0")));

            //get title
            WebElement titleE = SeleniumUtils.findElement(driver, By.cssSelector(".ChapterTitle__ChapterTitleItem-vjl9pv-1"));
            if(titleE == null){
                return null;
            }
            String title = titleE.getText();

            //get paragraph
            List<WebElement> plist = SeleniumUtils.findElements(driver, By.cssSelector(".RenderContentRawState__ContentRawStateElement-sc-197d66b-0 p"));
            if(ListUtils.isNullOrEmpty(plist)){
                return null;
            }

            List<String> parapraphs = ListUtils.newArrayList();
            for(WebElement p : plist){
                String paragraph = null;
                WebElement strong = SeleniumUtils.findElement(p, By.cssSelector("strong"));
                WebElement em = SeleniumUtils.findElement(p, By.cssSelector("em"));
//                WebElement a = SeleniumUtils.findElement(p, By.cssSelector("a"));
                if(strong != null){
                    paragraph = strong.getText();
                }else if(em != null){
                    paragraph = em.getText();
                }else{
                    paragraph = p.getText();
                }

                if(!StringUtils.isNullOrEmpty(paragraph)){
                    parapraphs.add(paragraph);
                }
            }

            Chapter chapter = new Chapter();
            chapter.setTitle(title);
            chapter.setParagraphs(parapraphs);

            chapters.add(chapter);

            System.out.println("-->已完成：" + title);
        }

        return chapters;
    }

    private Novel getDirectories(WebDriver driver, String url){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".TableOfContent__ChapterListSection-cl18wo-0")));

        WebElement e = SeleniumUtils.findElement(driver, By.cssSelector(".BookHeader__BookTitle-sc-7bc82j-5"));
        if(e == null){
            return null;
        }
        String novelName = e.getText();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        List<Directory> directories = ListUtils.newArrayList();
        //下拉列表
        List<WebElement> exlist = SeleniumUtils.findElements(driver, By.cssSelector(".TableOfContent__ChapterListSection-cl18wo-0 > .GroupChapterRow__GroupWrapper-sc-1crfeny-1"));
        if(ListUtils.isNullOrEmpty(exlist)){
            return null;
        }
        for(int i = 0; i < exlist.size(); i++){
            if(i != 0){
                exlist.get(i).click();
                TimerUtils.sleep(200);
            }
        }

        List<WebElement> cclist = SeleniumUtils.findElements(driver, By.cssSelector(".TableOfContent__ChapterListSection-cl18wo-0  .GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__RightItems-r5e2sp-6 > a"));
        if(ListUtils.isNullOrEmpty(cclist)){
            return null;
        }
//        for(WebElement ee : cclist){
//            System.out.println(ee.getText());
//        }

//        List<WebElement> alist = SeleniumUtils.findElements(driver, By.cssSelector(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a"));
//        if(ListUtils.isNullOrEmpty(alist)){
//            return null;
//        }
        for(int i = 0; i < cclist.size(); i++){
            if(i%2 == 0){
                WebElement we = cclist.get(i);
                String href = we.getAttribute("href");
                String title = "";
                WebElement ti = SeleniumUtils.findElement(we, By.cssSelector("div"));
                if(ti != null){
                    title = ti.getText();
                }
                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);

                directories.add(dir);
            }
        }

        novel.setDirectories(directories);
        return novel;

       /* String html = RequestsBuilder.create()
                .userAgent(UserAgent.CHROME)
                .url(url)
                .contentType("text/plain; charset=utf-8")
                .header("access-control-allow-origin", "https://fictionlog.co")
                .getAsString();

        Document doc = JsoupUtils.parse(html);

        String novelName = doc.selectFirst(".BookHeader__BookTitle-sc-7bc82j-5").text();
        Novel novel = new Novel();
        novel.setName(novelName);
        novel.setUrl(url);

        String schemaAndDomain = UrlUtils.getSchemaAndDomain(url);
        List<Directory> directories = ListUtils.newArrayList();
        Elements alist = doc.select(".GroupChapterRow__GroupChapterList-sc-1crfeny-0 .ChapterRow__MainWrapper-r5e2sp-8 > div > div > a");
        for(int i = 0; i < alist.size(); i++){
            if(i%2 == 0){
                Element a = alist.get(i);
                String href = schemaAndDomain + a.attr("href");
                String title = a.selectFirst("div").text();

                Directory dir = new Directory();
                dir.setUrl(href);
                dir.setName(title);

                directories.add(dir);
            }
            i++;
        }
        novel.setDirectories(directories);
        return novel;*/
    }
}
