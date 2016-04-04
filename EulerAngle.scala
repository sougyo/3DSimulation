case class EulerAngle(phi: Double, theta: Double, psi: Double) {
  def +(other: EulerAngle): EulerAngle = {
    EulerAngle(normalize(phi   + other.phi  ),
               normalize(theta + other.theta),
               normalize(psi   + other.psi  ))
  }
  
  private def normalize(r: Double): Double = {
    mod(r, 2 * Math.PI)
  }
  
  private def mod(_v: Double, m: Double): Double = {
    var v = _v
    if (m < 0)
      return v
    if (v > m)
      while (v >= m) v -= m
    if (v < m)
      while (v < 0) v += m
    v
  }
}