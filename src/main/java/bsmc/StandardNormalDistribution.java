package bsmc;

import java.util.Date;
import java.lang.Math;
import java.util.Random;

public class StandardNormalDistribution {
  private static final double m_const = java.lang.Math.sqrt(2 * java.lang.Math.PI);

  private java.util.Random m_random; 

  StandardNormalDistribution(long seed)
  {
    m_random = new java.util.Random(seed);
  }

  StandardNormalDistribution()
  {
    m_random = new java.util.Random();
  }
  
  public static double pdf(double x)
  {
      return java.lang.Math.exp(-x*x / 2) / m_const ;
  }
    
  public static double cdf(double z)
  {
    /* Cumulative distribution function.  P(X <= z)
      https://introcs.cs.princeton.edu/java/22library/Gaussian.java.html
    */
    if (z < -8.0) return 0.0;
    if (z >  8.0) return 1.0;
    double sum = 0.0, term = z;
    for (int i = 3; sum + term != sum; i += 2) {
        sum  = sum + term;
        term = term * z * z / i;
    }
    return 0.5 + sum * pdf(z);
  }

  public double getSample()
  {
    return m_random.nextGaussian();
  }
  
}