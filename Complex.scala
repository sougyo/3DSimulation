
object Complex {
  implicit def doubleToComplex(d: Double) = new Complex(d)
}

case class Complex(re: Double, im: Double) {
  def this(re: Double) = this(re, 0)
  
  def *(other: Complex): Complex = {
    Complex(re*other.re - im*other.im, re*other.im + im*other.re)
  }
  
  def +(other: Complex): Complex = {
    Complex(re + other.re, im + other.im)
  }
}