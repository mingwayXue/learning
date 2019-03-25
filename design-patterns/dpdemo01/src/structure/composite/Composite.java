package structure.composite;

import java.util.Enumeration;
import java.util.Vector;

/** 设计模式：组合模式（部分-整体模式）
 *  一般用于处理类似树形结构的问题（将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等）
 * Created by mingway on Date:2018-12-10 14:40.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class Composite {
	public static void main(String[] args) {
		Tree treeA = new Tree("A");
		TreeNode treeNodeB = new TreeNode("B");
		TreeNode treeNodeC = new TreeNode("C");

		treeNodeB.add(treeNodeC);
		treeA.root.add(treeNodeB);
		System.out.println("test tree debug!");
	}
}

class TreeNode {

	private String name;
	private TreeNode parent;
	private Vector<TreeNode> children = new Vector<TreeNode>();	//组合模式，将对象组合成一个列表对象

	public TreeNode(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	//添加孩子节点
	public void add(TreeNode node){
		children.add(node);
	}

	//删除孩子节点
	public void remove(TreeNode node){
		children.remove(node);
	}

	//取得孩子节点
	public Enumeration<TreeNode> getChildren(){
		return children.elements();
	}
}

class Tree {
	TreeNode root = null;

	public Tree(String name) {
		root = new TreeNode(name);
	}
}

