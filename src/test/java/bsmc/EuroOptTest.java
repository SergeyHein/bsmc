package bsmc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bsmc.Date;
import java.lang.Math;

import bsmc.MCPricer;
import bsmc.BlackScholesModel;
import bsmc.EuroOptProduct;
import bsmc.StandardNormalDistribution;
import bsmc.Calendar;
import bsmc.CallPutEnum;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;


/**
 * Testing EuroOpt pricing
 */
public class EuroOptTest 
{
    private static final double  m_soft_tolerance = 0.05;

    /*
    private shared state
    */
    
    private bsmc.Date m_valDate;
    private bsmc.Date m_maturityDate;
    private double m_strike;
    private bsmc.EuroOptProduct m_call_product;
    private bsmc.EuroOptProduct m_put_product;
    private bsmc.BlackScholesModel m_model;
    private bsmc.MCPricer m_pricer;
      
    @Before
    public void setUp() {
      m_valDate = new bsmc.Date(2021,07,06);
      m_maturityDate = new bsmc.Date(2022,07,06);
      m_strike = 105;
      bsmc.CallPutEnum _callput = bsmc.CallPutEnum.CALL;
      m_call_product = new bsmc.EuroOptProduct(m_strike, m_maturityDate,  _callput);

      _callput = bsmc.CallPutEnum.PUT;
      m_put_product = new bsmc.EuroOptProduct(m_strike, m_maturityDate, _callput);


      m_model = new bsmc.BlackScholesModel (100, m_valDate, 0.05, 0.01);
      m_pricer = new bsmc.MCPricer(20000, m_model);
    }
 
    @After
    public void tearDown() {
      // pass
    }
    
    /**
     * Test Call Price
     */
    @Test
    public void testCallPrice()
    {
      double _expectedPrice = getCallPrice(m_model, m_strike, m_maturityDate );
      double _valPrice = m_pricer.getPrice( m_call_product );
      assertEquals("Price",_expectedPrice, _valPrice, m_soft_tolerance );
    }

    /**
     * Test Call Delta
     */
    @Test
    public void testCallDelta()
    {

      double _expectedDelta = getCallDelta(m_model, m_strike, m_maturityDate );
      double _valDelta = m_pricer.getDelta( m_call_product );
      assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }
    /**
     * Test Call Vega
     */
    @Test
    public void testCallVega()
    {
    
      double _expectedVega = getCallVega(m_model, m_strike, m_maturityDate );
      double _valVega = m_pricer.getVega( m_call_product );
      assertEquals("Vega", _expectedVega, _valVega, m_soft_tolerance );
    }
    /**
     * Test Call Gamma
     */
    @Test
    public void testCallGamma()
    {
      double _expectedGamma = getCallGamma(m_model, m_strike, m_maturityDate );
      double _valGamma = m_pricer.getGamma( m_call_product );
      assertEquals("Gamma", _expectedGamma, _valGamma, m_soft_tolerance );
    }


    /**
     * Test Put Price
     */
    @Test
    public void testPutPrice()
    {
      double _expectedPrice = getPutPrice(m_model, m_strike, m_maturityDate );
      double _valPrice = m_pricer.getPrice(m_put_product);
      assertEquals("Price", _expectedPrice, _valPrice, m_soft_tolerance );
    }
    /**
     * Test Put Delta
     */
    @Test
    public void testPutDelta()
    {
      double _expectedDelta = getPutDelta(m_model, m_strike, m_maturityDate );
      double _valDelta = m_pricer.getDelta(m_put_product);
      assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }
    
    /**
     * Test Put Vega
     */
    @Test
    public void testPutVega()
    {
      double _expectedVega = getPutVega(m_model, m_strike, m_maturityDate );
      double _valVega= m_pricer.getVega(m_put_product);
      assertEquals("Vega", _expectedVega, _valVega, m_soft_tolerance );
    }
    
    /**
     * Test Put Price Gamma
     */
    @Test
    public void testPutGamma()
    {
      double _expectedGamma = getPutGamma(m_model, m_strike, m_maturityDate );
      double _valGamma = m_pricer.getGamma(m_put_product);
      assertEquals("Gamma", _expectedGamma, _valGamma, m_soft_tolerance );
    }

    /*
      https://www.investopedia.com/terms/b/blackscholes.asp
    */
    private double getCallPrice(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      bsmc.Date _valdt = model.getValuationDate();
      double _implVol = model.getImpliedVol();
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _d1 =  ( java.lang.Math.log( _price / strike ) + ( _rate  + _implVol * _implVol / 2 ) * _yearFrac ) / (_implVol * java.lang.Math.sqrt(_yearFrac) );
      double _d2 = _d1 - _implVol * java.lang.Math.sqrt( _yearFrac );

      double _cdf_d1 =  bsmc.StandardNormalDistribution.cdf( _d1 );
      double _cdf_d2 = bsmc.StandardNormalDistribution.cdf( _d2 );

      
      double ret =  _cdf_d1 * _price -  _cdf_d2 * strike * java.lang.Math.exp ( - _rate * _yearFrac );
      
      return ret;
    }

    private double getCallDelta(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      bsmc.Date _valdt = model.getValuationDate();
      double _implVol = model.getImpliedVol();
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _d1 =  ( java.lang.Math.log( _price / strike ) + ( _rate  + _implVol * _implVol / 2 ) * _yearFrac ) / (_implVol * java.lang.Math.sqrt(_yearFrac) );

      double _cdf_d1 =  bsmc.StandardNormalDistribution.cdf( _d1 );

      double ret = _cdf_d1;
      
      return ret;
    }

    private double getCallVega(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      bsmc.Date _valdt = model.getValuationDate();
      double _implVol = model.getImpliedVol();
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _d1 =  ( java.lang.Math.log( _price / strike ) + ( _rate  + _implVol * _implVol / 2 ) * _yearFrac ) / (_implVol * java.lang.Math.sqrt(_yearFrac) );

      double _pdf_d1 =  bsmc.StandardNormalDistribution.pdf( _d1 );

      double ret = _price * _pdf_d1 * java.lang.Math.sqrt(_yearFrac);

      return ret;
    }

    private double getCallGamma(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      bsmc.Date _valdt = model.getValuationDate();
      double _implVol = model.getImpliedVol();
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _d1 =  ( java.lang.Math.log( _price / strike ) + ( _rate  + _implVol * _implVol / 2 ) * _yearFrac ) / (_implVol * java.lang.Math.sqrt(_yearFrac) );

      double _pdf_d1 =  bsmc.StandardNormalDistribution.pdf( _d1 );

      double ret = _pdf_d1 / ( _price * _implVol * java.lang.Math.sqrt(_yearFrac) );
      
      return ret;
    }
    
    private double getPutPrice(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      
      bsmc.Date _valdt = model.getValuationDate();
      
      double _callPrice = getCallPrice(model, strike, expiry);
      
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _strikePrice = strike / java.lang.Math.exp( _rate * _yearFrac );
      
      double ret =  _callPrice + _strikePrice - _price;
      
      return ret;
    }

    private double getPutDelta(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      
      double _price = model.getPrice();
      bsmc.Date _valdt = model.getValuationDate();
      double _implVol = model.getImpliedVol();
      double _rate = model.getRate();
    
      double _yearFrac = bsmc.Calendar.getYearFrac( _valdt, expiry );
      
      double _d1 =  ( java.lang.Math.log( _price / strike ) + ( _rate  + _implVol * _implVol / 2 ) * _yearFrac ) / (_implVol * java.lang.Math.sqrt(_yearFrac) );

      double _cdf_neg_d1 =  bsmc.StandardNormalDistribution.cdf( - _d1 );

      double ret = - _cdf_neg_d1;
      
      return ret;
    }
    
    private double getPutVega(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      return getCallVega ( model, strike, expiry );
    }

    private double getPutGamma(bsmc.BlackScholesModel model,
                                double strike,
                                bsmc.Date expiry)
    {
      return getCallGamma ( model, strike, expiry );
    }


}
