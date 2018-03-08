package com.rcgreed.btree;

import java.util.Random;

public class SampleTreeModel implements TreeModel<Integer> {
	final private SampleTreeModel []children;
	final private int value;
	
	final private static Random r=new Random();
	private SampleTreeModel(int depth,int val) {
		value=depth*100+val;
		if(depth>0) {
			int cnt=r.nextInt(5)+1;

			children=new SampleTreeModel[cnt];

			for(int i=0;i<cnt;i++) {
				int setnull= r.nextInt() %12;
				if(setnull < 10) {
					children[i]=new SampleTreeModel(depth-1,i);
				}
			}
			return;
		}

			children=null;
		
	}
	@Override
	public int childCount() {
		return children==null?0:children.length;
	}

	@Override
	public TreeModel<Integer> childAt(int idx) {
		return children[idx];
	}
	public static TreeModel<Integer> newTreeModel(int depth){
				return new SampleTreeModel(depth,0);
	}
	@Override
	public String toString() {
		return String.format("%03d", value);
	}
	@Override
	public int width() {
		return 3;
	}
	@Override
	public void setStringConvert(StringConvert<Integer> convert) {
		throw new UnsupportedOperationException("converter is unnesscessary");
		
	}
}
