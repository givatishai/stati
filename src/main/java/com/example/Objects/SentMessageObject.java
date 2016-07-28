package com.example.Objects;

import java.util.Date;

/**
 * Created by Sigal on 7/24/2016.
 */
public class SentMessageObject {

    private int oid;
    private UserObject sender;
    private String title;
    private String details;
    private Date sendingDate;
   private boolean messageRead;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public UserObject getSender() {
        return sender;
    }

    public void setSender(UserObject sender) {
        this.sender = sender;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }
}

