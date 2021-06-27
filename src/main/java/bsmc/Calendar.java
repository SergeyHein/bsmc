package bsmc;

import java.time.LocalDate;
import java.time.Duration;
import java.lang.Math;

/*
Calendar class representing a Calendar
*/
public class Calendar {
  
  public static int getDaysDuration(Date d1, Date d2)
  {
      java.time.LocalDate _d1=  java.time.LocalDate.of(d1.getYear(), d1.getMonth(), d1.getDay() );
      java.time.LocalDate _d2=  java.time.LocalDate.of(d2.getYear(), d2.getMonth(), d2.getDay() );
      long _longDays = java.time.Duration.between(_d1.atStartOfDay(), _d2.atStartOfDay()).toDays();
      int ret = java.lang.Math.toIntExact( _longDays );
      return ret;
  }
 
  public static double getYearFrac(Date d1, Date d2)
  {
      return getDaysDuration(d1, d2) / 365;
  }
  
}