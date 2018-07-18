package com.nuaa.health.svm;

import libsvm.svm_node;

public class svm_make_node {
	public static svm_node[] makeNode(String input) {
		String[] args = input.split(" ");
		int length = args.length;
		
		svm_node[] nodes = new svm_node[length];
		for (int i = 1; i <= length; i++) {
			svm_node tmpNode = new svm_node();
			tmpNode.index = i;
			tmpNode.value = Double.valueOf(args[i-1]);
			nodes[i-1] = tmpNode;
		}
		return nodes;
	}
}
