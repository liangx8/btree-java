package com.rcgreed.btree;

public interface TreeModel<T> {
	public interface StringConvert<T>{
		String asString(T src);
	}
	int childCount();
	TreeModel<T> childAt(int idx);
	int width();
	void setStringConvert(StringConvert<T> convert);
}
