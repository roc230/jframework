package com.roc.jframework.core.component.httpclient;

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

public abstract class AbstractRequests implements IRequests {

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
