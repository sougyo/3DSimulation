

object Integrator {
  def solve(f: Double => Double, a: Double, b: Double, div: Int): Double = {
    var simpson = 0d
    var h = b - a
    var trapezoid = h * (f(a) + f(b)) / 2
    var n = 1
    
    while (n <= div) {
      var midpoint = 0d
      for (i <- 1 to n)
        midpoint += f(a + h * (i - 0.5))
      midpoint *= h
      simpson = (trapezoid + 2 * midpoint) / 3
      h /= 2
      trapezoid = (trapezoid + midpoint) / 2
      n *= 2
    }
    simpson
  }
}