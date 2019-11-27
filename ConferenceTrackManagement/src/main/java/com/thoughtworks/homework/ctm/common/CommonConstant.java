package com.thoughtworks.homework.ctm.common;

import com.thoughtworks.homework.ctm.entity.Talk;

public class CommonConstant {
    public static final String BLANKSIGN=" ";
    public static final Integer TOTALMINONEDAY=420;
    public static final Integer MORSTARTDATE=60*9;
    public static final Integer AFTSTARTDATE=60*1;


    public static final String LIGHTNING="lightning";
    public static final Integer LIGHTNINGMIN=5;

    public static final Talk LUNCHTIME=new Talk("Launch","12:00PM","01:00PM",60,true,"");
    public static final Talk NETWORKING_EVENT=new Talk("Networking Event","05:00PM","",0,true,"");

}
