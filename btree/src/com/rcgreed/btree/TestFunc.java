package com.rcgreed.btree;


public class TestFunc {
	public static void main(String[] args) {
		Btree<Integer> bt=BtreeBuilder.newBtree();
		for(int i=0;i<130;i++)
			bt.add(i);
		PrintTree pt=new PrintTree(System.out);
		TreeModel<Integer> m=bt.getModel();
		m.setStringConvert(new TreeModel.StringConvert<Integer>() {
			
			@Override
			public String asString(Integer src) {
				
				return String.format("%03d", src);
			}
		});
		pt.print(m);
	}


}
