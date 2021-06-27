package bsmc;

import java.time.LocalDate;
import java.time.Duration;
import java.lang.Math;

/*
Date class representing a date
*/
public class Date {
  private int m_yyyy;
  private int m_mm;
  private int m_dd;

  Date(int yyyy,
      int mm,
      int dd
      )
  {
    m_yyyy = yyyy;
    m_mm = mm;
    m_dd = dd;
  }

  Date( java.time.LocalDate jdate )
  {
    m_yyyy = jdate.getYear();
    m_mm = jdate.getMonthValue();
    m_dd = jdate.getDayOfMonth();
  }
  
  public int getYear()
  {
    return m_yyyy;
  }

  public int getMonth()
  {
    return m_mm;
  }

  public int getDay()
  {
    return m_dd;
  }

  public java.time.LocalDate toLocalDate()
  {
    return java.time.LocalDate.of(m_yyyy, m_mm, m_dd );
  }

  
  public int getDaysDuration(Date d2)
  {
      java.time.LocalDate _d1=  java.time.LocalDate.of(m_yyyy, m_mm, m_dd );
      java.time.LocalDate _d2=  java.time.LocalDate.of(d2.getYear(), d2.getMonth(), d2.getDay() );
      long _longDays = java.time.Duration.between(_d1.atStartOfDay(), _d2.atStartOfDay()).toDays();
      int ret = java.lang.Math.toIntExact( _longDays );
      return ret;
  }
  
}