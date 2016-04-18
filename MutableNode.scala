import javax.media.j3d.BranchGroup
import javax.media.j3d.Group
import javax.media.j3d.Node

class MutableNode {
  val node = new BranchGroup
  private var rootNode: BranchGroup = null
  
  initRootNode()
  node.setCapability(Group.ALLOW_CHILDREN_EXTEND)
  node.setCapability(Group.ALLOW_CHILDREN_WRITE)
  node.addChild(rootNode)
  
  def addBranchGroup(group: BranchGroup) = rootNode.addChild(group)
  
  def addNode(node: Node): Unit = {
    val b = new BranchGroup
    b.addChild(node)
    rootNode.addChild(b)
  }
  
  def clear() = {
    rootNode.detach()
    initRootNode()
    node.addChild(rootNode)
  }
  
  private def initRootNode() = {
    rootNode = new BranchGroup
    rootNode.setCapability(Group.ALLOW_CHILDREN_EXTEND)
    rootNode.setCapability(BranchGroup.ALLOW_DETACH)    
  }
}