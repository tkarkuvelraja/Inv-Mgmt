package com.company.inventory.managment;

public class LookupDetails {

    public  int lookupId;
    public  String lookupContext;
    public  String lookupValue;
    public  String lookupCode;
    public  String lookupStatus = "A";

    public LookupDetails(int lookupId, String lookupContext, String lookupValue, String lookupCode) {
        this.lookupId = lookupId;
        this.lookupContext = lookupContext;
        this.lookupValue = lookupValue;
        this.lookupCode = lookupCode;
    }

    public int getLookupId() {
        return lookupId;
    }

    public String getLookupContext() {
        return lookupContext;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getLookupStatus() {
        return lookupStatus;
    }

    public void setLookupStatus(String lookupStatus) {
        this.lookupStatus = lookupStatus;
    }

}