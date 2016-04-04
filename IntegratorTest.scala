import junit.framework.TestCase
import junit.framework.Assert.assertTrue

class IntegratorTest extends TestCase {
  def testX() {
    for (d <- 0d to 100d by 0.1) {
      val a = Integrator.solve(x => x, 0, d, 1000)
      assertTrue(Math.abs(0.5 * Math.pow(d, 2) - a) < 0.000001)
    }
  }
  
  def testX2() {
    for (d <- 0d to 100d by 0.1) {
      val a = Integrator.solve(x => x * x, 0, d, 1000)
      assertTrue(Math.abs(Math.pow(d, 3) / 3 - a) < 0.000001)
      println(a)
    }
  }
}