package com.honey.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/17 17:47
 * Copyright © 2015/8/17 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class HttpClientUtils {

    private HttpClient httpClient;
    private final static Header defaultHeader = new Header("Content-Type", "text/xml;charset=utf-8");
    private final static String CHARSET_UTF8 = "UTF-8";
    private static HttpClientUtils uUu = new HttpClientUtils();
    private HttpClientBuilder httpClientBuilder;

    private HttpClientUtils() {
        this.httpClientBuilder = HttpClientBuilder.create();
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        this.httpClientBuilder.setConnectionManager(connMgr);
    }

    public static HttpClientUtils getInstance() {
        return uUu;
    }

    /**
     * 默认返回xml格式
     *
     * @param url
     * @param jsonString
     * @param headeries
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String httpPost(String url, String jsonString, Header... headeries) throws IOException {
        this.httpClient = this.httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonString, CHARSET_UTF8);
        httpPost.setEntity(entity);
        if (headeries != null) {
            for (Header header : headeries) {
                httpPost.setHeader(header.getKey(), header.getValue());
            }
        } else {
            httpPost.setHeader(defaultHeader.getKey(), defaultHeader.getValue());
        }
        HttpResponse response = this.httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
    }

    /**
     * 默认返回xml格式
     *
     * @param url
     * @param headeries
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String httpGet(String url, Header... headeries) throws IOException {
        this.httpClient = this.httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        if (headeries != null) {
            for (Header header : headeries) {
                httpGet.setHeader(header.getKey(), header.getValue());
            }
        } else {
            httpGet.setHeader(defaultHeader.getKey(), defaultHeader.getValue());
        }
        HttpResponse response = this.httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), CHARSET_UTF8);
    }
}
