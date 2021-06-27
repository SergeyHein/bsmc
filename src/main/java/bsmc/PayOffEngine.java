package bsmc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;
import bsmc.Date;
import bsmc.IPayOffEngine;
import bsmc.EuroOptProduct;
import bsmc.BarrierOptProduct;
import bsmc.CallPutEnum;
import bsmc.Calendar;
import bsmc.BlackScholesModel;
import bsmc.BarrierObserver;
import bsmc.BarrierTypeEnum;

public class PayOffEngine implements bsmc.IPayOffEngine {
  private bsmc.IBlackScholesProcess m_process;

  PayOffEngine(bsmc.IBlackScholesProcess process)
  {
    m_process = process;
  }
  
  public double getDiscountedPayoff(bsmc.EuroOptProduct product )
  {
    bsmc.Date _valDate = m_process.getModel().getValuationDate();
    double _rate = m_process.getModel().getRate();
    
    bsmc.Date _maturity = product.getMaturity();

    double _final = m_process.simulateSample( _maturity );
    double _strike = product.getStrike();
    double _payoff = 0;
    if ( product.getCallPut() == bsmc.CallPutEnum.CALL )
    {
      _payoff = java.lang.Math.max (0, _final - _strike );
    }
    else
    {
      _payoff = java.lang.Math.max (0, _strike - _final);
    }
    
    return _payoff / java.lang.Math.exp( _rate * bsmc.Calendar.getYearFrac(_valDate, _maturity ) ); 
  }
  
  public double getDiscountedPayoff(bsmc.BarrierOptProduct product )
  {
    double ret = 0;
    bsmc.Date _valDate = m_process.getModel().getValuationDate();
    double _rate = m_process.getModel().getRate();
    
    double _strike = product.getStrike();
    double _barrier = product.getBarrier();
    bsmc.BarrierTypeEnum _barrierType = product.getBarrierType();
    bsmc.BarrierObserver _barrierObserver = new bsmc.BarrierObserver( _barrierType, _barrier);
    
    bsmc.Date _maturity = product.getMaturity();
    int _ndays=bsmc.Calendar.getDaysDuration(_valDate, _maturity);
    
    java.util.ArrayList<Double> _path = m_process.simulatePath(_ndays);
    
    java.util.Iterator<Double> _iter = _path.iterator();
    while (_iter.hasNext() && _barrierObserver.isAlive() )
    {
       _barrierObserver.observePrice( _iter.next() );
    }

    if (_barrierObserver.isValid())
    {
      double _payoff = 0;
      double _final = _path.get(_path.size()-1);
      if ( product.getCallPut() == bsmc.CallPutEnum.CALL )
      {
        _payoff = java.lang.Math.max (0, _final - _strike );
      }
      else
      {
        _payoff = java.lang.Math.max (0, _strike - _final);
      }
      ret = _payoff / java.lang.Math.exp( _rate * bsmc.Calendar.getYearFrac(_valDate, _maturity ) ); 
    }
    
    return ret;
  }
 
  
}