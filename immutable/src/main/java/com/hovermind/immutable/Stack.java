package com.hovermind.immutable;

/**
 * Defines the interface of a jvm Stack ADT.
 * 
 * @author HASSAN MD TAREQ
 * 
 * @param <T> element type of the Stack
 */
public interface Stack<T> {

	/**
	 * Inserts an element at the top of the Stack.
	 *
	 * @param t The element to push onto the Stack
	 * @return new ImmutableStack
	 */
	public Stack<T> push(T t);

	/**
	 * Removes the element at the top of the Stack.
	 *
	 * @return new ImmutableStack
	 * @throws IllegalStateException when empty
	 */
	public Stack<T> pop() throws IllegalStateException;

	/**
	 * Returns the element at the top of the Stack without removing it.
	 *
	 * @return The element at the top of the Stack
	 * @throws IllegalStateException when empty
	 */
	public T head() throws IllegalStateException;

	/**
	 * Checks if the stack is empty.
	 *
	 * @return true when stack is empty, false otherwise
	 */
	public boolean isEmpty();
}