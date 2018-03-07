package com.rcgreed.btree;

import java.util.Iterator;

public interface Btree<T extends Comparable<T>> {
	public interface Callback<C>{
		void callback(int idx,C e);
	}
	void add(T e);
	TreeModel<T> getModel();
	Iterator<T> iterator();
}
