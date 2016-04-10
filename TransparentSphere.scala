

import com.sun.j3d.utils.geometry.Sphere
import javax.media.j3d.Appearance
import javax.media.j3d.TransparencyAttributes
import com.sun.j3d.utils.geometry.Primitive

class TransparentSphere(transparency: Float, size: Float, div: Int) {
  private val ap = new Appearance
  private val ta = new TransparencyAttributes  
  ta.setTransparency(transparency)
  ta.setTransparencyMode(TransparencyAttributes.FASTEST)
  ap.setTransparencyAttributes(ta)
  
  val sphere = new Sphere(size, Primitive.GENERATE_NORMALS, div, ap)
}