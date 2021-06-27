package bsmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.Math;
import bsmc.Date;
import bsmc.FinancialInstrument;
import bsmc.IPayOffEngine;
import bsmc.CallPutEnum;
import bsmc.BarrierTypeEnum;

public class BarrierOptProduct implements bsmc.FinancialInstrument {
  private bsmc.Date m_maturity;
  private double m_strike;
  private double m_barrier;
  private bsmc.CallPutEnum m_callput;
  private bsmc.BarrierTypeEnum m_barrierType;

  BarrierOptProduct(double strike,
                 bsmc.Date maturity,
                 bsmc.BarrierTypeEnum barrierType,
                 bsmc.CallPutEnum callput,
                 double barrier
      )
  {
    m_strike = strike;
    m_maturity = maturity;
    m_barrierType = barrierType;
    m_callput = callput;
    m_barrier = barrier;
  }
  
  public bsmc.Date getMaturity()
  {
    return m_maturity;
  }

  public double getStrike()
  {
    return m_strike;
  }

  public double getBarrier()
  {
    return m_barrier;
  }
  
  public bsmc.CallPutEnum getCallPut()
  {
    return m_callput;
  }

  public bsmc.BarrierTypeEnum getBarrierType()
  {
    return m_barrierType;
  }
  
  public double getDiscountedPayoff(bsmc.IPayOffEngine payoffEngine )
  {
    return payoffEngine.getDiscountedPayoff(this);
  }
  
  
}