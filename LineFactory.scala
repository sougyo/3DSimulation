import javax.vecmath.Point3d
import javax.vecmath.Color3f
import javax.media.j3d.Shape3D
import javax.media.j3d.LineStripArray
import javax.media.j3d.GeometryArray

object LineFactory {
  def makeLine(arr: Array[(Point3d, Color3f)]): Shape3D = {
    val lsa = new LineStripArray(arr.length,
        GeometryArray.COORDINATES | GeometryArray.COLOR_3,
        Array(arr.length))
    lsa.setCoordinates(0, arr.map(x => x._1))
    lsa.setColors(0, arr.map(x => x._2))
    
    return new Shape3D(lsa)
  }
}