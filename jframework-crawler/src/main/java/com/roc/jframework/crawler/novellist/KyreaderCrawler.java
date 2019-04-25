package com.roc.jframework.crawler.novellist;

import com.roc.jframework.basic.constants.UserAgent;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.basic.utils.TimerUtils;
import com.roc.jframework.crawler.selenium.DriverPath;
import com.roc.jframework.crawler.selenium.WebDriverBuilder;
import com.roc.jframework.crawler.selenium.WebDriverWrapper;
import com.roc.jframework.crawler.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class KyreaderCrawler {

    /**
     *
     * @param startPageUrl 开始页url,
     * @param pageCount  页数
     * @return
     */
    public List<NovelInfo> getInfo(String startPageUrl, int pageCount){
        WebDriver driver = null;

        try {
            driver = WebDriverBuilder.create()
                    .headless(false)
                    .loadImg(true)
                    .userAgent(UserAgent.CHROME)
                    .driverPath(DriverPath.CHROME_DRIVER_PATH)
                    .buildChrome();
            WebDriverWrapper wrapper = WebDriverWrapper.wrapper(driver);


//            String nextUrl = "https://novel.1789a.com/Update/get_cont_data/sort/%E5%85%A8%E9%83%A8.html";
//            String nextUrl = "https://novel.1789a.com/index.php?s=/Update/get_cont_data/channel/%E5%85%A8%E9%83%A8/p/121.html";
            String nextUrl = startPageUrl;

            List<String> urls = ListUtils.newArrayList();

            int i = 0;
            do{
                System.out.println("进入下一页：" + nextUrl);
                driver.get(nextUrl);
                TimerUtils.sleep(500);
                List<WebElement> alist = driver.findElements(By.cssSelector(".cont_con a"));
                if(ListUtils.isNullOrEmpty(alist)){
                    return null;
                }
                for(WebElement e : alist){
                    WebElement sortE = SeleniumUtils.findElement(e, By.cssSelector(".cont_txt .cont_sort"));
                    if(sortE == null){
                        continue;
                    }
                    String sort = sortE.getText();
                    if(sort.equals("现代言情") || sort.equals("短篇小说")){
                        String url = e.getAttribute("href");
                        urls.add(url);
                        System.out.println("添加url: " + url);
                    }

                }
                WebElement next = wrapper.findElement(By.cssSelector("#example1_next a"));
                if(next == null){
                    nextUrl = null;
                }else{
                    nextUrl = next.getAttribute("href");
                }
                ++i;
            }while(nextUrl != null && i < pageCount);

            List<NovelInfo> nilist = ListUtils.newArrayList();
            for(String url : urls){
                wrapper.get(url);
                TimerUtils.sleep(500);

                WebElement p = wrapper.findElement(By.cssSelector(".infos"));
                if(p == null){
                    continue;
                }

                NovelInfo ni = new NovelInfo();

                WebElement clickE = SeleniumUtils.findElement(p,By.cssSelector("#readVisitor .click"));
                WebElement categoryE = SeleniumUtils.findElement(p, By.cssSelector(".cate a"));
                WebElement wordsE = SeleniumUtils.findElement(p, By.cssSelector(".words"));

                if(clickE != null){
                    String click = clickE.getText();
                    click = StringUtils.findByReg(click, "([\\d]+)", 1);
                    ni.setClickCount(Integer.valueOf(click));
                }

                if(categoryE != null){
                    String category = categoryE.getText();
                    ni.setCategory(category);
                }

                if(wordsE != null){
                    String worlds = wordsE.getText();
                    worlds = StringUtils.findByReg(worlds, "([\\d]+)", 1);
                    ni.setLength(Integer.valueOf(worlds));
                }

                String title = wrapper.findElement(By.cssSelector(".hd h2")).getText();

                String img = wrapper.findElement(By.cssSelector(".book-cover")).getAttribute("src");


                ni.setCoverImg(img);
                ni.setName(title);
                ni.setUrl(url);

                nilist.add(ni);
            }
            return nilist;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(driver != null) driver.quit();
        }

        return null;
    }

    public static class NovelInfo{
        private String name;
        private Integer clickCount;
        private String category;
        private Integer length;
        private String coverImg;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getClickCount() {
            return clickCount;
        }

        public void setClickCount(Integer clickCount) {
            this.clickCount = clickCount;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
