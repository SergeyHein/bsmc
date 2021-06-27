package bsmc;

import bsmc.Date;

public class BlackScholesModel {
  private double m_price;
  private bsmc.Date m_valdt ;
  private double m_implVol;
  private double m_rate;
  BlackScholesModel(double price,
                    bsmc.Date valdt,
                    double implVol,
                    double rate)
  {
    m_price = price;
    m_valdt = valdt;
    m_implVol = implVol;
    m_rate = rate;
  }
  
  public double getPrice()
  {
      return m_price;
  }
  
  public bsmc.Date getValuationDate()
  {
      return m_valdt;
  }

  public double getImpliedVol()
  {
      return m_implVol;
  }


  public double getRate()
  {
      return m_rate;
  }
}