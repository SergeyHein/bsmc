package bsmc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bsmc.Date;
import java.lang.Math;

import bsmc.BlackScholesModel;
import bsmc.EuroOptProduct;
import bsmc.StandardNormalDistribution;
import bsmc.Calendar;
import bsmc.BlackScholesProcess;
import java.util.ArrayList;

import org.junit.Test;


/**
 * Testing BlackScholesProcess 
 */
public class BlackScholesProcessTest 
{
    private static final double  m_soft_tolerance = 1e-2;

    
    /**
     * Test simulateLogPath
     */
    @Test
    public void testLogPrice()
    {
      double _price = 100;
      double _implVol = 0.05;
      double _rate = 0.01;
      bsmc.Date _valDate = new bsmc.Date(2021,07,06);
      bsmc.Date _expiryDate = new bsmc.Date(2022,07,06);
      bsmc.BlackScholesModel _model = new bsmc.BlackScholesModel (100, _valDate, _implVol, _rate);
      int _ndays = bsmc.Calendar.getDaysDuration(_valDate, _expiryDate) ;
      
      int _npaths = 20000;
      bsmc.BlackScholesProcess _pricer = new bsmc.BlackScholesProcess(_model);
      double _val = 0;
      for (int i = 0; i < _npaths; i++)
      {
        java.util.ArrayList<Double> _paths = _pricer.simulateLogPath( _ndays );
        _val = _val + _paths.get( _paths.size() - 1 );
      }
      _val = _val / _npaths;
      double _expected = java.lang.Math.log( _price ) + ( _rate + _implVol * _implVol / 2 ) * bsmc.Calendar.getYearFrac(_valDate, _expiryDate);
      
      assertEquals( _expected, _val, m_soft_tolerance );

    }


}
