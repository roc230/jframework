package com.roc.jframework.crawler;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.Serializable;
import java.util.List;

public class Baiduyuedu implements IBaiduyuedu{

    @Override
    public CopyRight copyright(String bookname) {
        WebDriver driver = null;
        try {
             driver = WebDriverBuilder.create()
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .userAgent(UserAgent.CHROME)
                    .loadImg(false)
                    .headless(true)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver)
                    .get("https://yuedu.baidu.com")
                    .untilElementPresence(By.cssSelector(".s-submit"));
            WebElement word = wrapper.findElement(By.cssSelector(".s-word"));
            if(word == null){
                return null;
            }
            WebElement submit = wrapper.findElement(By.cssSelector(".s-submit"));
            if(submit == null){
                return null;
            }
            word.sendKeys(bookname);
            submit.click();

            TimerUtils.sleep(2000);
            if(wrapper.findElement(By.cssSelector(".no-result-wrap")) != null){
                System.out.println("没有查询结果！");
                return null;
            }

//            wrapper.untilElementPresence(By.cssSelector(".yd-search-books-content a"));
            WebElement e = wrapper.findElement(By.cssSelector(".yd-search-books-content a"));
            if(e == null){
                System.out.println("没有查询结果， 有相似推荐");
                return null;
            }
            String href = e.getAttribute("href");
            wrapper.get(href);


            wrapper.untilElementPresence(By.cssSelector(".content-block"));

            WebElement block = wrapper.findElement(By.cssSelector(".content-block"));
            if(block == null){
                return null;
            }
            String title = block.findElement(By.cssSelector(".book-title")).getText();
            if(!title.equals(bookname)){
                System.out.println("没查询到指定书籍！");
                return null;
            }
            List<WebElement> lis = block.findElements(By.cssSelector(".doc-info-org li"));
            if(ListUtils.isNullOrEmpty(lis) || lis.size() != 3){
                return null;
            }
            String author = lis.get(0).findElement(By.tagName("a")).getText();
            String owner = lis.get(1).findElement(By.tagName("a")).getText();
            String tags = lis.get(2).findElement(By.tagName("a")).getAttribute("title");
            String coverImg = wrapper.findElement(By.cssSelector(".cover-block a img")).getAttribute("src");
            String readCount=  wrapper.findElement(By.cssSelector(".doc-info-read-count")).getText();
            readCount = StringUtils.findByReg(readCount, "([\\d]+)", 1);
            String price = wrapper.findElement(By.cssSelector(".confirm-price .numeric")).getText();
            price = StringUtils.findByReg(price, "([\\d\\.]+)", 1);
            String category = wrapper.findElement(By.cssSelector("#page-curmbs")).getText();
            category = category.replaceAll("\\n", "/");
            String rolltext = wrapper.findElement(By.cssSelector(".roll-text-area .roll-txt")).getText();
            String wordCount = StringUtils.findByReg(rolltext, "([\\d]+)个字", 1);
            String chapterCount = StringUtils.findByReg(rolltext, "([\\d]+)个章节", 1);

            CopyRight cr = new CopyRight();
            cr.setBookName(title);
            cr.setTags(tags);
            cr.setOwner(owner);
            cr.setAuthor(author);
            cr.setCoverImg(coverImg);
            cr.setReadCount(Integer.valueOf(readCount));
            cr.setPrice(Double.valueOf(price));
            cr.setCategory(category);
            cr.setChapterCount(Integer.valueOf(chapterCount));
            cr.setWordCount(Integer.valueOf(wordCount));
            return cr;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null){
                driver.quit();
            }
        }

        return null;
    }

    public static class CopyRight implements Serializable {
        private String bookName;
        private String author;
        private String owner;
        private String tags;
        private String coverImg;
        private Integer readCount;
        private Double price;
        private String category;
        private Integer wordCount;
        private Integer chapterCount;

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public Integer getReadCount() {
            return readCount;
        }

        public void setReadCount(Integer readCount) {
            this.readCount = readCount;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getWordCount() {
            return wordCount;
        }

        public void setWordCount(Integer wordCount) {
            this.wordCount = wordCount;
        }

        public Integer getChapterCount() {
            return chapterCount;
        }

        public void setChapterCount(Integer chapterCount) {
            this.chapterCount = chapterCount;
        }
    }
}
