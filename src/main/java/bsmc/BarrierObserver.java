package bsmc;

import bsmc.BarrierTypeEnum;
import bsmc.BarrierStatusEnum;

public class BarrierObserver {
  private double m_barrier;
  private bsmc.BarrierTypeEnum m_barrierType;
  private bsmc.BarrierStatusEnum m_barrierStatus;
  private boolean m_alive;
  BarrierObserver( bsmc.BarrierTypeEnum barrierType,
                     double barrier
      )
  {
    m_barrierType = barrierType;
    m_barrier = barrier;
    m_barrierStatus = bsmc.BarrierStatusEnum.NA;
  }

  public void observePrice(double price)
  {
    if (m_barrierStatus==bsmc.BarrierStatusEnum.NA)
    {
      switch (m_barrierType) {
            case UPIN:
                     m_barrierStatus = ( price > m_barrier ? bsmc.BarrierStatusEnum.BREACHED : m_barrierStatus);
                     break;
            case DOWNIN:
                     m_barrierStatus = ( price < m_barrier ? bsmc.BarrierStatusEnum.BREACHED : m_barrierStatus);           
                     break;
            case UPOUT:
                     m_barrierStatus = ( price > m_barrier ? bsmc.BarrierStatusEnum.BREACHED : m_barrierStatus);
                     break;
            case DOWNOUT:
                     m_barrierStatus = ( price < m_barrier ? bsmc.BarrierStatusEnum.BREACHED : m_barrierStatus);           
                     break;
        }
    }
  }
  
  /*
  Checks if option is alive,
  i.e. either of OUT barrier has not been touched yet
  */
  public boolean isAlive()
  {
    boolean ret=false;
    if ( (m_barrierType==bsmc.BarrierTypeEnum.UPIN || m_barrierType==bsmc.BarrierTypeEnum.DOWNIN)  )
    {
      ret = true;
    }
    else if ( (m_barrierType==bsmc.BarrierTypeEnum.UPOUT || m_barrierType==bsmc.BarrierTypeEnum.DOWNOUT) && m_barrierStatus == bsmc.BarrierStatusEnum.NA )
    {
      ret = true;
    }
    return ret;
  }
  
  /*
  Checks if option contract has been initiated,
  i.e. knock-in barrier breached or knock-out barrier not breached
  */
  public boolean isValid()
  {
    boolean ret=false;
    if ( (m_barrierType==bsmc.BarrierTypeEnum.UPIN || m_barrierType==bsmc.BarrierTypeEnum.DOWNIN) && m_barrierStatus == bsmc.BarrierStatusEnum.BREACHED )
    {
      ret = true;
    }
    else if ( (m_barrierType==bsmc.BarrierTypeEnum.UPOUT || m_barrierType==bsmc.BarrierTypeEnum.DOWNOUT) && m_barrierStatus == bsmc.BarrierStatusEnum.NA )
    {
      ret = true;
    }
    return ret;
  }

  
}