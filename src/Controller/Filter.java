package Controller;

import bean.quake;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hans on 2017/12/21.
 */
public class Filter {
    //filter the String[] data and change its type to quake
    public static quake filter(String date1, String date2, double magnitude,String re, String[] l) throws IOException {
        if(dateFilter(l,date1,date2)&&magFilter(l,magnitude)&&reFilter(l,re)) {
            Integer l0 = Integer.parseInt(l[0]);
            String l1 = l[1];
            Float l2 = Float.parseFloat(l[2]);
            Float l3 = Float.parseFloat(l[3]);
            Integer l4 = Integer.parseInt(l[4]);
            Float l5 = Float.parseFloat(l[5]);
            String l6 = l[6].replaceAll("\"", "");
            quake q = new quake(l0, l1, l2, l3, l4, l5, l6);
            return q;
        }
        return null;
    }

    private static boolean magFilter(String[] values, double magnitude) throws IOException {
        Double mag = Double.parseDouble(values[5]);
        if (mag >= magnitude) {
            return true;
        }else {
            return false;
        }
    }

    private static boolean dateFilter(String[] values, String date1, String date2) throws IOException {
        values[1] =  values[1].replaceAll("\"", "").replaceAll("/", "-");
        String d = values[1].substring(0,11);
        if(compare_date(d,date1)>=0
                &&compare_date(d,date2)<=0){
            return true;
        }else {
            return false;
        }
    }
    
    private static boolean reFilter(String[] values,String re) throws IOException {
        String de =  values[6];
        if(re.equals("-- World Wide --") || de.contains(re)) {
        	return true;
        }
        else {
        	return false;
        }
    }

    private static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (DATE2!=null){
                Date dt1 = df.parse(DATE1);
                Date dt2 = df.parse(DATE2);
                if (dt1.getTime() > dt2.getTime()) {
                    //dt1 is after than dt2
                    return 1;
                } else if (dt1.getTime() < dt2.getTime()) {
                    //dt1 is before than dt2
                    return -1;
                } else {
                    return 0;
                }
            }
        } catch (Exception exception) {
        }
        return 0;
    }
}
