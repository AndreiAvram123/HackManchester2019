package com.example.hive.model;

import java.util.Date;

public class Donation {
  private int amount;
  private String vendor; // PayPal, Stripe, etc.
  private String currencyCode; // GBP, EUR
  private Date dateSent;
  private User sender;

  public Donation(int amount, String vendor, String currencyCode, Date dateSent, User sender) {
    this.amount = amount;
    this.vendor = vendor;
    this.currencyCode = currencyCode;
    this.dateSent = dateSent;
    this.sender = sender;
  }

  public User getSender() {
    return this.sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public Date getDateSent() {
    return this.dateSent;
  }

  public void setDateSent(Date dateSent) {
    this.dateSent = dateSent;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getVendor() {
    return this.vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getCurrencyCode() {
    return this.currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }
}

