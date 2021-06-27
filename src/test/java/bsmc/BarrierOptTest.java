package bsmc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import bsmc.Date;
import java.lang.Math;

import bsmc.MCPricer;
import bsmc.BlackScholesModel;
import bsmc.BarrierOptProduct;
import bsmc.StandardNormalDistribution;
import bsmc.Calendar;
import bsmc.CallPutEnum;
import bsmc.BarrierTypeEnum;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;


/**
 * Testing BarrierOpt pricing
 */
public class BarrierOptTest 
{
    private static final double  m_soft_tolerance = 0.5;

    /*
    private shared state
    */
    
    private bsmc.Date m_valDate;
    private bsmc.Date m_maturityDate;
    private bsmc.BarrierOptProduct m_knock_upin_call_product;
    private bsmc.BarrierOptProduct m_knock_downin_put_product;

    private bsmc.BarrierOptProduct m_knock_upout_call_product;
    private bsmc.BarrierOptProduct m_knock_downout_put_product;


    private bsmc.BlackScholesModel m_model;
    private bsmc.MCPricer m_pricer;
      
    @Before
    public void setUp() {
      m_valDate = new bsmc.Date(2021,07,06);
      m_maturityDate = new bsmc.Date(2022,07,06);
      double _strike = 105;
      double _barrier = 110;
      bsmc.CallPutEnum _callput = bsmc.CallPutEnum.CALL;
      bsmc.BarrierTypeEnum _barrierType = bsmc.BarrierTypeEnum.UPIN;
      m_knock_upin_call_product = new bsmc.BarrierOptProduct(_strike, m_maturityDate, _barrierType, _callput, _barrier);
      _barrierType = bsmc.BarrierTypeEnum.UPOUT;
      m_knock_upout_call_product = new bsmc.BarrierOptProduct(_strike, m_maturityDate, _barrierType, _callput, _barrier);

      _strike = 95;
      _barrier = 90;
      _callput = bsmc.CallPutEnum.PUT;
      _barrierType = bsmc.BarrierTypeEnum.DOWNIN;
      m_knock_downin_put_product = new bsmc.BarrierOptProduct(_strike, m_maturityDate, _barrierType, _callput, _barrier);
      _barrierType = bsmc.BarrierTypeEnum.DOWNOUT;
      m_knock_downout_put_product = new bsmc.BarrierOptProduct(_strike, m_maturityDate, _barrierType, _callput, _barrier);


      m_model = new bsmc.BlackScholesModel (100, m_valDate, 0.05, 0.01);
      m_pricer = new bsmc.MCPricer(20000, m_model);
    }
 
    @After
    public void tearDown() {
      // pass
    }
    
    /**
     * Test UpIn Price : TODO Imiplement
     */
    @Test
    public void testUpInCallPrice()
    {
      //double _expectedPrice = 10;
      double _valPrice = m_pricer.getPrice( m_knock_upin_call_product );
      System.out.println("UpIn Call Price " + String.valueOf(_valPrice));
      //assertEquals("Price",_expectedPrice, _valPrice, m_soft_tolerance );
    }

    /**
     * Test UpIn Delta : TODO Imiplement
     */
    @Test
    public void testUpInCallDelta()
    {

      //double _expectedDelta = 19;
      double _valDelta = m_pricer.getDelta( m_knock_upin_call_product );
      System.out.println("UpIn Call Delta " + String.valueOf(_valDelta));      
      //assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }

    /**
     * Test UpIn Vega : TODO Imiplement
     */
    @Test
    public void testUpInCallVega()
    {

      //double _expectedDelta = 19;
      double _valVega = m_pricer.getVega( m_knock_upin_call_product );
      System.out.println("UpIn Call Vega " + String.valueOf(_valVega));      
      //assertEquals("Vega", _expectedDelta, _valVega, m_soft_tolerance );
    }

    /**
     * Test UpIn Gamma : TODO Imiplement
     */
    @Test
    public void testUpInCallGamma()
    {

      //double _expectedDelta = 19;
      double _valGamma = m_pricer.getVega( m_knock_upin_call_product );
      System.out.println("UpIn Call Gamma " + String.valueOf(_valGamma));      
      //assertEquals("Gamma", _expectedDelta, _valGamma, m_soft_tolerance );
    }

    /**
     * Test DownIn Price : TODO Imiplement
     */
    @Test
    public void testDownInPutPrice()
    {
      //double _expectedPrice = 10;
      double _valPrice = m_pricer.getPrice( m_knock_downin_put_product );
      System.out.println("DownIn Put Price " + String.valueOf(_valPrice));
      //assertEquals("Price",_expectedPrice, _valPrice, m_soft_tolerance );
    }

    /**
     * Test DownIn Delta : TODO Imiplement
     */
    @Test
    public void testUpInPutDelta()
    {

      //double _expectedDelta = 19;
      double _valDelta = m_pricer.getDelta( m_knock_downin_put_product );
      System.out.println("DownIn Put Delta " + String.valueOf(_valDelta));      
      //assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }

    /**
     * Test DownIn Vega : TODO Imiplement
     */
    @Test
    public void testUpInPutVega()
    {

      //double _expectedDelta = 19;
      double _valVega = m_pricer.getVega( m_knock_downin_put_product );
      System.out.println("DownIn Put Vega " + String.valueOf(_valVega));      
      //assertEquals("Vega", _expectedDelta, _valVega, m_soft_tolerance );
    }

    /**
     * Test DownIn Gamma : TODO Imiplement
     */
    @Test
    public void testUpInPutGamma()
    {

      //double _expectedDelta = 19;
      double _valGamma = m_pricer.getVega( m_knock_downin_put_product );
      System.out.println("DownIn Put Gamma " + String.valueOf(_valGamma));      
      //assertEquals("Gamma", _expectedDelta, _valGamma, m_soft_tolerance );
    }

    /**
     * Test UpOut Price : TODO Imiplement
     */
    @Test
    public void testUpOutCallPrice()
    {
      //double _expectedPrice = 10;
      double _valPrice = m_pricer.getPrice( m_knock_upout_call_product );
      System.out.println("UpOut Call Price " + String.valueOf(_valPrice));
      //assertEquals("Price",_expectedPrice, _valPrice, m_soft_tolerance );
    }

    /**
     * Test UpOut Delta : TODO Imiplement
     */
    @Test
    public void testUpOutCallDelta()
    {

      //double _expectedDelta = 19;
      double _valDelta = m_pricer.getDelta( m_knock_upout_call_product );
      System.out.println("UpOut Call Delta " + String.valueOf(_valDelta));      
      //assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }

    /**
     * Test UpOut Vega : TODO Imiplement
     */
    @Test
    public void testUpOutCallVega()
    {

      //double _expectedDelta = 19;
      double _valVega = m_pricer.getVega( m_knock_upout_call_product );
      System.out.println("UpOut Call Vega " + String.valueOf(_valVega));      
      //assertEquals("Vega", _expectedDelta, _valVega, m_soft_tolerance );
    }

    /**
     * Test UpOut Gamma : TODO Imiplement
     */
    @Test
    public void testUpOutCallGamma()
    {

      //double _expectedDelta = 19;
      double _valGamma = m_pricer.getVega( m_knock_upout_call_product );
      System.out.println("UpOut Call Gamma " + String.valueOf(_valGamma));      
      //assertEquals("Gamma", _expectedDelta, _valGamma, m_soft_tolerance );
    }

    /**
     * Test DownOut Price : TODO Imiplement
     */
    @Test
    public void testDownOutPutPrice()
    {
      //double _expectedPrice = 10;
      double _valPrice = m_pricer.getPrice( m_knock_downout_put_product );
      System.out.println("DownOut Put Price " + String.valueOf(_valPrice));
      //assertEquals("Price",_expectedPrice, _valPrice, m_soft_tolerance );
    }

    /**
     * Test DownOut Delta : TODO Imiplement
     */
    @Test
    public void testUpOutPutDelta()
    {

      //double _expectedDelta = 19;
      double _valDelta = m_pricer.getDelta( m_knock_downout_put_product );
      System.out.println("DownOut Put Delta " + String.valueOf(_valDelta));      
      //assertEquals("Delta", _expectedDelta, _valDelta, m_soft_tolerance );
    }

    /**
     * Test DownOut Vega : TODO Imiplement
     */
    @Test
    public void testUpOutPutVega()
    {

      //double _expectedDelta = 19;
      double _valVega = m_pricer.getVega( m_knock_downout_put_product );
      System.out.println("DownOut Put Vega " + String.valueOf(_valVega));      
      //assertEquals("Vega", _expectedDelta, _valVega, m_soft_tolerance );
    }

    /**
     * Test DownOut Gamma : TODO Imiplement
     */
    @Test
    public void testUpOutPutGamma()
    {

      //double _expectedDelta = 19;
      double _valGamma = m_pricer.getVega( m_knock_downout_put_product );
      System.out.println("DownOut Put Gamma " + String.valueOf(_valGamma));      
      //assertEquals("Gamma", _expectedDelta, _valGamma, m_soft_tolerance );
    }
    

}
