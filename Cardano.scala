
/*
 * solution of
 * a x^3 + b x^2 + c x + d = 0
 */
object Cardano {
  
  def solve(_a: Double, _b: Double, _c: Double, _d: Double): CubicEquationRoot = {
    val (b, c, d) = (_b / (3 * _a), _c / _a, _d / _a)
    val (p, q) = (b * b - c / 3, (b * (c - 2 * b * b) - d) / 2)
    val a = q * q - p * p * p
    
    if (a == 0) {
      val r = cuberoot(q)
      val (x1, x2) = (2 * r - b, -r - b)
      new CubicEquationRoot(new Complex(x1, 0), new Complex(x2, 0))
    } else if (a > 0) {
      val sign = if (q > 0) 1 else -1
      val a3 = cuberoot(q + sign * Math.sqrt(a))
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
    if (_x == 0)
      return 0
    var (sign, x) = if (_x > 0) (1, _x) else (-1, -_x)
    var (s, prev) = (if (x > 1) x else 1, 0d)
    do {
      prev = s
      s = (x / (s * s) + 2 * s) / 3
    } while (s < prev)
    sign * prev
  }
}

case class CubicEquationRoot(r1: Complex, r2: Complex, r3: Complex) {
  def this(r1: Complex, r2: Complex) = this(r1, r2, r2)
  
  def isReal() = r1.im == 0 && r2.im == 0 && r3.im == 0

}