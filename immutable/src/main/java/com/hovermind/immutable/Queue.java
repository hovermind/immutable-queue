package com.hovermind.immutable;

/**
 * Defines the interface of a jvm Queue ADT.
 * 
 * @author HASSAN MD TAREQ
 * @see <a href="https://hovermind.com">hovermind.com</a>
 *
 * @param <T> element type of the Queue
 */
public interface Queue<T> {

	/**
	 * Adds an element to the end of the Queue.
	 *
	 * @param t The element to add to the Queue.
	 * @return new ImmutableQueue
	 */
	public Queue<T> enQueue(T t);

	/**
	 * Removes and returns the element at the beginning of the Queue.
	 *
	 * @return new ImmutableQueue
	 */
	public Queue<T> deQueue() throws IllegalStateException;

	/**
	 * Returns the element at the beginning of the Queue without removing it.
	 *
	 * @return The element at the beginning of the Queue
	 */
	public T head();

	/**
	 * Checks if the Queue is empty.
	 *
	 * @return true when queue is empty, false otherwise
	 */
	public boolean isEmpty();
}
