package bsmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.Math;
import bsmc.Date;
import bsmc.FinancialInstrument;
import bsmc.IPayOffEngine;
import bsmc.CallPutEnum;


public class EuroOptProduct implements bsmc.FinancialInstrument {
  private bsmc.Date m_maturity;
  private double m_strike;
  private bsmc.CallPutEnum m_callput;

  EuroOptProduct( double strike,
                  bsmc.Date maturity,
                  bsmc.CallPutEnum callput 
      )
  {
    m_maturity = maturity;
    m_strike = strike;
    m_callput = callput;
  }
  
  public bsmc.Date getMaturity()
  {
    return m_maturity;
  }

  public double getStrike()
  {
    return m_strike;
  }

  public bsmc.CallPutEnum getCallPut()
  {
    return m_callput;
  }
  
  public double getDiscountedPayoff(bsmc.IPayOffEngine payoffEngine )
  {
    return payoffEngine.getDiscountedPayoff(this);
  }
  
  
}