

object EmspSolutionFactory {
  def create(spParam: SpParam, emParam: EmParam): Double => SpCoodinate = {
    val a = spParam.a
    val m = spParam.m
    val g = spParam.g
    val h = emParam.h
    val l = emParam.l
    val root = Cardano.solve(1.0, -h/(g*m), -a*a, a*a*h/(g*m)-l*l/(2*g*m*m))
    
    if (!root.isReal())
      return null
    
    val realRoot = (root.r1.re, root.r2.re, root.r3.re)
    
    val z      = new Z(realRoot, spParam, emParam.delta)
    val dotZ   = new DotZ(z, realRoot, spParam)
    val r      = new R(z, spParam)
    val dotPhi = new DotPhi(r, spParam, emParam)
    val dotR   = new DotR(r, z, dotZ, spParam, emParam)
    val phi    = new Phi(dotPhi, dotZ, r, emParam, emParam.phi0)
    
    t => new SpCoodinate(t, r.of(t), phi.of(t), z.of(t), dotR.of(t), dotPhi.of(t), dotZ.of(t))
  }
}

class Z(realRoot: (Double, Double, Double), spParam: SpParam, delta: Double) {
  private val (alpha, beta, gamma) = realRoot
  private val k = Math.sqrt((beta - alpha) / (gamma - alpha))
  private val c = Math.sqrt((gamma - alpha) * spParam.g / 2) / spParam.a
  
  def of(t: Double): Double = {
    if (alpha == beta)
      return alpha
    val sn = JacobiEllipticFunction.sn(c * t + delta, k)
    alpha + (beta - alpha) * sn * sn
  }
}

class DotZ(z: Z, realRoot: (Double, Double, Double), spParam: SpParam) {
  private val EPSILON = 0.001
  private val (alpha, beta, gamma) = realRoot
  private val c = spParam.g / spParam.a
  
  def of(t: Double): Double = {
    val zVal = z.of(t)
    val sgn = if (z.of(t + EPSILON) - zVal >= 0) 1 else -1
    sgn * Math.sqrt(c * (zVal - alpha) * (zVal - beta) * (zVal - gamma))
  }
}

class R(z: Z, spParam: SpParam) {
  private val a = spParam.a
  def of(t: Double): Double = {
    val zVal = z.of(t)
    Math.sqrt(a * a - zVal * zVal)
  }
}

class DotPhi(r: R, spParam: SpParam, emParam: EmParam) {
  private val l = emParam.l
  private val m = spParam.m
  
  def of(t: Double): Double = {
    if (l == 0)
      return 0
    val rVal = r.of(t)
    l / (m * rVal * rVal)
  }
}

class DotR(r: R, z: Z, dotZ: DotZ, spParam: SpParam, emParam: EmParam) {
  private val EPSILON = 0.001

  private val (h, l) = (emParam.h, emParam.l)
  private val (m, g) = (spParam.m, spParam.g)
  
  def of(t: Double): Double = {
    if (l != 0)
      return z.of(t) * dotZ.of(t) / r.of(t)
    
    val sgn = if (r.of(t + EPSILON) - r.of(t) >= 0) 1 else -1
    sgn * Math.sqrt(2*(h-m*g*z.of(t)) - dotZ.of(t) * dotZ.of(t))      
  }
}  

class Phi(dotPhi: DotPhi, dotZ: DotZ, r: R, emParam: EmParam, phi0: Double) {
  private var tau = 0d
  private var phiTau = phi0
  private var prevDotZ = dotZ.of(0)
  private var prevR = r.of(0)
  private val l = emParam.l
  def of(t: Double): Double = {
    if (t == tau)
      return phiTau
    
    if (l != 0) {
      phiTau += Integrator.solve(dotPhi.of, tau, t, 64)
      tau = t
    } else {
      if (prevDotZ * dotZ.of(t) < 0 && Math.abs((r.of(t) + prevR) * 0.5) < 0.1)
        phiTau += Math.PI
      prevDotZ = dotZ.of(t)
      prevR = r.of(t)
    }
    phiTau
  }
}

case class SpCoodinate(t: Double, r: Double, phi: Double, z: Double,
                         dotR: Double, dotPhi: Double, dotZ: Double)

case class SpParam(a: Double, m: Double, g: Double)

case class EmParam(h: Double, l: Double, delta: Double, phi0: Double)