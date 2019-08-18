package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;
import java.util.List;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      return blackHeightLeft((RBNode<T>) this.root);
   }

   private int blackHeightLeft(RBNode<T> node) {
      if (node.isEmpty()) {
         return 0;
      } else if (node.getColour().equals(Colour.RED)) {
         return this.blackHeightLeft((RBNode<T>) node.getLeft());
      } else {
         return 1 + this.blackHeightLeft((RBNode<T>) node.getLeft());
      }
   }

   private int blackHeightRight(RBNode<T> node) {
      if (node.isEmpty()) {
         return 0;
      } else if (node.getColour().equals(Colour.RED)) {
         return this.blackHeightRight((RBNode<T>) node.getRight());
      } else {
         return 1 + this.blackHeightRight((RBNode<T>) node.getRight());
      }
   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return this.verifyChildrenOfRedNodesAux((RBNode<T>) this.root);
   }

   private boolean verifyChildrenOfRedNodesAux(RBNode<T> node) {
      if (!node.isEmpty()) {
         if (node.getColour().equals(Colour.RED)) {
            if (((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED)
                  || ((RBNode<T>) node.getRight()).getColour().equals(Colour.RED)) {
               return false;
            }
         } else {
            return this.verifyChildrenOfRedNodesAux((RBNode<T>) node.getLeft())
                  && this.verifyChildrenOfRedNodesAux((RBNode<T>) node.getRight());
         }
      }
      return true;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      return this.verifyBlackHeightAux((RBNode<T>) this.root);
   }

   private boolean verifyBlackHeightAux(RBNode<T> node) {
      if (node.isEmpty()) {
         return true;
      } else if (this.blackHeightLeft(node) != this.blackHeightRight(node)) {
         return false;
      } else {
         return this.verifyBlackHeightAux((RBNode<T>) node.getLeft())
               && this.verifyBlackHeightAux((RBNode<T>) node.getRight());
      }
   }

   @Override
   public void insert(T value) {
      this.insertAux((RBNode<T>) this.root, value);
   }

   public void insertAux(RBNode<T> node, T element) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());

         node.getLeft().setParent(node);
         node.getRight().setParent(node);

         node.setColour(Colour.RED);
         this.fixUpCase1(node);
      } else {
         if (element.compareTo(node.getData()) > 0)
            insertAux((RBNode<T>) node.getRight(), element);
         else if (element.compareTo(node.getData()) < 0)
            insertAux((RBNode<T>) node.getLeft(), element);
      }
   }

   @Override
   public RBNode<T>[] rbPreOrder() {
      RBNode<T>[] array = new RBNode[this.size()];
      List<RBNode<T>> list = new ArrayList<>();
      this.rbPreOrderAux((RBNode<T>) this.root, list);
      for (int i = 0; i < list.size(); i++) {
         array[i] = list.get(i);
      }
      return array;
   }

   private void rbPreOrderAux(RBNode<T> node, List<RBNode<T>> array) {
      if (!node.isEmpty()) {
         array.add(node);
         this.rbPreOrderAux((RBNode<T>) node.getLeft(), array);
         this.rbPreOrderAux((RBNode<T>) node.getRight(), array);
      }
   }

   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
      if (node.equals(this.root)) {
         node.setColour(Colour.BLACK);
      } else {
         this.fixUpCase2(node);
      }
   }

   protected void fixUpCase2(RBNode<T> node) {
      if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)) {
         this.fixUpCase3(node);
      }
   }

   protected void fixUpCase3(RBNode<T> node) {
      RBNode<T> uncle = this.getUncle(node);
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandFather = (RBNode<T>) parent.getParent();
      if (uncle.getColour().equals(Colour.RED)) {
         parent.setColour(Colour.BLACK);
         uncle.setColour(Colour.BLACK);
         grandFather.setColour(Colour.RED);
         this.fixUpCase1(grandFather);
      } else {
         this.fixUpCase4(node);
      }
   }

   protected void fixUpCase4(RBNode<T> node) {
      RBNode<T> next = node;
      RBNode<T> parent = (RBNode<T>) next.getParent();
      if (this.isRightChild(node) && !this.isRightChild(parent)) {
         Util.leftRotation(parent);
         next = (RBNode<T>) next.getLeft();
      } else if (!this.isRightChild(node) && this.isRightChild(parent)) {
         Util.rightRotation(parent);
         next = (RBNode<T>) next.getRight();
      }
      this.fixUpCase5(next);
   }

   protected void fixUpCase5(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> grandFather = (RBNode<T>) parent.getParent();

      parent.setColour(Colour.BLACK);
      grandFather.setColour(Colour.RED);
      if (isRightChild(node)) {
         Util.leftRotation(grandFather);
      } else {
         Util.rightRotation(grandFather);
      }
   }

   private RBNode<T> getUncle(RBNode<T> node) {
      RBNode<T> parent = (RBNode<T>) node.getParent();
      RBNode<T> uncle;
      if (isRightChild(parent)) {
         uncle = (RBNode<T>) parent.getParent().getLeft();
      } else {
         uncle = (RBNode<T>) parent.getParent().getRight();
      }
      return uncle;
   }

   private boolean isRightChild(RBNode<T> node) {
      return node.getParent().getRight().equals(node);
   }
}
