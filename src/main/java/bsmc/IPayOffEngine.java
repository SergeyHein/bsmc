package bsmc;

import java.time.LocalDate;
import java.util.ArrayList;
import bsmc.EuroOptProduct;
import bsmc.BarrierOptProduct;

interface IPayOffEngine {

  double getDiscountedPayoff( bsmc.EuroOptProduct product );
  double getDiscountedPayoff( bsmc.BarrierOptProduct product );
  
  
}