package com.rcgreed.btree;

import java.util.Iterator;

public class BtreeBuilder {
	private static class NodeTreeModel<T extends Comparable<T>> implements TreeModel<T>{
		final private Node<T> n;
		public NodeTreeModel(Node<T> nn) {
			n=nn;
		}
		@Override
		public int childCount() {
			if(n.l != null || n.r != null) return 2;
			return 0;
		}

		@Override
		public TreeModel<T> childAt(int idx) {
			if(idx==0) {
				if(n.l==null) return null;
				return new NodeTreeModel<>(n.l);
			}
			return n.r==null? null:new NodeTreeModel<>(n.r);
		}

		@Override
		public int width() {
			
			return 3;
		}
		@Override
		public String toString() {
			
			return n.e.toString();
		}
		
	}
	public static <T extends Comparable<T>> Btree<T> newBtree(){
		
		return new Btree<T>(){
			private Node<T> top=null;
			@Override
			public void add(T e) {
				top=recurAdd(top,e);
				
			}

			@Override
			public TreeModel<T> getModel() {
				return new NodeTreeModel<>(top);
			}

			@Override
			public Iterator<T> iterator() {

				return new Iterator<T>() {
					@Override
					public boolean hasNext() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public T next() {
						// TODO Auto-generated method stub
						return null;
					}
				};
			}

		};
	}

	private static <T extends Comparable<T>> Node<T> recurAdd(Node<T> top,T e){
		if(top==null) return new Node<>(e);
		int cp=top.e.compareTo(e);

		if(cp>0) {
			if(top.l==null) {
				top.l=new Node<>(e);
				top.ln=1;
				return top;
			} else {
				top.l=recurAdd(top.l,e);
				top.ln=incDepth(top.l);
			}
		}else {
			if(top.r==null) {
				top.r=new Node<>(e);
				top.rn=1;
				return top;
			} else {
				top.r=recurAdd(top.r,e);
				top.rn=incDepth(top.r);
			}
		}
		int bal=top.rn-top.ln;
		if(bal < -1 || bal > 2) {
			throw new RuntimeException("Out of balance");
		}
		if(bal == -2) {
			int bal1=top.l.rn-top.l.ln;
			if(bal1<=0)
				top=rotateRight(top);
			else
				top=rotateLR(top);
		}
		if(bal == 2) {
			int bal1=top.r.rn-top.r.ln;
			if(bal1>=0) {
				top=rotateLeft(top);
			} else {
				top=rotateRL(top);
			}
		}
		return top;
		
	}
	private static <T extends Comparable<T>> Node<T> rotateRL(Node<T> top) {
		if(top.r == null) {
			throw new RuntimeException("rotation Right then Left error");
		}
		top.r=rotateRight(top.r);
		top.ln=incDepth(top.r);
		return rotateLeft(top);
	}
	private static <T extends Comparable<T>> Node<T> rotateLR(Node<T> top) {
		if(top.l == null) {
			throw new RuntimeException("rotation Left then Right error");
		}
		top.l=rotateLeft(top.l);
		top.ln=incDepth(top.l);
		return rotateRight(top);
	}
	private static <T extends Comparable<T>> Node<T> rotateLeft(Node<T> top) {
		final Node<T> b=top;
		final Node<T> c=top.r.l;
		final Node<T> d=top.r;
		
		b.r=c;
		b.rn=incDepth(b.r);
		d.l=b;
		d.ln=incDepth(d.l);
		
		return d;
	}
	private static <T extends Comparable<T>> Node<T> rotateRight(Node<T> top) {
		Node<T> d=top;
		Node<T> b=top.l;
		Node<T> c=top.l.r;

		d.l=c;
		d.ln=incDepth(d.l);
		b.r=d;
		b.rn=incDepth(b.r);
		return b;
	}
	private static <T extends Comparable<T>> int incDepth(Node<T> n) {
		if (n==null) return 0;
		
		return (n.ln > n.rn? n.ln:n.rn)+1;
	}
	private static class Node<T extends Comparable<T>>{
		public Node<T> l,r;
		public int ln,rn;
		final public T e;
		public Node(T v) {
			e=v;
			ln=0;
			rn=0;
		}
	}
}
