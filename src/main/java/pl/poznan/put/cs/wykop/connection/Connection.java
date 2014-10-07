package pl.poznan.put.cs.wykop.connection;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Connection {
	private static String REST_OF_PARAMS = ",output,clear";
	private static String URL_BASE = "http://a.wykop.pl/";
	private String appKey;
	private HttpClient httpClient = new DefaultHttpClient();
	private long lastReq = System.currentTimeMillis();
	/**
	 * Time between requests. In miliseconds.
	 */
	private long reqInterval = 0;
	private String secret;

	// http:/a.wykop.pl/link/index/54321/appkey,12345
	public Connection(String appKey, String secret) {
		this.appKey = appKey;
		this.secret = secret;
	}

	private String buildUrl(String method, String urlParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(URL_BASE);
		sb.append(method);
		sb.append("/");
		sb.append(urlParams);
		sb.append("/appkey,");
		sb.append(appKey);
		sb.append(REST_OF_PARAMS);

		return sb.toString();
	}

	public String call(String method, String urlParam) throws ConnectionException {
		String url = buildUrl(method, urlParam);
		String sign = sign(url);
		HttpPost req = new HttpPost(url);
		req.addHeader("apisign", sign);
		try {
			HttpResponse resp = this.execute(req);
			if (resp.getStatusLine().getStatusCode() != 200) {
				throw new ConnectionException("Status code != 200.");
			}
			String res = IOUtils.toString(resp.getEntity().getContent(), "utf-8");
			if (res.startsWith("{\"error\":{\"code\":")) {
				throw new WykopException(res);
			} else {
				return res;
			}
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	private HttpResponse execute(HttpPost req) throws IOException, ClientProtocolException {
		HttpResponse resp;
		synchronized (this) {
			this.waitForReqTime();
			this.lastReq = System.currentTimeMillis();
			resp = this.httpClient.execute(req);
		}
		return resp;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getSecret() {
		return secret;
	}

	private String sign(String url) {
		return DigestUtils.md5Hex(this.secret+url);
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setHourLimit(int noOfRequest) {
		if (noOfRequest > 0) {
			this.reqInterval = (3600000 / noOfRequest) + 1;
		} else {
			this.reqInterval = 0;
		}
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	private void waitForReqTime() {
		long rest;
		while ((rest = lastReq + reqInterval - System.currentTimeMillis()) >= 0) {
			try {
				Thread.sleep(rest);
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

}
