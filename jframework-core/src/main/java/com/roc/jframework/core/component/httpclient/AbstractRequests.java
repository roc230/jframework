package com.roc.jframework.core.component.httpclient;

import com.roc.jframework.basic.utils.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

;

public abstract class AbstractRequests implements IRequests {

    protected Map<String,String> params;
    protected Map<String,String> headers;
    protected String url;
    protected String userAgent;
    protected String charset = "utf-8";
    protected String contentType;
    protected String accept;
    protected String acceptEncoding;
    protected String acceptLanguage;
    protected String referer;
    protected String xRequestedWith;
    protected String origin;

    protected HttpHost httpHost;

    public AbstractRequests(){
        params = new HashMap<>();
        headers = new HashMap<>();
    }

    @Override
    public IRequests header(String name, String value) {
        if(!StringUtils.isNullOrEmpty(name)){
            headers.put(name, value);
        }
        return this;
    }

    @Override
    public IRequests param(String name, String value) {
        if(!StringUtils.isNullOrEmpty(name)){
            params.put(name, value);
        }
        return this;
    }

    @Override
    public IRequests userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public IRequests url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public IRequests charset(String charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public IRequests accept(String accept) {
        this.accept = accept;
        return this;
    }

    @Override
    public IRequests acceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
        return this;
    }

    @Override
    public IRequests acceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    @Override
    public IRequests contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    @Override
    public IRequests referer(String referer) {
        this.referer = referer;
        return this;
    }

    @Override
    public IRequests xRequestedWith(String xRequestedWith) {
        this.xRequestedWith = xRequestedWith;
        return this;
    }

    @Override
    public IRequests origin(String origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public IRequests proxy(String ip, int port, String schema) {
        this.httpHost = new HttpHost(ip, port, schema);
        return this;
    }

    /**
     * 创建SSL连接工厂(绕过SSL认证)
     * @return
     */
    protected SSLConnectionSocketFactory buildSSLConnectionFactory(){
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom()
                    .loadTrustMaterial(new TrustSelfSignedStrategy())
                    .build();
            sslContext.init(null, new X509TrustManager[]{x509TrustManager}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext, (s, sslsession) -> true);

        return factory;
    }

    /**
     * 创建SSL连接池
     * @param factory
     * @return
     */
    protected PoolingHttpClientConnectionManager buildSSLConnectionPool(SSLConnectionSocketFactory factory){
        Registry registry = RegistryBuilder.create()
                .register("https", factory)
                .build();
        PoolingHttpClientConnectionManager connPool = new PoolingHttpClientConnectionManager(registry);
        connPool.setMaxTotal(200);
        connPool.setDefaultMaxPerRoute(20);
        return connPool;
    }

}
