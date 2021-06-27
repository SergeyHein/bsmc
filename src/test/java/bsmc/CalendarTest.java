package bsmc;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import bsmc.Date;


import org.junit.Test;


/**
 * Testing Date interfacce
 */
public class CalendarTest 
{

    private static final int m_year = 2021;
    private static final int m_month = 12;
    private static final int m_day = 11;
  
    /**
     * Test getDaysDuration
     */
    @Test
    public void testDaysDurartion1()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      bsmc.Date _newDate = new bsmc.Date( m_year, m_month, m_day);
      
      assertEquals( 0, _date.getDaysDuration(_newDate) );
    }
    

    /**
     * Test getDaysDuration
     */
    @Test
    public void testDaysDurartion2()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      bsmc.Date _newDate = new bsmc.Date( m_year, m_month, m_day+1);
      
      assertEquals( 1, _date.getDaysDuration(_newDate) );
    }


    /**
     * Test getDaysDuration
     */
    @Test
    public void testDaysDurartion3()
    {
      bsmc.Date _date = new bsmc.Date( m_year, m_month, m_day);
      bsmc.Date _newDate = new bsmc.Date( m_year+1, m_month, m_day);
      
      assertEquals( 365, _date.getDaysDuration(_newDate) );
    }


}
