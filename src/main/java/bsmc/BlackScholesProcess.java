package bsmc;

import java.util.ArrayList;
import java.lang.Math;

import bsmc.StandardNormalDistribution;
import bsmc.BlackScholesModel;
import bsmc.FinancialInstrument;
import bsmc.IPayOffEngine;
import bsmc.PayOffEngine;
import bsmc.Calendar;
import bsmc.IBlackScholesProcess;

public class BlackScholesProcess implements bsmc.IBlackScholesProcess {

  private bsmc.BlackScholesModel m_model;
  private bsmc.StandardNormalDistribution m_std;
  
  public BlackScholesProcess( bsmc.BlackScholesModel model, long seed )
  {
    m_model = model;
    m_std = new bsmc.StandardNormalDistribution(seed);
    
  }

  public BlackScholesProcess( bsmc.BlackScholesModel model)
  {
    m_model = model;
    m_std = new bsmc.StandardNormalDistribution();
    
  }
  
  public bsmc.BlackScholesModel getModel()
  {
    return m_model;
  }
  
  /*
  Simulate S path
  */
  public java.util.ArrayList<Double> simulatePath(int days) {
    java.util.ArrayList<Double> _ret = new java.util.ArrayList<Double>( days );

    

    double _S0 = m_model.getPrice();
    double _sigma = m_model.getImpliedVol();
    double _rate = m_model.getRate();

    double _S = _S0;
    
    _ret.add(_S0);
    for(int i=1; i<=days; i++){
      double _yearFrac = days / 365.0 ;
      double _first = ( _rate - _sigma * _sigma / 2 ) * (_yearFrac) ;
      double _second = _sigma * m_std.getSample() * java.lang.Math.sqrt( (_yearFrac) ) ;
      _S = _S0*  java.lang.Math.exp( _first + _second ) ;
      _ret.add( _S );      
    }
    return _ret;
  }

  /*
  Simulate S path
  */
  public java.util.ArrayList<Double> simulateLogPath(int days) {
    java.util.ArrayList<Double> _ret = new java.util.ArrayList<Double>( days );
    

    double _S0 = m_model.getPrice();
    double _sigma = m_model.getImpliedVol();
    double _rate = m_model.getRate();

    double _logS0 = java.lang.Math.log( _S0 );
    
    _ret.add(_logS0);
    for(int i=1; i<=days; i++){
      double _yearFrac = days / 365.0 ;
      double _first = ( _rate - _sigma * _sigma / 2 ) * (_yearFrac) ;
      double _second = _sigma * m_std.getSample() * java.lang.Math.sqrt( (_yearFrac) ) ;
      double _S = _logS0+  _first + _second  ;
      _ret.add( _S );      
    }
    return _ret;
  }

  /*
  Simulate S 
  */
  public double simulateSample(bsmc.Date expiry)
  {
    return java.lang.Math.exp( simulateLogSample( expiry ) );
  }

  /*
  Simulate S 
  */
  public double simulateLogSample(bsmc.Date date)
  {
    bsmc.Date _valdt = m_model.getValuationDate();
    
    double _S0 = m_model.getPrice();
    double _sigma = m_model.getImpliedVol();
    double _rate = m_model.getRate();

    double _logS0 = java.lang.Math.log( _S0 );
    
    double _yearFrac = bsmc.Calendar.getYearFrac(_valdt, date);
    double _first = ( _rate - _sigma * _sigma / 2 ) * (_yearFrac) ;
    double _second = _sigma * m_std.getSample() * java.lang.Math.sqrt( (_yearFrac) ) ;
    double _logS = _logS0 +  _first + _second  ;
    return _logS;
    
  }
}