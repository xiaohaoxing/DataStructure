package tree;

import java.util.LinkedList;
import java.util.Scanner;

public class BinTree<T> {
	public BinNode<T> root;
	private static  LinkedList<BinNode> result;
	public BinTree(){
		root=null;
	}
	public BinTree(BinNode<T> root){
		this.root=root;
	}
	public BinTree(T value){
		root=new BinNode<T>(value);
	}
	public void preOrderReverse(){
		result=new LinkedList<BinNode>();
		preOrder(root);
	}
	public void preOrder(BinNode<T> root){
		if(root!=null){
			BinNode<T> temp=root;
			result.add(temp);
			preOrder(temp.leftchild);
			preOrder(temp.rightchild);
		}
	}
	
	
	public void postOrderReverse(){
		result=new LinkedList<BinNode>();
		postOrder(root);
	}
	public void postOrder(BinNode<T> root){
		if(root!=null){
			BinNode<T> temp=root;
			postOrder(temp.leftchild);
			postOrder(temp.rightchild);
			result.add(temp);
		}
	}
	

	public void midOrderReverse(){
		result=new LinkedList<BinNode>();
		midOrder(root);
	}
	public void midOrder(BinNode<T> root){
		if(root!=null){
			BinNode<T> temp=root;
			midOrder(temp.leftchild);
			result.add(temp);
			midOrder(temp.rightchild);
		}
	}
	
	public void layerOrderReverse(){
		result=new LinkedList<BinNode>();
		BinNode[] layer={root};
		while(true){
			if(layer==null){
				break;
			}
			for(BinNode<T> n:layer){
				if(n!=null){
					result.add(n);
				}
			}
			layer=getChilden(layer);
		}
	}
	
	public int getLayerNum(){
		BinNode[] lay={root};
		int num=0;
		while(true){
			lay=getChilden(lay);
			num++;
			if(lay==null)
				break;
		}
		return num;
	}
	public int getNodeNum(){
		preOrderReverse();
		return result.size();
	}
	public BinNode<T>[] getChilden(BinNode<T>[] upper){
		BinNode<T>[] result=new BinNode[upper.length*2];
		int flag=0;
		for(BinNode<T> p:upper){
			if(p.leftchild!=null){
				result[flag]=p.leftchild;
				flag++;
			}
			if(p.rightchild!=null){
				result[flag]=p.rightchild;
				flag++;
			}
		}
		if(flag==0){	//�Ѿ������һ����
			return null;
		}
		return result;
	}
	public static String getList(){
		StringBuilder sb=new StringBuilder();
		for(BinNode n:result){
			sb.append(n.toString());
		}
		return sb.toString();
	}
	public String toString(){
		// TODO ���ƶ�����
		return null;
	}
	
	public static void showMenu(){
		System.out.println("----------ѡ��----------");
		System.out.println("������������ѡ��˵��");
		System.out.println("1.ǰ�����");
		System.out.println("2.�������");
		System.out.println("3.�������");
		System.out.println("4.��α���");
		System.out.println("5�������");
		System.out.println("6.��ڵ���");
		System.out.println("7.�˳�");
	}
	public static void main(String args[]){
		/*
		 * ��ȡ����
		 * 				1
		 * 			    �L	     �K
		 * 			2		3
		 * 		  �L	��		��  �K
		 * 		4	5		6	7
		 */
		BinNode<Integer> four=new BinNode<Integer>(4);
		BinNode<Integer> five=new BinNode<Integer>(5);
		BinNode<Integer> six=new BinNode<Integer>(6);
		BinNode<Integer> seven=new BinNode<Integer>(7);
		BinNode<Integer> two=new BinNode<Integer>(2,four,five);
		BinNode<Integer> three=new BinNode<Integer>(3,six,seven);
		BinNode<Integer> one=new BinNode<Integer>(1,two,three);
		BinTree<Integer> tree=new BinTree<Integer>(one);
//		tree.preOrderReverse();
//		System.out.println("���������"+getList());
//		tree.midOrderReverse();
//		System.out.println("���������"+getList());
//		tree.postOrderReverse();
//		System.out.println("���������"+getList());
//		tree.layerOrderReverse();
//		System.out.println("��α�����"+getList());
//		System.out.println("������"+tree.getLayerNum());
//		System.out.println("�ڵ�����"+tree.getNodeNum());
		Scanner in=new Scanner(System.in);
		while(true){
			showMenu();
			try{
				int num=in.nextInt();
				switch(num){
				case 1:
					tree.preOrderReverse();
					System.out.println("���������"+getList());
					break;
				case 2:
					System.out.println("���������"+getList());
					tree.postOrderReverse();
					break;
				case 3:
					System.out.println("���������"+getList());
					tree.layerOrderReverse();
					break;
				case 4:
					tree.layerOrderReverse();
					System.out.println("��α�����"+getList());
					break;
				case 5:
					System.out.println("������"+tree.getLayerNum());
					break;
				case 6:
					System.out.println("�ڵ�����"+tree.getNodeNum());
					break;
				case 7:
					System.out.println("��лʹ�ã��ټ���");
					in.close();
					Thread.sleep(1000);
					return;
				default:
					throw new Exception();
				}
			}catch(Exception e){
				System.out.println("����������������룡");
				in.next();
			}
		}
		
	}
}
