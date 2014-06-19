/**
 * 
 */
package com.jm.client.https;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author LuZheqi
 * 
 */
public class DefaultSSLSocketFactory extends SSLSocketFactory {
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
		HttpClient client = new DefaultHttpClient();
		ClientConnectionManager ccm = client.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", DefaultSSLSocketFactory.getInstance(),
				443));
		client = new DefaultHttpClient(ccm, client.getParams());
		return client;
		// return
		// HttpClients.custom().setSSLSocketFactory(defaultSSLSocketFactory)
		// .build();
	}

	private DefaultSSLSocketFactory(SSLContext sslContext) {
		// super(sslContext,
		// SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		super(sslContext);
		this.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);
	}

	public static DefaultSSLSocketFactory getInstance() {
		if (defaultSSLSocketFactory != null) {
			return defaultSSLSocketFactory;
		} else {
			return defaultSSLSocketFactory = new DefaultSSLSocketFactory(
					createSContext());
		}
	}
}
