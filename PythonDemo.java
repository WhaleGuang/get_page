package com.daqi.test;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PythonDemo {

    public static void main(String[] args) throws Exception {
        String url = "http://www.zhsme.gov.cn/main/toMainIndex";

        Connection connect = Jsoup.connect(url); //创建对象
        Document document = connect.method(Connection.Method.GET).get();  //获得连接

        //所有a标签
        String[] aLinksOne = document.select(".news-list ul li a").toString().split("\n");
//        Elements aaaa = document.select(".news-list ul li a");
//        for (Element element : aaaa) {
//            System.out.println(element);
//        }

        List<String> aList = new ArrayList<String>(); //保存所有链接

        //循环所有a标签
        for (String s : aLinksOne) {
            aList.add(s.substring(s.indexOf("/notices"),s.indexOf("\" class=")));
        }
        //获得详情数据
        String sourceUrl = "http://www.zhsme.gov.cn";

        //循环获得每条数据的详细信息
        for (String link : aList) {
            Connection sourceConnect = Jsoup.connect(sourceUrl + link);
            Document sourceDocument = sourceConnect.method(Connection.Method.GET).get();

            // .view 节点下的数据
            Elements source = sourceDocument.select(".view");

            //获得标题
            String title = source.select("h1 strong").text();
            System.out.println("标题："+title);
        }
    }



}
