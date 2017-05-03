package com.ninehcom.newsserver.service;

import com.ninehcom.common.untils.Result;
import com.ninehcom.newsserver.entity.News;
import com.ninehcom.newsserver.entity.NewsContents;
import com.ninehcom.newsserver.entity.PageResponse;
import com.ninehcom.newsserver.mapper.EditconfigMapper;
import com.ninehcom.newsserver.mapper.NewsMapper;
import com.ninehcom.common.entity.PageRequest;
import com.ninehcom.common.enums.ConfigKeys;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sun.plugin.javascript.navig.JSType.Document;

/**
 * News的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service

public class NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    EditconfigMapper editconfig;
    private Random rand;

    public NewsService() {
        rand = new Random();
    }

    public Result selectNewsByType(int typeId, PageRequest page) {
        int totalCount = newsMapper.selectNewsCountByType(typeId);
        List<News> list = newsMapper.selectNewsByType(typeId, page);
        PageResponse response = new PageResponse<>(totalCount, page, list);
        return Result.Success(response);
    }

    public Result updateNewsReadTimes(int newsId) {
        String timeText = editconfig.selectEditconfig(ConfigKeys.ReadTimes.toString()).getValue();
        int baseNum;
        int randNum;

        try {
            String[] values = timeText.split("-");
            if (values.length == 1) {
                baseNum = Integer.parseInt(values[0]);
                randNum = 0;
            } else if (values.length == 2) {
                baseNum = Integer.parseInt(values[0]);
                randNum = Integer.parseInt(values[1]) - baseNum;
            } else {
                baseNum = 1;
                randNum = 0;
            }
        } catch (Exception ex) {
            baseNum = 1;
            randNum = 0;
        }

        int count;
        if (randNum <= 0) {
            count = baseNum;
        } else {
            count = rand.nextInt(randNum) + baseNum;
        }
        if (count <= 0) {
            count = 1;
        }
        Integer readTimes = newsMapper.updateNewsReadTimes(newsId, count);
        return Result.Success(readTimes);
    }

    public Result selectNewsByID(int id) {
        News news = newsMapper.selectNewsByID(id);
        //获取查询的新闻内容
        String content = news.getContents();
        Document document = Jsoup.parse(content);
        //拆分页面的每一个段落
        Elements p = document.getElementsByTag("p");
        ArrayList<NewsContents> srcList = new ArrayList<>();
        //获取每一个段落中的元素进行拆分获取
        for (Element e : p) {
            //获取span标签内的文字内容
            String aa = e.getElementsByAttribute("span").text();

            if(aa!=null && !aa.equals("")){
                String style = e.getElementsByAttribute("span").attr("style");
                aa = "["+style+"]"+aa;
                srcList.add(new NewsContents("text",aa));
            }
            //获取p标签中间的文字内容
            String bb = e.text();
            String style = e.attr("style");
            bb = "["+style+"]"+bb;
            if(bb!=null && !bb.equals("")){
                srcList.add(new NewsContents("text",bb));
            }
            //获取其中的图片标签
            Elements cc = e.select("img");
            for(Element e1 : cc){
                if(e1.attr("src")!=null && !e1.attr("src").equals("")){
                    srcList.add(new NewsContents("image",e1.attr("src")));
                }
            }
        }
        news.setSections(srcList);
        return Result.Success(news);
    }
}
