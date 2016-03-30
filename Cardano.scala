
/*
 * solution of
 * a x^3 + b x^2 + c x + d = 0
 */
object Cardano {
  
  def solve(_a: Double, _b: Double, _c: Double, _d: Double): CubicEquationRoot = {
    val b = _b / (3 * _a)
    val c = _c / _a
    val d = _d / _a
    val p = b * b - c / 3
    val q = (b * (c - 2 * b * b) - d) / 2
    val a = q * q - p * p * p
    
    if (a == 0) {
      val r = cuberoot(q)
      val x1 = 2 * r - b
      val x2 = -r - b
      new CubicEquationRoot(new Complex(x1, 0), new Complex(x2, 0))
    } else if (a > 0) {
      val a3 = if (q > 0) cuberoot(q + Math.sqrt(a)) else cuberoot(q - Math.sqrt(a))
      val b3 = p / a3
      val x1 = a3 + b3 - b
      val x2 = -0.5 * (a3 + b3) - b
      val x3 = Math.abs(a3 - b3) * Math.sqrt(3.0) / 2
      CubicEquationRoot(Complex(x1, 0), Complex(x2, x3), Complex(x2, -x3))
    } else {
      val r = Math.sqrt(p)
      val t = Math.acos(q / (p * r))
      val x1 = 2 * r * Math.cos(t                 / 3) - b
      val x2 = 2 * r * Math.cos((t + 2 * Math.PI) / 3) - b
      val x3 = 2 * r * Math.cos((t + 4 * Math.PI) / 3) - b
      CubicEquationRoot(Complex(x1, 0), Complex(x2, 0), Complex(x3, 0))
    }
  }
  
  private def cuberoot(_x: Double): Double = {
    var x = _x
    var pos = true
    if (x == 0)
      return 0
    if (x > 0) {
      pos = false
      x = -x
    }
    var s = if (x > 1) x else 1
    var prev: Double = 0
    do {
      prev = s
      s = (x / (s * s) + 2 * s) / 3
    } while (s < prev)
    if (pos) prev else -prev
  }
}

case class Complex(re: Double, im: Double);

case class CubicEquationRoot(r1: Complex, r2: Complex, r3: Complex) {
  def this(r1: Complex, r2: Complex) = this(r1, r2, r2)
}