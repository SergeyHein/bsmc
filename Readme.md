# Intro
Simple Monte-Carlo pricing engine for pricing financial instruments on the 1-dimensional Black-Scholes model

# Stochastic Model

We consider the 1-dimensional Black-Scholes-Model, i.e. a single underlying follows a Geometric Brownian 
Motion over time. Input parameters are the underlying price S as per the valuation date D and the 
annualized constant implied volatility 𝜎 and risk-free annual interest rate r. 
We assume these are risk neutral parameters, i.e. the model price can be calculated as the discounted 
expected payoff under the above probability distribution.

# Pricing Engine

MCPricer class : initialized with the model parameters and number of paths and is 
able to price the below financial instruments

# FinancialInstrument interface - used by MCPricer.
Following payoff profiles are supported

  * European Call / Put Option (Strike K, Maturity Date T, OptionType Call/Put)

  * KnockIn/KnockOut Barrier Call/Put Option (Strike K, Maturity Date T, BarrierType (UpIn, DownIn, UpOut, DownOut), OptionType Call/Put. Barrier
  
The FinancialInstrument interface supports path-dependent and autocallable payoffs. We assume for simplicity that intraday underlying values are not relevant, but only end of day values)

# Implementation details

## Interfaces: 
  * bsmc.IBlackScholesProcess
    * bsmc.BlackScholesMode getModel()
    * double simulatePath(int days)
    * double simulateSample(bsmc.Date)
  * bsmc.IPayOffEngine
    * double getDiscountedPayoff(bsmc.EuroOptProduct product)
    * double getDiscountedPayoff(bsmc.BarrierOptProduct product)    
  * bsmc.FinancialInstrument
  
## Core
  * bsmc.Calendar
  * bsmc.CallPutEnum
  * bsmc.BarrierTypeEnum
  * bsmc.BarrierObserver
    * BarrierObserver(bsmc.BarrierTypeEnum, double barrier)
    * boolean isAlive()
    * boolean isValid
  * bsmc.Date
  * bsmc.StandardNormalDistribution.  Seed for random number generator initialisation can be passed as optional parameter to support greeks calculation
  
## Implementation
  * bsmc.BlackScholesModel
    * BlackScholesModel(double price, bsmc.Date valdt, double implVol, double rate)
  * bsmc.BlackScholesProcess : implements IBlackScholesProcess
    * bsmc.BlackScholesMode getModel()
    * double simulatePath(int days)
    * double simulateSample(bsmc.Date)
  * bsmc.EuroOptProduct implements bsmc.FinancialInstrument
  * bsmc.BarrierOptProduct implements bsmc.FinancialInstrument
  * bsmc.MCPricer
    * double getPrice (bsmc.FinancialInstrument product)
    * double getDelta (bsmc.FinancialInstrument product)
    * double getGamma (bsmc.FinancialInstrument product)
    * double getVega (bsmc.FinancialInstrument product)
  * bsmc.PayOffEngine: implements bsmc.IPayOffEngine
    * double getDiscountedPayoff(bsmc.EuroOptProduct product)
    * double getDiscountedPayoff(bsmc.BarrierOptProduct product)    

## Main
  * PricerAppCLI
  
## Tests
  * BlackScholesProcessTest
    * testLogPrice - simple test of underlying log price at maturity
  * CalendarTest
    * testDaysDurartion1, testDaysDurartion2, testDaysDurartion3 - simple tests
  * DateTest
    * testYear, testMonth, testDay, testToLocalDate - simple tests
  * EuroOptTest ( comparison agaist analytical solutions of Black-Scholes Model)
    * testCallPrice, testCallDelta, testCallVega, testCallGamma
    * testPutPrice, testPutDelta, testPutVega, testPutGamma
  * BarrierOptTest ( TODO )
    * TODO
  * StandardNormalDistributionTest
    * testCdf0, testCdfPos1, testCdfNeg1 - simple CDF tests at 0, -1 and +1
    * testPdf0, testPdfPos1, testPdfNeg1 - simple PDF tests at 0, -1 and +1
    * testSample5000, testSample20000, testSample100000  mean tests

# HowTo

##  Build 
  
  mvn -Dmaven.test.failure.ignore=true package

## Execute 
  
  java -jar target\bsmc-1.0-SNAPSHOT.jar

  *Note*: jar does not provide any UI, only API is exposed.

# Asumption
 * All input parameters are expected to be correct, e.g. rates and prices are positive
  
# Open points - opportunities for improvement
  * Testing of BarrierOptProduct
  * Validation of input parameters
  * optimal choice of npaths for Monte Carlo simulations
  * optimisation for simultaneous Price, Delta, Vega and Gamma calculations
  * Adress nondeterministic outcome of unit tests
  
# PS

This was an exciting opportunity for implementation of basic quantitative models from scratch using java language.
