package week10lab;

/**
 *
 * @author margaretconnor
 */
public class CharTree {

    public static void main(String[] args) {
        CharTree tree = new CharTree();
        tree.add('c');
        tree.add('a');
        tree.add('t');
        System.out.println(tree.toString());//inorder prints a c t

        System.out.println(tree.countNodes());
        System.out.println(tree.getDepth());

        System.out.println(tree.contains('c'));
        try{
        System.out.println(tree.getParent('a'));  //can be tricky
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private CharNode trunk;

    public class CharNode {

        private Character charData;
        private CharNode lNode; //left node
        private CharNode rNode; //right node

        public CharNode(char charData) {
            this.charData = charData;
            lNode = null;
            rNode = null;
        }
    }

    public CharTree() {
        this.trunk = null;
    }

    public void add(char charAdd) {
        if (this.trunk == null) {    //first item added
            this.trunk = new CharNode(charAdd);
        } else {                //build to existing tree
            CharNode currentNode = this.trunk;
            Character add = charAdd;
            boolean endOfTree = false;

            while (!endOfTree) {
                if (currentNode.charData.compareTo(charAdd) < 0) {
                    if (currentNode.lNode == null) {
                        endOfTree = true;
                        currentNode.lNode = new CharNode(charAdd);
                    }
                    currentNode = currentNode.lNode;
                } else {
                    if (currentNode.rNode == null) {
                        endOfTree = true;
                        currentNode.rNode = new CharNode(charAdd);
                    }
                    currentNode = currentNode.rNode;
                }
            }
        }
    }

    public int countNodes() {
        return countNodes(this.trunk);
    }

    private int countNodes(CharNode current) {
        int subNodes = 0;
        if (current.lNode == null && current.rNode == null) {
            return 1;
        } else {
            if (current.lNode != null) {
                subNodes += countNodes(current.lNode);
            }
            if (current.rNode != null) {
                subNodes += countNodes(current.rNode);
            }
        }
        return 1 + subNodes;

    }

    public int getDepth() {
        return getDepth(this.trunk);
    }

    private int getDepth(CharNode tree) {
        if (tree == null) {
            return 0;
        } else {
            int lDepth = getDepth(tree.lNode);
            int rDepth = getDepth(tree.rNode);

            if (lDepth > rDepth) {
                return 1 + lDepth;
            } else {
                return 1 + rDepth;
            }
        }
    }

    public String toString() {
        return toString(this.trunk);
    }

    private String toString(CharNode current) {
        String toString = "";

        if (current.lNode == null && current.rNode == null) {
            return current.charData.toString() + " ";
        } else {
            if (current.rNode != null) {
                toString += toString(current.rNode);
            }

            //adds current data before right node
            toString += current.charData + " ";

            if (current.lNode != null) {
                toString += toString(current.lNode);
            }
        }
        return toString;
    }

    public boolean contains(char find) {
        if (this.trunk == null) {    //first item added
            return false;
        } else {                //build to existing tree
            CharNode currentNode = this.trunk;

            while (currentNode != null) {
                if (currentNode.charData.compareTo(find) == 0) {
                    return true;
                } else if (currentNode.charData.compareTo(find) < 0) {
                    if (currentNode.lNode == null) {
                        return false;
                    }
                    currentNode = currentNode.lNode;
                } else {
                    if (currentNode.rNode == null) {
                        return false;
                    }
                    currentNode = currentNode.rNode;
                }
            }
            return false;
        }
    }

    public char getParent(char findParent) {
        if (this.trunk == null) {    //first item added
            throw new RuntimeException("No parent exists");
        } else {                //build to existing tree
            CharNode previousNode = null;
            CharNode currentNode = trunk;

            while (currentNode != null) {
                if (currentNode.charData.compareTo(findParent) == 0) {
                    currentNode = null;
                } else if (currentNode.charData.compareTo(findParent) < 0) {
                    previousNode = currentNode;
                    currentNode = currentNode.lNode;
                } else {
                    previousNode = currentNode;
                    currentNode = currentNode.rNode;
                }
            }
            if (previousNode != null) {
                return previousNode.charData;
            } else {
                throw new RuntimeException("No parent exists");
            }
        }
    }
}
