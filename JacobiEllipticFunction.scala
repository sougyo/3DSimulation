

import scala.util.control.Breaks

object JacobiEllipticFunction {
  def sn(t: Double, k: Double) = {
    sncndn(t, k)._1
  }
  
  def cn(t: Double, k: Double) = {
    sncndn(t, k)._2
  }
  
  def dn(t: Double, k: Double) = {
    sncndn(t, k)._3
  }
  
  def sncndn(t: Double, k: Double): (Double, Double, Double) = {
    val emc = 1 - k * k
    
    if (emc != 0) {
      cal_sncndn(t, emc)
    } else {
      val cn = 1d / Math.cosh(t)
      (Math.tanh(t), cn, cn) 
    }
  }
  
  private def cal_sncndn(_t: Double, _emc: Double) = {
    val EPSILON = 0.0003d
    var (t, emc) = (_t, _emc)
    var (sn, cn, dn) = (0d, 0d, 1d)
    var (a, c, d) = (1d, 0d, 0d)
    val em = new Array[Double](14)
    val en = new Array[Double](14)
    var l = 0
    if (emc < 0) {
      d = 1 - emc
      emc /= -1 / d
      d = Math.sqrt(d)
      t *= d
    }
    
    val br = new Breaks
    br.breakable {
      for (i <- 1 to 13) {
        l = i
        em(i) = a
        emc = Math.sqrt(emc)
        en(i) = emc
        c = 0.5 * (a + emc)
        if (Math.abs(a - emc) <= EPSILON * a)
          br.break
        emc *= a
        a = c
      }
    }
    t *= c
    sn = Math.sin(t)
    cn = Math.cos(t)
    if (sn != 0) {
      a = cn / sn
      c *= a
      for (i <- l to 1 by -1) {
        var b = em(i)
        a *= c
        c *= dn
        dn = (en(i) + a) / (b + a)
        a = c / b
      }
      a = 1 / Math.sqrt(c * c + 1)
      sn = if (sn >= 0) a else -a
      cn = c * sn
    }
    if (emc < 0) {
      a = dn
      dn = cn
      cn = a
      sn /= d
    }
    (sn, cn, dn)
  }
}