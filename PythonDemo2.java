package com.daqi.test;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PythonDemo2 {
    public static void main(String[] args) throws Exception {
        // 先把要抓取的网页链接放出来存着
        // 企业原网站“http://www.zhsme.gov.cn/main/toMainIndex”
        String url = "http://www.zhsme.gov.cn/main/toMainIndex";

        // 创建对象，这里需要引入一个爬虫的jar
        Connection connection = Jsoup.connect(url);
        // 这里会有一个异常报错，抛出，处理都可以
        Document document = connection.method(Connection.Method.GET).get();

        // 因为很多一样的，所以我们用一个集合存放这些节点
        Elements lis = document.select("#zxzcDiv li");


        /**
         * 原链接 “http://www.zhsme.gov.cn/policy/getAllPolicyInfoByPolicyId?id=e2d41e70Meb2eM4fe0M9e8bM29a32753e56f”
         * 我们要把id前的链接保存起来，然后通过截取的办法把连接拼接起来
         */
        String sourceUrl = "http://www.zhsme.gov.cn/policy/getAllPolicyInfoByPolicyId?id=";

        for (Element s : lis) {
            String id = s.toString().substring(s.toString().indexOf("id=")+3,s.toString().indexOf("\"",s.toString().indexOf("id=")+3));
            // 把前部分的连接和截取出来的链接拼接起来，形成一个完成的链接
            Connection connection1 = Jsoup.connect(sourceUrl + id);
            Document sourceDocument = connection1.method(Connection.Method.GET).get();

            // 获取链接的标题
            String title = sourceDocument.select(".policy-info h2").text();

            // 输出标题
            System.out.println("标题：" + title);
        }
    }
}
