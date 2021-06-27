package bsmc;

import static org.junit.Assert.assertEquals;
import bsmc.StandardNormalDistribution;
import bsmc.Date;
import java.lang.Math;


import org.junit.Test;


/**
 * Testing StandardNormalDistribution
 * Using https://www.math.arizona.edu/~rsims/ma464/standardnormaltable.pdf
 */
public class StandardNormalDistributionTest 
{
  private static final double m_soft_tolerance = 1e-5;
  private static final double m_hard_tolerance = 1e-10;
  
    /**
     * Test cfd
     */
    @Test
    public void testCdf0()
    {
      double x = 0;
      double val  = StandardNormalDistribution.cdf( x );
      double expected  = 0.5;
      assertEquals( expected, val, m_soft_tolerance );
    }

    /**
     * Test cfd
     */
    @Test
    public void testCdfPos1()
    {
      double x = 1;
      double val  = StandardNormalDistribution.cdf( x );
      double expected  = 0.84134;
      assertEquals( expected, val, m_soft_tolerance );
    }


    /**
     * Test cfd
     */
    @Test
    public void testCdfNeg1()
    {
      double x = -1;
      double val  = StandardNormalDistribution.cdf( x );
      double expected  = 0.15866;
      assertEquals( expected, val, m_soft_tolerance );
    }


    /**
     * Test pdf
     */
    @Test
    public void testPdf0()
    {
      double x = 0;
      double val  = StandardNormalDistribution.pdf( x );
      double expected  = 1 / java.lang.Math.sqrt(2 * java.lang.Math.PI);
      assertEquals( expected, val, m_hard_tolerance );
    }

    /**
     * Test pdf
     */
    @Test
    public void testPdfNeg1()
    {
      double x = -1;
      double val  = StandardNormalDistribution.pdf( x );
      double expected  = java.lang.Math.exp(-x*x / 2)  / java.lang.Math.sqrt(2 * java.lang.Math.PI);
      assertEquals( expected, val, m_hard_tolerance );
    }

    /**
     * Test pdf
     */
    @Test
    public void testPdfPos1()
    {
      double x = 1;
      double val  = StandardNormalDistribution.pdf( x );
      double expected  = java.lang.Math.exp(-x*x / 2)  / java.lang.Math.sqrt(2 * java.lang.Math.PI);
      
      assertEquals( expected, val, m_hard_tolerance );
    }
    
    /**
     * Test sample
     */
    @Test
    public void testSample5000()
    {
      bsmc.StandardNormalDistribution _std = new bsmc.StandardNormalDistribution();
      double ret = 0;
      int _n_iter = 5000;
      for ( int i = 0 ; i < _n_iter ; i++)
      {
        ret  = ret + _std.getSample( );
      }
      
      assertEquals( 0, ret / _n_iter, 0.05 );
    }

    /**
     * Test sample
     */
    @Test
    public void testSample20000()
    {
      bsmc.StandardNormalDistribution _std = new bsmc.StandardNormalDistribution();
      double ret = 0;
      int _n_iter = 20000;
      for ( int i = 0 ; i < _n_iter ; i++)
      {
        ret  = ret + _std.getSample( );
      }
      
      assertEquals( 0, ret / _n_iter, 1e-2 );
    }

    /**
     * Test sample
     */
    @Test
    public void testSample100000()
    {
      bsmc.StandardNormalDistribution _std = new bsmc.StandardNormalDistribution();
      double ret = 0;
      int _n_iter = 100000;
      for ( int i = 0 ; i < _n_iter ; i++)
      {
        ret  = ret + _std.getSample( );
      }
      
      assertEquals( 0, ret / _n_iter, 1e-2 );
    }
    
}
