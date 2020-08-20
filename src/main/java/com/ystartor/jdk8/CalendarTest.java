package com.ystartor.jdk8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SS");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date date = cal.getTime();
        String day = format.format(date);
        System.out.println(day);


    }


}
