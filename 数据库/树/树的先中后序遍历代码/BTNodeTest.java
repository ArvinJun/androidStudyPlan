package com.yyydjk.miaojiedemo;

/**
 * Created by arvin on 2022/11/12.
 *       a
 *     b  c
 *       d  e
 *    创建这样一个输
 */
public class BTNodeTest {

    class  BTNode{
        String data;
        BTNode mLeftNode;
        BTNode mRightNode;
    }




    public static  void  main(String args []){
        BTNodeTest btNodeTest = new BTNodeTest();
        BTNode nodeTree = btNodeTest.creatTree();
        btNodeTest.lastTreeTraverse(nodeTree);

    }

   /*
   * 先序遍历二叉树
   * 先访问根结点
   * 然后访问左子树
   * 最后访问右子树
   *根结点在第一位
   * */
   void  preTreeTraverse(BTNode btNode){

       if(btNode != null){
           //根直接输出它的值
           System.out.print(btNode.data+"--->");
          // 然后访问左节点，但是左节点可能还会有子节点，所以递归区遍历
           //如果是空的左节点就不执行
           if(btNode.mLeftNode != null){
               preTreeTraverse(btNode.mLeftNode);
           }
           if(btNode.mRightNode != null){
               //最后访问右节点，这个是同样的道理，需要递归
               preTreeTraverse(btNode.mRightNode);
           }

       }
   }

    /*
    * 同样的中序遍历
    * 首先访问左节点
    * 然后访问根结点
    * 最后访问右节点
    * 中序遍历根结点都在中间
    * */
    void  midTreeTraverse(BTNode btNode){

        if(btNode != null){

            // 首先访问左节点，但是左节点可能还会有子节点，所以递归区遍历
            //如果是空的左节点就不执行
            if(btNode.mLeftNode != null){
                midTreeTraverse(btNode.mLeftNode);
            }
            //根直接输出它的值
            System.out.print(btNode.data+"--->");
            if(btNode.mRightNode != null){
                //最后访问右节点，这个是同样的道理，需要递归
                midTreeTraverse(btNode.mRightNode);
            }

        }
    }


    /*
   * 同样的后序遍历
   * 首先访问左节点
   * 然后访问右节点
   * 最后访问根结点
   * 后序遍历根结点都在最后
   * */
    void  lastTreeTraverse(BTNode btNode){

        if(btNode != null){

            // 首先访问左节点，但是左节点可能还会有子节点，所以递归区遍历
            //如果是空的左节点就不执行
            if(btNode.mLeftNode != null){
                lastTreeTraverse(btNode.mLeftNode);
            }

            if(btNode.mRightNode != null){
                //然后访问右节点，这个是同样的道理，需要递归
                lastTreeTraverse(btNode.mRightNode);
            }
            //根直接输出它的值
            System.out.print(btNode.data+"--->");

        }
    }
    BTNode creatTree(){
        BTNode A = new BTNode();
        BTNode B = new BTNode();
        BTNode C = new BTNode();
        BTNode D = new BTNode();
        BTNode E = new BTNode();

        A.data = "A";
        B.data = "B";
        C.data = "C";
        D.data = "D";
        E.data = "E";
        A.mLeftNode = B;
        A.mRightNode =C;
        B.mLeftNode =B.mRightNode =null;
        C.mLeftNode=D;
        C.mRightNode =E;
        D.mRightNode =D.mLeftNode =null;
        E.mRightNode =E.mLeftNode =null;
     return A;
    }



}
