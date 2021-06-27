package bsmc;

import bsmc.BlackScholesModel;
import java.util.ArrayList;
import bsmc.Date;

interface IBlackScholesProcess {

  bsmc.BlackScholesModel getModel();
  /*
  Simulate S path
  */
  java.util.ArrayList<Double> simulatePath(int days);
  java.util.ArrayList<Double> simulateLogPath(int days);

  /*
  Simulate S 
  */
  double simulateSample(bsmc.Date date);
  double simulateLogSample(bsmc.Date date);

  
}