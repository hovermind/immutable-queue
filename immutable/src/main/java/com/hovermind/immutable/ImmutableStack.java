package com.hovermind.immutable;

/**
 * Immutable implementation of Stack ADT. Represents a first-in, first-out collection of objects. The purpose of this
 * class is to serve as ImmutableStack type for frontStack & backStack for ImmutableQueue
 * 
 * @author HASSAN MD TAREQ
 * @see <a href="https://hovermind.com">hovermind.com</a>
 *
 * @param <S> element type of the Stack
 */
final class ImmutableStack<S> implements Stack<S> {

	private final S head;
	private final Stack<S> tail;

	private ImmutableStack(S head, Stack<S> tail) {
		this.head = head;
		this.tail = tail;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Stack<S> push(S value) {
		return new ImmutableStack<S>(value, this);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Stack<S> pop() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty.");
		}
		return tail;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final S head() throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty.");
		}
		return head;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isEmpty() {
		return tail == null;
	}

	/**
	 * Returns the empty Stack.
	 *
	 * @param <T> Component type
	 * @return The empty Stack.
	 */
	@SuppressWarnings({ "unchecked" })
	public static final <S> Stack<S> empty() {
		return (Stack<S>) EmptyStack.empty();
	}

	// empty Stack
	private static final class EmptyStack<T> implements Stack<T> {

		@SuppressWarnings("rawtypes")
		public static final EmptyStack empty() {
			return new EmptyStack();
		}

		public final Stack<T> push(T t) {
			return new ImmutableStack<T>(t, this);
		}

		public final Stack<T> pop() throws IllegalStateException {
			throw new IllegalStateException("Stack is empty.");
		}

		public final T head() throws IllegalStateException {
			throw new IllegalStateException("Stack is empty.");
		}

		public final boolean isEmpty() {
			return true;
		}
	}
}