import javax.media.j3d.BranchGroup
import javax.media.j3d.Canvas3D
import javax.media.j3d.Node

abstract class Viewer3d {
  def addNode(node: Node)
  def addBranchGroup(group: BranchGroup)
  def clear()
  def getComponent(): Canvas3D
}