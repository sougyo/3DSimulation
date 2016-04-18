import javax.vecmath.Point3d
import javax.vecmath.Color3f
import scala.collection.mutable.ArrayBuffer

class RefreshLineManager(refreshStep: Int) {
  val node = new MutableNode
  private val tmpNode = new MutableNode
  private val list = new ArrayBuffer[(Point3d, Color3f)]
  
  node.addBranchGroup(tmpNode.node)
  
  def add(point: (Point3d, Color3f)): Unit = {
    list += point
    if (list.length < 2)
      return
    val prevPoint = list(list.length - 2)
    val isTooLong = prevPoint._1.distance(point._1) > 0.3
    if (list.length > refreshStep || isTooLong)
      refresh(isTooLong)
    else
      tmpNode.addNode(LineFactory.makeLine(Array(prevPoint, point)))
  }
  
  private def refresh(isTooLong: Boolean): Unit = {
    val lastPoint = list.last
    
    if (isTooLong)
      list.remove(list.length - 1)
    if (list.length >= 2)
      node.addNode(LineFactory.makeLine(list.toArray))
    tmpNode.clear()
    list.clear()
    list += lastPoint
  }
}