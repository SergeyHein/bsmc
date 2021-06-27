package bsmc;

import java.time.LocalDate;
import bsmc.Date;
import bsmc.IPayOffEngine;

/*
FinancialInstrument interface
  Used by the pricing engine andsupport following payoff profiles:
(+) European Call / Put Option (Strike K, Maturity Date T, OptionType Call/Put)
(+) KnockIn/KnockOut Barrier Call/Put Option (Strike K, Maturity Date T, BarrierType (UpIn, DownIn, UpOut, DownOut), OptionType Call/Put. Barrier

Interface supports path-dependent and autocallable payoffs.
Intraday underlying values are ignored, only end of day values are considered relevant for pricing
*/
interface FinancialInstrument {

  double getDiscountedPayoff(bsmc.IPayOffEngine payoff);
  
}