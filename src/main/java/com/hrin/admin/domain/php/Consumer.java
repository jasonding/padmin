package com.hrin.admin.domain.php;

import com.hrin.admin.domain.enumeration.ConsumerStatus;

import java.util.Date;

/**
 * Created by jason on 14-11-30.
 */
public class Consumer {
    private String uid;
    private String email;
    private String ident;
    private String nickname;
    private Date regTime;
    private Date lastLogin;
    private Date latestLogin;
    private ConsumerStatus status;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLatestLogin() {
        return latestLogin;
    }

    public void setLatestLogin(Date latestLogin) {
        this.latestLogin = latestLogin;
    }

    public ConsumerStatus getStatus() {
        return status;
    }

    public void setStatus(ConsumerStatus status) {
        this.status = status;
    }
}
