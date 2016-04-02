import org.junit.Test;


import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import junit.framework.Assert.fail

class CardanoTest extends TestCase {
  def testCalc() {
    val rng = scala.util.Random
    
    for (i <- 1 to 10000) {
      val p = (nextRnd(rng), nextRnd(rng), nextRnd(rng), nextRnd(rng))
      testAbcd(p)
    }
    testAbcd((1d, 3d, 3d, 1d))
    testAbcd((1d, -3d, 3d, -1d))
  }
  
  def nextRnd(rng: scala.util.Random) = {
    (rng.nextDouble() - 0.5) * 100
  }
  
  def testAbcd(k: (Double, Double, Double, Double)) = {
    println("test for" + k)
    val sol = Cardano.solve(k._1, k._2, k._3, k._4)
    
    testSol(k, sol.r1)
    testSol(k, sol.r2)
    testSol(k, sol.r3)
    println("")
  }
  
  def testSol(k: (Double, Double, Double, Double), r: Complex) {
    println(r)
    val c = k._1*r*r*r + k._2*r*r + k._3*r + k._4
    assertTrue(c.re < 0.0000001)
    assertTrue(c.im < 0.0000001)
    printComplex(c)
  }
  def printComplex(c: Complex) {
    println(f"${c.re}%,.6f + i ${c.im}%,.6f")
  }
}