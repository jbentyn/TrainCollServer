package com.bentyn.traincoll.commons.algorithms;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class FixedSizeQueue<E> extends ConcurrentLinkedDeque<E>{


	private static final long serialVersionUID = -926631079867031064L;

	private int maxSize;

	public FixedSizeQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
	}
	//TODO I know I don't override all add, poll etc. methods. Doesn't matter for now
	//TODO check for concurrency issues
	@Override
	public boolean add(E e) {
		boolean result = super.add(e);
		if (size() > maxSize){
			removeFirst();
		}
		return result;
	}
	
	
	
}
