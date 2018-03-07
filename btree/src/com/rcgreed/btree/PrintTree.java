package com.rcgreed.btree;

import java.io.OutputStream;
import java.io.PrintStream;

public class PrintTree {
	final private PrintStream pt;

	public PrintTree(OutputStream out) {
		pt = new PrintStream(out);
	}

	public <T> void print(TreeModel<T> tm) {
		printWithPrefix(tm, "");
	}

	private <T> void printWithPrefix(TreeModel<T> tm, final String prefix) {
		StringBuilder sb1;
		if (tm == null) {
			pt.println('=');
			return;
		}
		pt.print(tm);
		sb1 = new StringBuilder(prefix);
		int cnt = tm.childCount();
		if (cnt == 0) {
			pt.println('$');
			return;
		} else {
			pt.print("-+-");
		}
		for (int i = 0; i < tm.width() + 1; i++) {
			sb1.append(' ');
		}
		StringBuilder ssb = new StringBuilder(sb1);
		StringBuilder sb2 = new StringBuilder(sb1);

		sb1.append("| ");
		sb2.append("  ");
		ssb.append("+-");

		for (int i = 0; i < cnt; i++) {
			if (i == cnt - 1) {
				printWithPrefix(tm.childAt(i), sb2.toString());
			} else {
				printWithPrefix(tm.childAt(i), sb1.toString());
				pt.print(ssb);
			}

		}

	}
}
