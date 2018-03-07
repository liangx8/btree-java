package com.rcgreed.btree;


public class TestFunc {
	public static class MyInt implements Comparable<MyInt>{
		final private int i;
		public MyInt(int v) {
			i=v;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("%03d", i);
		}
		@Override
		public int compareTo(MyInt o) {
			
			return i-o.i;
		}
	}
	public static void main(String[] args) {
		Btree<MyInt> bt=BtreeBuilder.newBtree();
		for(int i=0;i<130;i++)
			bt.add(new MyInt(i));
		PrintTree pt=new PrintTree(System.out);
		pt.print(bt.getModel());
	}


}
