package com.lmiky.platform.mail;

import java.security.Security;

/**
 * 邮件
 * @author lmiky
 * @date 2015年8月10日 下午10:04:51
 */
public class SSLMail extends Mail {
    private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /**
     * 构造函数
     * @param hostName 域名
     * @param username 用户名
     * @param password 密码
     */
    public SSLMail(String hostName, String username, String password) {
        super(hostName, username, password);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.mail.Mail#setProperties()
     */
    @SuppressWarnings("restriction")
    @Override
    public void setProperties() {
        super.setProperties();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        putProperty("socketFactory.class", SSL_FACTORY);
        putProperty("socketFactory.fallback", "false");
    }

    public static void main(String[] args) {
        boolean sendReturn = SSLMail.send("smtp.exmail.qq.com", "aiyacloud@51aiya.com", "xxxxxx",
                "aiyacloud@51aiya.com", "5487751@qq.com", "主题test", "内容test");
        System.out.println(sendReturn);
    }
}
