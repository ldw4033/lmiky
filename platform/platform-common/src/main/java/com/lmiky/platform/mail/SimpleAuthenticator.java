package com.lmiky.platform.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 简单验证
 * @author lmiky
 * @date 2015年8月10日 下午3:38:27
 */
public class SimpleAuthenticator extends Authenticator {
    /**
     * 用户名（登录邮箱）
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     *
     */
    public SimpleAuthenticator() {

    }

    /**
     * @param username
     * @param password
     */
    public SimpleAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * @see javax.mail.Authenticator#getPasswordAuthentication()
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
