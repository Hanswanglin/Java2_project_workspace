package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code Filter} class judge data.
 * <p>It can judge data by the start and end date, magnitude and region</p>
 *
 * @author  Zhang Yilin
 */
public class Filter {

    /**
     * Filter the String[] data, return true if it pass muster, and vice versa.
     *
     * @param date1
     *            The start date.
     * @param date2
     *            The end date.
     * @param magnitude
     *            The minimum magnitude.
     * @param re
     *            The quake region.
     * @param l
     *            The String[] data.
     * @return If the data meet the condition
     */
    public static boolean filter(String date1, String date2, float magnitude,String re, String[] l) throws IOException {
        if(dateFilter(l,date1,date2)&&magFilter(l,magnitude)&&reFilter(l,re)) {
            return true;
        }
        return false;
    }

    /**
     * Filter the String[] data, return true if it pass muster, and vice versa.
     *
     * @param magnitude
     *            The minimum magnitude.
     * @return If the data meet the condition
     */
    private static boolean magFilter(String[] values, double magnitude) throws IOException {
        Double mag = Double.parseDouble(values[5]);
        if (mag >= magnitude) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Filter the String[] data, return true if it pass muster, and vice versa.
     *
     * @param date1
     *            The start date.
     * @param date2
     *            The end date.
     * @return If the data meet the condition
     */
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

    /**
     * Filter the String[] data, return true if it pass muster, and vice versa.
     *
     * @param re
     *            The quake region.
     * @return If the data meet the condition
     */
    private static boolean reFilter(String[] values,String re) throws IOException {
        String de =  values[6];
        if(re.equals("-- World Wide --") || de.contains(re)) {
        	return true;
        }
        else {
        	return false;
        }
    }

    /**
     * Compare two dates sequence.
     *
     * @param DATE1
     *            The date that needs to be compared.
     * @param DATE2
     *            The date that needs to be compared.
     * @return Number 1 means DATE1 is after DATE2, number -1 means DATE1 is before DATE2，number 0 means DATE1 is equals to DATE2
     */
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
