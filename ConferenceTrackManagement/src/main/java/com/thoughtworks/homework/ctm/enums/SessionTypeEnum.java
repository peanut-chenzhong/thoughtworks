package com.thoughtworks.homework.ctm.enums;

public enum SessionTypeEnum {
   MORNING("morning", "AM",180), //morning talk session
   AFTERNOON("afternoon", "PM",240); // afternoon talk session

    private String typeName;
    private String typeCode;
    private Integer typeTime;

    SessionTypeEnum(String typeName, String typeCode,Integer typeTime) {
        this.typeName = typeName;
        this.typeCode = typeCode;
        this.typeTime=typeTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public Integer getTypeTime() {
        return typeTime;
    }
}
