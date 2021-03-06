package graph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 有向图的数据结构
 * @author Star
 *
 */
public class MyDigraph<T> {
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
	public MyDigraph(){
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
		for(int i=0;i<=toPoint;i++){
			if(points[i].equals(p)){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 和无向图有区别
	 * {@link MyGraph#addEdge(MyPoint, MyPoint)}
	 * 有向图中边包含了起点和终点，每次只更新矩阵一个点
	 * @param from		边的起始点
	 * @param to		边的终止点
	 * @return			更新结果
	 */
	public boolean addEdge(MyPoint<T> from,MyPoint<T> to){
		int pa=getPointPos(from);
		int pb=getPointPos(to);
		if(pa==-1||pb==-1){
			System.out.println("error at addEdge method!");
			System.out.println(from.toString()+"->"+to.toString());
			return false;
		}
		//原位置上是什么值
		boolean exist=matrix[pa][pb];
		if(exist){
			return false;
		}else{
			matrix[pa][pb]=true;
			return true;
		}
	}
	public boolean hasEdge(MyPoint<T> a,MyPoint<T> b){
		int pa=getPointPos(a);
		int pb=getPointPos(b);
		return matrix[pa][pb];
	}
	public String toString(){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<toPoint;i++){
			for(int j=0;j<toPoint;j++){
				if(matrix[i][j]){
					result.append(points[i].toString()+"—>"+points[j].toString()+'\n');
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
	/**
	 * 还原所有的点的flag，每个需要标记各点的方法返回之前都要调用此方法。
	 */
	protected void resetFlags(){
		for(int i=0;i<=toPoint;i++){
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
	/**
	 * 一个用于测试的类：输出一个确定点的邻点
	 * @param head	要求邻点的原点
	 */
	public void printNeighbors(MyPoint<T> head){
		int next;
		while((next=getUnvisitedPointPos(getPointPos(head)))!=-1){
			points[next].visitFlag();
			System.out.println(points[next].toString());
		}
		resetFlags();
	}
	/**
	 * 通过广度优先搜索方式获取两节点之间的最小距离<br/>
	 * <B>可能存在点不存在于图中的异常</B>throws new NotFoundError
	 * @param a
	 * @param b
	 * @return		当两个点无法连通时返回-1
	 */
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
			while((next=getUnvisitedPointPos(getPointPos(head)))!=-1){
				if(points[next].equals(b)){
					resetFlags();
					return dis;
				}
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
	
	public MyGraph<T> getMinimumSpanningTree(MyPoint<T> root){
		MyGraph<T> mst=new MyGraph<T>();
		
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
				mst.points=this.points;
				//点必须要相同，addEdge()是针对graph自身的位置的。
				System.out.println("add point"+points[next].toString());
				mst.addEdge(points[next],stack.peek());
				System.out.println("add edge"+stack.peek()+"-"+points[next]);
				points[next].visitFlag();
				list.add(points[next]);
				stack.push(points[next]);
			}
		}
		resetFlags();
		return mst;
	}
	
	public int getNoNextPointPos(){
		for(int i=0;i<=toPoint;i++){
			boolean hasNext=false;
			for(int j=0;j<=toPoint;j++){
				if(matrix[i][j]){
					hasNext=true;
				}
			}
			if(!hasNext){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 图的拓扑排序
	 * @return	拓扑结果
	 */
	public LinkedList<MyPoint<T>> topo(){
		 int count=toPoint;
		 while(count>0){
			 int current=getNoNextPointPos();
			 if(current==-1){
				 //这整个图找不到没有后继节点的点，表示这个图一定存在环
				 System.out.println("the graph has circle!");
				 return null;
			 }
		 }
		 // TODO
		 return null;
	}
	
	public static void main(String[] args) {
		MyDigraph<String> graph=new MyDigraph<String>();
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
		graph.addEdge(chengdu, beijing);
		graph.addEdge(beijing, shanghai);
		graph.printNeighbors(shanghai);
	}

}
