package com.ruoyi.common.utils.http;

import com.ruoyi.common.constant.Constants;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * 通用http发送方法
 *
 * @author ruoyi
 */
public class HttpUtils {
    protected static final String POST_METHOD = "POST";
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        return sendGet(url, param, Constants.UTF8);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url         发送请求的 URL
     * @param param       请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null) {
                if (ret != null && !"".equals(ret.trim())) {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static String doPost(String url, Map<String, String> map, String data) {
        return HttpDefaultExecute(url, POST_METHOD, map, data);
    }

    /**
     * 默认的http请求执行方法
     *
     * @param url    url 路径
     * @param method 请求的方法 POST/GET
     * @param map    请求参数集合
     * @param data   输入的数据 允许为空
     * @return result
     */
    private static String HttpDefaultExecute(String url, String method, Map<String, String> map, String data) {
        String result = "";
        try {
            url = setParmas(url, map, null);
            result = defaultConnection(url, method, data);
        } catch (Exception e) {
            log.error("出错参数 {}", map);
        }
        return result;
    }

    /**
     * 拼接参数
     *
     * @param url     需要拼接参数的url
     * @param map     参数
     * @param charset 编码格式
     * @return 拼接完成后的url
     */
    public static String setParmas(String url, Map<String, String> map, String charset) throws Exception {
        String result = StringUtils.EMPTY;
        boolean hasParams = false;
        if (StringUtils.isNotEmpty(url) && MapUtils.isNotEmpty(map)) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().trim();
                String value = entry.getValue().trim();
                if (hasParams) {
                    builder.append("&");
                } else {
                    hasParams = true;
                }
                if (StringUtils.isNotEmpty(charset)) {
                    builder.append(key).append("=").append(URLEncoder.encode(value, charset));
                } else {
                    builder.append(key).append("=").append(value);
                }
            }
            result = builder.toString();
        }

        URL u = new URL(url);
        if (StringUtils.isEmpty(u.getQuery())) {
            if (url.endsWith("?")) {
                url += result;
            } else {
                url = url + "?" + result;
            }
        } else {
            if (url.endsWith("&")) {
                url += result;
            } else {
                url = url + "&" + result;
            }
        }
        log.debug("request url is {}", url);
        return url;
    }

    /**
     * @param path   请求路径
     * @param method 方法
     * @param data   输入的数据 允许为空
     * @return
     * @throws Exception
     */
    private static String defaultConnection(String path, String method, String data) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new IOException("url can not be null");
        }
        String result = null;
        URL url = new URL(path);
        HttpURLConnection conn = getConnection(url, method);
        if (StringUtils.isNotEmpty(data)) {
            OutputStream output = conn.getOutputStream();
            output.write(data.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream input = conn.getInputStream();
            result = IOUtils.toString(input, StandardCharsets.UTF_8);
            input.close();
            conn.disconnect();
        }
//        log.info(result);
        return result;
    }

    /**
     * 根据url的协议选择对应的请求方式
     *
     * @param url    请求路径
     * @param method 方法
     * @return conn
     * @throws IOException 异常
     */
    //待改进
    protected static HttpURLConnection getConnection(URL url, String method) throws IOException {
        HttpURLConnection conn;
        if (StringUtils.equals("https", url.getProtocol())) {
            conn = (HttpsURLConnection) url.openConnection();
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        if (conn == null) {
            throw new IOException("connection can not be null");
        }
        conn.setRequestProperty("Pragma", "no-cache");// 设置不适用缓存
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Connection", "Close");// 不支持Keep-Alive
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod(method);
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);

        return conn;
    }

}