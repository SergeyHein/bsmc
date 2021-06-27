package bsmc;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import bsmc.Date;


import org.junit.Test;


/**
 * Testing Date interfacce
 */
public class DateTest 
{

    private static final int m_year = 2021;
    private static final int m_month = 12;
    private static final int m_day = 11;
  
    /**
     * Test getYear
     */
    @Test
    public void testYear()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      
      assertEquals( m_year, _date.getYear() );
    }


    /**
     * Test getYear
     */
    @Test
    public void testMonth()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      
      assertEquals( m_month, _date.getMonth() );
    }

    /**
     * Test getDay
     */
    @Test
    public void testDay()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      
      assertEquals( m_day, _date.getDay() );
    }

    /**
     * Test toLocalDate
     */
    @Test
    public void testToLocalDate()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day );
      java.time.LocalDate _jdate = java.time.LocalDate.of( m_year, m_month, m_day);
      assertEquals( _jdate, _date.toLocalDate() );
    }
    
}
