package com.usc.task2.myinvoices;

import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.Date;
import java.util.UUID;

public class Invoice {
    private UUID invoId;

    private String invoTitle;
    private Date invoDate;
    private String invoShopName;
    private String invoComment;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;



    public String getInvoChooseType() {
        return invoChooseType;
    }

    public void setInvoChooseType(String invoChooseType) {
        this.invoChooseType = invoChooseType;
    }

    private String invoChooseType;


    public RadioGroup getInvoRadio() {
        return invoRadio;
    }

    public void setInvoRadio(RadioGroup invoRadio) {
        this.invoRadio = invoRadio;
    }

    private RadioGroup invoRadio;

    private boolean invoSolved;
    public Invoice() {
        this(UUID.randomUUID());
    }
    public Invoice(UUID id) {
        invoId = id;
    invoDate = new Date(); }
    public UUID getId() {
        return invoId;
    }
    public String getTitle() {
        return invoTitle;    }
    public void setTitle(String title) {
        invoTitle = title;    }
    public Date getDate() {
        return invoDate;
    }
    public void setDate(Date date) {
        invoDate = date;
    }
    public boolean isSolved()
    {
        return invoSolved;
    }
    public void setSolved(boolean solved) {
        invoSolved = solved;
    }


    public String getShopName() {
        return invoShopName;
    }

    public void setShopName(String shopName) {
        invoShopName = shopName;
    }

    public String getComment() {
        return invoComment;
    }

    public void setComment(String comment) {
        invoComment = comment;
    }

    public String getImageFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }


}
