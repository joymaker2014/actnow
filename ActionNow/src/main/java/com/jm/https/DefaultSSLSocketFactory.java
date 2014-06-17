/**
 * 
 */
package com.jm.https;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;

/**
 * @author LuZheqi
 * 
 */
public class DefaultSSLSocketFactory extends SSLConnectionSocketFactory {
	static {
		defaultSSLSocketFactory = new DefaultSSLSocketFactory(createSContext());
	}
	private static DefaultSSLSocketFactory defaultSSLSocketFactory = null;

	private static SSLContext createSContext() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			sslcontext.init(null,
					new TrustManager[] { new TrustAnyTrustManager() }, null);
		} catch (KeyManagementException e) {
			e.printStackTrace();
			return null;
		}
		return sslcontext;
	}

	public HttpClient wrapedHttpClient() {
		return HttpClients.custom().setSSLSocketFactory(defaultSSLSocketFactory)
				.build();
	}

	private DefaultSSLSocketFactory(SSLContext sslContext) {
		super(sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	}

	public static DefaultSSLSocketFactory getInstance() {
		if (defaultSSLSocketFactory != null) {
			return defaultSSLSocketFactory;
		} else {
			return defaultSSLSocketFactory = new DefaultSSLSocketFactory(createSContext());
		}
	}
}
