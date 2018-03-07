package com.rcgreed.btree;

public interface TreeModel<T> {
	int childCount();
	TreeModel<T> childAt(int idx);
	int width();
}
