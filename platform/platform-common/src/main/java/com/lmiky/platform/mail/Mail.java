/**
 *
 */
package com.lmiky.platform.mail;

import com.lmiky.platform.logger.util.LoggerUtils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件
 *
 * @author lmiky
 * @date 2015年8月10日 下午10:04:51
 */
public class Mail {
    // 内容编码
    private static final String CONTENT_ENCODING = "UTF-8";
    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    private static boolean needAuth = true; // smtp是否需要认证
    private String hostName; // 域名
    private String protocol; // 发送协议
    private String username; // smtp认证用户名
    private String password; // smtp认证密码
    private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
    private SimpleAuthenticator authenticator;// 邮件服务器登录验证

    /**
     * 构造函数
     *
     * @param hostName 域名
     * @param username 用户名
     * @param password 密码
     */
    public Mail(String hostName, String username, String password) {
        this.hostName = hostName;
        this.username = username;
        this.password = password;
        setProperties();
        createMimeMessage();
    }

    /**
     * 设置邮件属性
     *
     * @author lmiky
     * @date 2015年8月11日 下午6:10:37
     */
    public void setProperties() {
        if (props == null) {
            // 获得系统属性对象
            props = System.getProperties();
        }
        protocol = hostName.substring(0, hostName.indexOf("."));
        setHost();
        setNeedAuth(needAuth); // 需要验证
        putProperty("timeout", "15000");
    }

    /**
     * 设置邮件发送服务器
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:07:00
     */
    public void setHost() {
        putProperty("host", hostName);
    }

    /**
     * 设置SMTP是否需要验证
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:11:10
     * @param need
     */
    public void setNeedAuth(boolean need) {
        putProperty("auth", String.valueOf(need));
    }

    /**
     * 放入属性值
     *
     * @param propertyName
     * @param propertyValue
     * @author lmiky
     * @date 2015年8月14日 上午11:00:25
     */
    public void putProperty(String propertyName, Object propertyValue) {
        if (propertyValue != null) {
            props.put("mail." + protocol + "." + propertyName, propertyValue);
        }
    }

    /**
     * 创建MIME邮件对象
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:07:26
     * @return
     */
    public boolean createMimeMessage() {
        try {
            authenticator = new SimpleAuthenticator(username, password);
            // 获得邮件会话对象
            session = Session.getDefaultInstance(props, authenticator);
        } catch (Exception e) {
            LoggerUtils.error("获取邮件会话对象时发生错误！", e);
            return false;
        }
        try {
            // 创建MIME邮件对象
            mimeMsg = new MimeMessage(session);
            mp = new MimeMultipart();
            return true;
        } catch (Exception e) {
            LoggerUtils.error("创建MIME邮件对象失败！", e);
            return false;
        }
    }

    /**
     * 设置邮件主题
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:18:07
     * @param mailSubject
     * @return
     */
    public boolean setSubject(String mailSubject) {
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            LoggerUtils.error("设置邮件主题发生错误！", e);
            return false;
        }
    }

    /**
     * 设置邮件正文
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:18:17
     * @param mailBody
     * @return
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(mailBody, "text/html;charset=" + CONTENT_ENCODING);
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            LoggerUtils.error("设置邮件正文时发生错误！", e);
            return false;
        }
    }

    /**
     * 添加附件
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:18:46
     * @param filename
     * @return
     */
    public boolean addFileAffix(String filename) {
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(fileds.getName());
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            LoggerUtils.error("增加邮件附件：" + filename + "发生错误！", e);
            return false;
        }
    }

    /**
     * 设置发信人
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:18:55
     * @param from
     * @return
     */
    public boolean setFrom(String from) {
        try {
            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
            return true;
        } catch (Exception e) {
            LoggerUtils.error("设置发信人错误！", e);
            return false;
        }
    }

    /**
     * 设置收信人
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:19:02
     * @param to
     * @return
     */
    public boolean setTo(String to) {
        if (to == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            LoggerUtils.error("设置接收人错误！", e);
            return false;
        }
    }

    /**
     * 设置抄送人
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:19:24
     * @param copyto
     * @return
     */
    public boolean setCopyTo(String copyto) {
        if (copyto == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            LoggerUtils.error("设置抄送人错误！", e);
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @author lmiky
     * @date 2015年8月10日 下午10:27:15
     * @return
     */
    public boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            Transport.send(mimeMsg, mimeMsg.getAllRecipients());
            return true;
        } catch (Exception e) {
            LoggerUtils.error("邮件发送失败！", e);
            return false;
        }
    }

    /**
     * 构建并设置公共发送属性
     *
     * @param smtp 邮件服务器 @param username 登录用户名
     * @param password 邮件登录密码
     * @param from 邮件发送者
     * @param to 邮件接收者
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return 邮件实体类
     * @author lmiky
     * @date 2015年8月10日 下午10:41:32
     */
    private static Mail setSendAttribute(String smtp, String username, String password, String from, String to,
            String subject, String content) {
        Mail theMail = new Mail(smtp, username, password);
        if (!theMail.setSubject(subject)) {
            return null;
        }
        if (!theMail.setBody(content)) {
            return null;
        }
        if (!theMail.setTo(to)) {
            return null;
        }
        if (!theMail.setFrom(from)) {
            return null;
        }
        return theMail;
    }

    /**
     * 调用sendOut方法完成邮件发送
     *
     * @param smtp 邮件服务器 @param username 登录用户名
     * @param password 邮件登录密码
     * @param from 邮件发送者
     * @param to 邮件接收者
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return 是否成功
     * @author lmiky
     * @date 2015年8月10日 下午10:41:20
     */
    public static boolean send(String smtp, String username, String password, String from, String to, String subject,
            String content) {
        Mail theMail = setSendAttribute(smtp, username, password, from, to, subject, content);
        if (theMail == null) {
            return false;
        }
        doSend(theMail);
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带附件
     *
     * @param smtp 邮件服务器
     * @param username 登录用户名
     * @param password 邮件登录密码
     * @param from 邮件发送者
     * @param to 邮件接收者
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filenames 邮件附件列表
     * @return 是否成功
     * @author lmiky
     * @date 2015年8月10日 下午10:40:58
     */
    public static boolean send(String smtp, String username, String password, String from, String to, String subject,
            String content, String... filenames) {
        Mail theMail = setSendAttribute(smtp, username, password, from, to, subject, content);
        if (theMail == null) {
            return false;
        }
        for (String filename : filenames) {
            if (!theMail.addFileAffix(filename)) {
                return false;
            }
        }
        doSend(theMail);
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带抄送
     *
     * @param smtp 邮件服务器 @param username 登录用户名
     * @param password 邮件登录密码
     * @param from 邮件发送者
     * @param to 邮件接收者
     * @param copyto 邮件抄送
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return 是否成功
     * @author lmiky
     * @date 2015年8月10日 下午10:41:10
     */
    public static boolean sendAndCc(String smtp, String username, String password, String from, String to,
            String copyto, String subject, String content) {
        Mail theMail = setSendAttribute(smtp, username, password, from, to, subject, content);
        if (theMail == null) {
            return false;
        }
        if (!theMail.setCopyTo(copyto)) {
            return false;
        }
        doSend(theMail);
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带附件和抄送
     *
     * @param smtp 邮件服务器 @param username 登录用户名
     * @param password 邮件登录密码
     * @param from 邮件发送者
     * @param to 邮件接收者
     * @param copyto 邮件抄送
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filenames 邮件附件列表
     * @return 是否成功
     * @author lmiky
     * @date 2015年8月10日 下午10:41:55
     */
    public static boolean sendAndCc(String smtp, String username, String password, String from, String to,
            String copyto, String subject, String content, String... filenames) {
        Mail theMail = setSendAttribute(smtp, username, password, from, to, subject, content);
        if (theMail == null) {
            return false;
        }
        for (String filename : filenames) {
            if (!theMail.addFileAffix(filename)) {
                return false;
            }
        }
        if (!theMail.setCopyTo(copyto)) {
            return false;
        }
        doSend(theMail);
        return true;
    }

    /**
     * 执行发送
     *
     * @param theMail 要发送的邮件
     * @author lmiky
     * @date 2015年8月11日 下午9:57:27
     */
    private static void doSend(final Mail theMail) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean sendResult = theMail.sendOut();
                    LoggerUtils.info("邮件发送结果：" + sendResult);
                } catch (Exception e) {
                    LoggerUtils.error("邮件发送失败！", e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        boolean sendReturn = Mail.sendAndCc("smtp.qq.com", "5487751", "xxxxx", "5487751@qq.com", "5487751@qq.com",
                "321456@qq.com", "主题test", "内容test");
        System.out.println(sendReturn);
    }
}
