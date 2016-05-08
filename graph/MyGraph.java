package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 这个类适用的是无向图，所有地方对邻接矩阵采取的操作都是同时对对称点操作。
 * 如果采用有向图，两个点之间应该增加一个错误判断
 * @author Star
 *
 * @param <T>
 */
public class MyGraph <T>{
	//	最大顶点数量
	public static int max_count=100;
	//	顶点列表
	public MyPoint<T>[] points;
	//	当前节点的位置
	private int toPoint;
	//	邻接矩阵
	private boolean[][] matrix;
	//	遍历过程中用到的暂存搜索组
	LinkedList<MyPoint<T>> list;
//	public static Stack<MyPoint> neighbers=new Stack<MyPoint>();
	public MyGraph(){
		points=new MyPoint[max_count];
		//初始化的时候指针指向第一个元素之前
		toPoint=-1;
		//初始化邻接矩阵
		matrix=new boolean[100][100];
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				matrix[i][j]=false;
			}
		}
	}
	public boolean addPoint(MyPoint<T> point){
		if(toPoint<100){
			points[++toPoint]=point;
			return true;
		}else{
			//已经超过最大长度了
			return false;
		}
	}
	/**
	 * 获取特定点的数组位置
	 * @param p	给定的点
	 * @return	该点的位置
	 * 		查询不到返回-1
	 */
	public int getPointPos(MyPoint<T> p){
		for(int i=0;i<100;i++){
			if(points[i].equals(p)){
				return i;
			}
		}
		return -1;
	}
	public boolean addEdge(MyPoint<T> a,MyPoint<T> b){
		int pa=getPointPos(a);
		int pb=getPointPos(b);
		//原位置上是什么值
		boolean exist=matrix[pa][pb]&&matrix[pb][pa];
		if(exist){
			return false;
		}else{
			matrix[pa][pb]=true;
			matrix[pb][pa]=true;
			return true;
		}
	}
	public boolean hasEdge(MyPoint<T> a,MyPoint<T> b){
		int pa=getPointPos(a);
		int pb=getPointPos(b);
		return matrix[pa][pb]&&matrix[pb][pa];
	}
	public String toString(){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<100;i++){
			for(int j=0;j<i;j++){
				if(matrix[i][j]&&matrix[j][i]){
					result.append(points[i].toString()+"——"+points[j].toString()+'\n');
				}
			}
		}
		return result.toString();
	}
	/**
	 * 获取一个位置的点的未访问的邻点的位置
	 * @param a		需要获取未访问邻点的原点
	 * @return		第一个未访问邻点的位置,如果不存在返回-1
	 */
	public int getUnvisitedPointPos(int a){
		for(int i=0;i<=toPoint;i++){
			if(matrix[a][i]&&!points[i].getFlag()){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 图的深度优先搜索算法，采用栈储存的方式来进行搜索
	 * @param root	需要进行搜索的根节点
	 * @return	一个包含了根节点的可以通过多次邻接点访问到的节点的链表。
	 */
	public LinkedList<MyPoint<T>> depthFirstSearch(MyPoint<T> root){
		list=new LinkedList<MyPoint<T>>();
		list.add(root);
		root.visitFlag();
		Stack<MyPoint<T>> stack=new Stack<MyPoint<T>>();
		stack.push(root);
		while(!stack.isEmpty()){
			int next=getUnvisitedPointPos(getPointPos(stack.peek()));
			if(next==-1){
				stack.pop();
			}else{
				points[next].visitFlag();
				list.add(points[next]);
				stack.push(points[next]);
			}
		}
		resetFlags();
		return list;
	}
	public void resetFlags(){
		for(int i=0;i<toPoint;i++){
			points[i].resetFlag();
		}
	}
	/**
	 * 图的广度优先搜索算法，采用队列储存
	 * 规则：
	 * 1、访问当前节点的第一个邻节点，标记并插入队列
	 * 2、如果1没有找到邻节点，就从队列头取出一个点当做当前节点
	 * 3、如果队列取空了就结束搜索
	 * @param root		需要搜索的根节点
	 * @return			包含头节点的一个节点的链表
	 */
	public LinkedList<MyPoint<T>> breadthFirstSearch(MyPoint<T> root){
		list=new LinkedList<MyPoint<T>>();
		LinkedList<MyPoint<T>> queue=new LinkedList<MyPoint<T>>();
		root.visitFlag();
		list.add(root);
		queue.add(root);
		int next;
		while(!queue.isEmpty()){
			MyPoint<T> head=queue.removeFirst();
			//当：当前点(head)的邻点next存在时：
			while((next=getUnvisitedPointPos(getPointPos(head)))!=-1){
				//标记该点已访问
				points[next].visitFlag();
				//添加到队列中，方便作为下次查找的顶点
				queue.add(points[next]);
				//该点是可以搜索到的，添加到list中
				list.add(points[next]);
			}//当前节点head的邻点都访问完了
		}
		resetFlags();
		return list;
	}
	public void printNeighbors(MyPoint<T> head){
		int next;
		int pos=getPointPos(head);
		while((next=getUnvisitedPointPos(pos))!=-1){
			points[next].visitFlag();
			System.out.println(points[next].toString());
		}
		resetFlags();
	}
	public int getMinDistance(MyPoint<T> a,MyPoint<T> b){
		MyPoint<T> root=a;
		int dis=0;
		list=new LinkedList<MyPoint<T>>();
		LinkedList<MyPoint<T>> queue=new LinkedList<MyPoint<T>>();
		root.visitFlag();
		list.add(root);
		queue.add(root);
		int next;
		while(!queue.isEmpty()){
			MyPoint<T> head=queue.removeFirst();
			//当：当前点(head)的邻点next存在时：
			dis++;
			System.out.println("一趟：");
			while((next=getUnvisitedPointPos(getPointPos(head)))!=-1){
				if(points[next].equals(b)){
					return dis;
				}
				System.out.println(points[next].toString());
				//标记该点已访问
				points[next].visitFlag();
				//添加到队列中，方便作为下次查找的顶点
				queue.add(points[next]);
				//该点是可以搜索到的，添加到list中
				list.add(points[next]);
			}//当前节点head的邻点都访问完了
		}
		resetFlags();
		return -1;
	}
	
	public static void main(String args[]){
		MyGraph<String> graph=new MyGraph<String>();
		MyPoint<String> wuhan=new MyPoint<String>("武汉");
		MyPoint<String> chengdu=new MyPoint<String>("成都");
		MyPoint<String> beijing=new MyPoint<String>("北京");
		MyPoint<String> shanghai=new MyPoint<String>("上海");
		graph.addPoint(wuhan);
		graph.addPoint(chengdu);
		graph.addPoint(beijing);
		graph.addPoint(shanghai);
		graph.addEdge(wuhan, chengdu);
		graph.addEdge(wuhan, beijing);
		graph.addEdge(wuhan, shanghai);
		graph.addEdge(chengdu, shanghai);
		LinkedList<MyPoint<String>> list=graph.breadthFirstSearch(beijing);
		System.out.println("北京可以达到的地点有：");
		for(MyPoint<String> point:list){
			System.out.println(point.toString());
		}
		System.out.println("北京到上海距离："+graph.getMinDistance(beijing,shanghai));
		graph.printNeighbors(wuhan);
	}
}
