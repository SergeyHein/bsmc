package bsmc;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

import bsmc.StandardNormalDistribution;
import bsmc.BlackScholesModel;
import bsmc.FinancialInstrument;
import bsmc.IPayOffEngine;
import bsmc.PayOffEngine;
import bsmc.Calendar;
import bsmc.IBlackScholesProcess;
import bsmc.BlackScholesProcess;

public class MCPricer {

  private int m_npaths;
  private bsmc.BlackScholesModel m_model;
  
  public MCPricer(int npaths,
    bsmc.BlackScholesModel model
    )
  {
    m_npaths = npaths;
    m_model = model;
  }
  
  static private double getPriceImpl(bsmc.FinancialInstrument product, bsmc.IBlackScholesProcess bsProcess, int npaths) {
    java.util.ArrayList<Double> _prices = new java.util.ArrayList<Double>( npaths );
    for(int i=0; i< npaths; i++){
      bsmc.IPayOffEngine payoffEngine = new bsmc.PayOffEngine(bsProcess);
      _prices.add( product.getDiscountedPayoff( payoffEngine ) );
    }
    double ret = 0;

    for(int i=0; i< npaths; i++){
      ret = ret + _prices.get( i );
    }

    ret = ret / _prices.size();
    
    return ret;
  }
  
   public double getPrice(bsmc.FinancialInstrument product) {
    long _seed = (new java.util.Random()).nextLong();
    bsmc.IBlackScholesProcess _bsProcess = new bsmc.BlackScholesProcess( m_model, _seed );     
    return getPriceImpl(product, _bsProcess, m_npaths);
  }

  /*
    Delta - partial derivative of FinancialInstrument's Price with respect to underlying price
  */
   public double getDelta(bsmc.FinancialInstrument product) {
    double _price0 = m_model.getPrice();
    double _delta = _price0 / 100;
    double _price1 = _price0 + _delta;
    
    long _seed = (new java.util.Random()).nextLong();
    
    bsmc.BlackScholesModel _model1 = new bsmc.BlackScholesModel( _price1,
                    m_model.getValuationDate(),
                    m_model.getImpliedVol(),
                    m_model.getRate() );
    bsmc.IBlackScholesProcess _bsProcess0 = new bsmc.BlackScholesProcess( m_model, _seed );
    bsmc.IBlackScholesProcess _bsProcess1 = new bsmc.BlackScholesProcess( _model1, _seed );
    
    double _f0 = getPriceImpl(product, _bsProcess0, m_npaths);
    double _f = getPriceImpl(product, _bsProcess1, m_npaths);
    
    return (_f - _f0 ) / _delta;
    
  }

  /*
    Gamma - second order partial derivative of FinancialInstrument's Price with respect to underlying price
  */
   public double getGamma(bsmc.FinancialInstrument product) {
    double _price0 = m_model.getPrice();
    double _delta = _price0 / 100;
    double _price_left = _price0 - _delta;
    double _price_right = _price0 + _delta;

    long _seed = (new java.util.Random()).nextLong();

    bsmc.BlackScholesModel _model_left = new bsmc.BlackScholesModel( _price_left,
                    m_model.getValuationDate(),
                    m_model.getImpliedVol(),
                    m_model.getRate());

    bsmc.BlackScholesModel _model_right = new bsmc.BlackScholesModel( _price_right,
                    m_model.getValuationDate(),
                    m_model.getImpliedVol(),
                    m_model.getRate());

    bsmc.IBlackScholesProcess _bsProcess0 = new bsmc.BlackScholesProcess( m_model, _seed );
    bsmc.IBlackScholesProcess _bsProcess_left = new bsmc.BlackScholesProcess( _model_left, _seed );
    bsmc.IBlackScholesProcess _bsProcess_right = new bsmc.BlackScholesProcess( _model_right, _seed );
                    
    double _f0 = getPriceImpl(product, _bsProcess0, m_npaths);
    double _f_left = getPriceImpl(product, _bsProcess_left, m_npaths);
    double _f_right = getPriceImpl(product, _bsProcess_right, m_npaths);
    
    return (_f_right - 2*_f0  + _f_left ) / ( _delta * _delta ) ;
    
  }

  /*
    Vega -  partial derivative of FinancialInstrument's Price with respect to implied volatility
  */
   private double getVegaImplFwd(bsmc.FinancialInstrument product) {
    double _implVol0 = m_model.getImpliedVol();
    double _delta = _implVol0 / 100;
    double _implVol1 = _implVol0 + _delta;

    long _seed = (new java.util.Random()).nextLong();
    
    bsmc.BlackScholesModel _model1 = new bsmc.BlackScholesModel( m_model.getPrice(),
                    m_model.getValuationDate(),
                    _implVol1,
                    m_model.getRate());

    bsmc.IBlackScholesProcess _bsProcess0 = new bsmc.BlackScholesProcess( m_model, _seed );
    bsmc.IBlackScholesProcess _bsProcess1 = new bsmc.BlackScholesProcess( _model1, _seed );
    
    double _f0 = getPriceImpl(product, _bsProcess0, m_npaths);
    double _f = getPriceImpl(product, _bsProcess1, m_npaths);
    
    return (_f - _f0 ) / _delta;
    
  }

  /*
    Vega -  partial derivative of FinancialInstrument's Price with respect to implied volatility
  */
   private double getVegaImplMid(bsmc.FinancialInstrument product) {
    double _implVol0 = m_model.getImpliedVol();
    double _delta = _implVol0 / 100;
    double _implVol_left = _implVol0 - _delta/2;
    double _implVol_right = _implVol0 + _delta/2;

    long _seed = (new java.util.Random()).nextLong();
    
    bsmc.BlackScholesModel _model_left = new bsmc.BlackScholesModel( m_model.getPrice(),
                    m_model.getValuationDate(),
                    _implVol_left,
                    m_model.getRate());

    bsmc.BlackScholesModel _model_right = new bsmc.BlackScholesModel( m_model.getPrice(),
                    m_model.getValuationDate(),
                    _implVol_right,
                    m_model.getRate());
                    
    bsmc.IBlackScholesProcess _bsProcess_left = new bsmc.BlackScholesProcess( _model_left, _seed );
    bsmc.IBlackScholesProcess _bsProcess_right = new bsmc.BlackScholesProcess( _model_right, _seed );
    
    double _f0 = getPriceImpl(product, _bsProcess_left, m_npaths);
    double _f = getPriceImpl(product, _bsProcess_right, m_npaths);
    
    return (_f - _f0 ) / _delta;
    
  }
  
  /*
    Vega -  partial derivative of FinancialInstrument's Price with respect to implied volatility
  */
   public double getVega(bsmc.FinancialInstrument product) {
    return getVegaImplMid(product);
    
  }


  
}