package gestaopet.classes;

import gestaopet.enums.DateFormat;
import static gestaopet.enums.DateFormat.FIRSTDAYOFMONTH;
import gestaopet.enums.DateMethods;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTools {
    public static Date today = getDate(DateMethods.TODAY, 0, 0, 0);
    public static int formatDate(Date date, DateFormat format){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        
        String stringFormat = dateToString(date);
        String[] dateSplit = stringFormat.split("/");

        switch(format){
            case DAY:
                return Integer.parseInt("" + dateSplit[0]);
            case MONTH:
                return Integer.parseInt("" + dateSplit[1]);
            case YEAR:
                return Integer.parseInt("" + dateSplit[2]);
            case DAYMONTH:
                return Integer.parseInt("" + dateSplit[1] + dateSplit[0]);
            case DAYMONTHYEAR:
                return Integer.parseInt("" + dateSplit[2] + dateSplit[1] + dateSplit[0]);
            case MONTHYEAR:
                return Integer.parseInt("" + dateSplit[2] + dateSplit[1]);
            case DAYOFWEEK:
                return date.getDay();
            case WEEKNUMBER:
                return calendar.get(java.util.Calendar.WEEK_OF_YEAR);
            case FIRSTDAYOFMONTH:
                return Integer.parseInt(dateSplit[2] + dateSplit[1] + "01");
        }
        return -1;
    }
    

    
    public static String getDayOfWeek(Date d){
        String output = "erro";
        String pre = "";
        switch(d.getDay()){
            case 0 :
                pre = "domingo";
                break;
            case 1 :
                pre = "segunda";
                break;
            case 2 :
                pre = "terça";
                break;
            case 3 :
                pre = "quarta";
                break;
            case 4 :
                pre = "quinta";
                break;
            case 5 :
                pre = "sexta";
                break;
            case 6 :
                pre = "sábado";
                break;
        }
        
        output = dateToString(d) + " - " + "(" + pre.substring(0, 3).toUpperCase() + ")";
        
        
        
        return output;
    }
    
    
    public static Date getDate(DateMethods method, int y, int m, int d){
        /*
        Retorna a data de HOJE;
        Retorna uma nova data baseada na data de HOJE.
        Retorna a data inicial de um mês;
        Retorna a data final de um mês;        
        */
        Date res = java.util.Calendar.getInstance().getTime();
        
        if(method != DateMethods.TODAY) {
            //personaliza a data
            Calendar p = Calendar.getInstance();
            p.set(Calendar.YEAR, y);
            p.set(Calendar.MONTH, m -1);
            p.set(Calendar.DAY_OF_MONTH, d);
            
            //verifica se quer retorno do primeiro ou ultimo dia do mês
            if(method == DateMethods.FIRSTDAYOFMONTH){
                p.set(java.util.Calendar.DAY_OF_MONTH, 01);
            } else if(method == DateMethods.LASTDAYOFMONTH){
                int ld = p.getActualMaximum(java.util.Calendar.DATE);
                p.set(java.util.Calendar.DAY_OF_MONTH, ld);
            }
            //aplica a data personalizada ao retorno do método
            res =  p.getTime();
        }
        return res;
    }
    
    public static int daysBetween(Date dataInicial, Date dataFinal, boolean absolute){
        dataInicial.setHours(0);
        dataInicial.setMinutes(0);
        dataFinal.setHours(1);
        dataFinal.setMinutes(30);
        
        long dateBeforeInMs = dataInicial.getTime();
        long dateAfterInMs = dataFinal.getTime();
        long diff = dateAfterInMs - dateBeforeInMs;
        
        long timeDiff = (absolute)? Math.abs(diff) : diff;
        
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        
        return (int)daysDiff;
    }
    
    public static int yearsBetween(Date startDate, Date finalDate, boolean absolute){
        String di = dateToString(startDate);
        String df = dateToString(finalDate);
        
        di = di.split("/")[2] + "-" + di.split("/")[1] + "-" + di.split("/")[0];
        df = df.split("/")[2] + "-" + df.split("/")[1] + "-" + df.split("/")[0];
        
        LocalDate date1 = LocalDate.parse(di);
        LocalDate date2 = LocalDate.parse(df);
        Period period = date1.until(date2);
        int yearsBetween = period.getYears();
        
        
        
        
        return yearsBetween;
    }
    
    public static Date convertDateTime(int d, int m, int y, int hh, int mm){
        Calendar p = Calendar.getInstance();
        
        p.set(Calendar.DAY_OF_MONTH, d);
        p.set(Calendar.MONTH, m-1);
        p.set(Calendar.YEAR, y);
        
        p.set(Calendar.HOUR_OF_DAY, hh);
        p.set(Calendar.MINUTE,mm);
        
        return p.getTime();
    }
    
    public static String dateToString(Date date){
        String d = ((date.getDate() < 10)? "0" : "") + date.getDate();
        String m = ((date.getMonth() < 9)? "0" : "") + (date.getMonth() + 1);
        String a = (date.getYear() + 1900) + "";
        String output = d + "/" + m + "/" + a;
        
        return output;
    }
    
    public static String timeToString(Date date){
        int h = date.getHours(), m = date.getMinutes();
        
        String hh = (h<9)? "0" + h: "" + h;
        String mm = (m<9)? "0" + m: "" + m;

        return hh + ":" + mm;
    }
    
    public static Date stringToDate(String date /*DD/MM/AAAA*/, String time /*HH:MM*/){
        int d = Integer.parseInt(date.split("/")[0]);
        int m = Integer.parseInt(date.split("/")[1]);
        int a = Integer.parseInt(date.split("/")[2]);
        
        int hh = Integer.parseInt(time.split(":")[0]);
        int mm = Integer.parseInt(time.split(":")[1]);
        
        Date output = convertDateTime(d, m, a, hh, mm);
        
        return output;
    }
    
    public static Date SQLToDate(String date /*AAAA-MM-DD*/, String time /*HH:MM*/){
        int d = Integer.parseInt(date.split("-")[2]);
        int m = Integer.parseInt(date.split("-")[1]);
        int a = Integer.parseInt(date.split("-")[0]);

        int hh = Integer.parseInt(time.split(":")[0]);
        int mm = Integer.parseInt(time.split(":")[1]);
        
        Date output = convertDateTime(d, m, a, hh, mm);
        
        return output;
    }
    
    public static String SQLToString(String date /*AAAA-MM-DD*/, String time /*HH:MM*/){
        int d = Integer.parseInt(date.split("-")[2]);
        int m = Integer.parseInt(date.split("-")[1]);
        int a = Integer.parseInt(date.split("-")[0]);

        int hh = Integer.parseInt(time.split(":")[0]);
        int mm = Integer.parseInt(time.split(":")[1]);
        
        Date data = convertDateTime(d, m, a, hh, mm);
        
        return dateToString(data);
    }
    
    public static String stringDateToSQL(String date /*DD/MM/AAAA*/){
        String d = date.split("/")[0];
        String m = date.split("/")[1];
        String a = date.split("/")[2];
        
        String output = a + "-" + m + "-" + d;
        
        return output;
    }
    
    public static String dateToSQL(Date date /*DD/MM/AAAA*/){
        return stringDateToSQL(dateToString(date));
    }
    
    public static String timeToSQL(Date date /*DD/MM/AAAA*/){
        int h = date.getHours(), m = date.getMinutes();
        
        String hh = (h<9)? "0" + h: "" + h;
        String mm = (m<9)? "0" + m: "" + m;

        return hh + ":" + mm;
    }
    
    public static Date setTime(Date date, int hh, int mm){
        date.setHours(hh);
        date.setMinutes(mm);
        
        return date;        
    }
    
    public static boolean dateCompare(Date firstDate, Date finalDate, boolean acceptZero){
        int t = daysBetween(firstDate, finalDate, false);
        
        if(acceptZero){
            return (t < 0)? false : true;
        } else {
            return (t <= 0)? false : true;
        }
    }
    
    public static int getYear(){
        int output = getDate(DateMethods.TODAY, 0, 0, 0).getYear() + 1900;
       
        return output;
    }
    
    public static int[] getDaysInMonth(int year, int month){
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int[] daysInMonth =  new int[yearMonthObject.lengthOfMonth()]; //28  
        
        for(int i = 0; i < daysInMonth.length; i++){
            daysInMonth[i] = i +1;
        }
        
        return daysInMonth;
    }
    
    public static Date dateIncrease(Object origin, int d){
        Date da;
        
        if(origin instanceof String){
            da = stringToDate((String)origin, "00:00");
        } else {
            da = (Date)origin;
        }
        
        Calendar c = Calendar.getInstance();
        c.setTime(da);
        c.add(Calendar.DATE, d);
        
        
        
        return c.getTime();
    }
    
    public static boolean isBetween(String i, String f, String c){
        Date in = stringToDate(i, "00:00");
        Date fi = stringToDate(f, "00:00");
        Date co = stringToDate(c, "00:00");
        
        return isBetween(in, fi, co);
    }
    
    public static boolean isBetween(Date i, Date f, Date c){
        if((c.after(i) || compareDates(c,i) ) && (c.before(f) || compareDates(c, f)) ){
            return true;
        }
        return false;
    }
    
    public static boolean compareDates(Date i, Date f){
        String in = dateToString(i);
        String fi = dateToString(f);
        return (in.equals(fi))? true: false;       
    }
    
    public static boolean beforeThan(Date i, Date f, boolean equal){
        if(equal && compareDates(i, f)){
            return true;
        } else {
            return i.before(f);
        }
    }
    
    public static boolean afterThan(Date i, Date f, boolean equal){
        if(equal && compareDates(i, f)){
            return true;
        } else {
            return i.after(f);
        }
    }
}
