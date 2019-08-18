package com.hovermind.immutable;

/**
 * Immutable implementation of Queue ADT. Represents a variable size last-in-first-out (LIFO) collection of instances of
 * the same specified type.
 * 
 * This uses two immutable stacks to keep track of the elements.
 * 
 * @author HASSAN MD TAREQ
 * @see <a href="https://hovermind.com">hovermind.com</a>
 *
 * @param <T> element type of the Queue
 */
public final class ImmutableQueue<T> implements Queue<T> {

	// backing stacks to keep track of the elements
	private final Stack<T> frontStack;
	private final Stack<T> backStack;

	private ImmutableQueue(Stack<T> frontStack, Stack<T> backStack) {

		if (frontStack == null) {
			throw new IllegalArgumentException("frontStack");
		}

		if (backStack == null) {
			throw new IllegalArgumentException("backStack");
		}

		this.frontStack = frontStack;
		this.backStack = backStack;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Queue<T> enQueue(T t) {
		return new ImmutableQueue<T>(frontStack, backStack.push(t));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalStateException
	 */
	@SuppressWarnings("unchecked")
	public final Queue<T> deQueue() throws IllegalStateException {

		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty.");
		}

		Stack<T> stack = frontStack.pop();
		if (!stack.isEmpty()) {
			return new ImmutableQueue<T>(stack, backStack);
		} else if (backStack.isEmpty()) {
			return ImmutableQueue.empty();
		} else {
			return new ImmutableQueue<T>(reverse(backStack), (Stack<T>) ImmutableStack.empty());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final T head() {
		return frontStack.head();
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isEmpty() {
		return frontStack.isEmpty() && backStack.isEmpty();
	}

	// Reverses the stack
	private final Stack<T> reverse(Stack<T> stack) throws IllegalStateException {
		Stack<T> result = ImmutableStack.empty();
		Stack<T> cur = stack;
		while (!cur.isEmpty()) {
			result = result.push(cur.head());
			cur = cur.pop();
		}
		return result;
	}

	/**
	 * Returns the empty Queue.
	 *
	 * @param <T> element type
	 * @return an empty Queue.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Queue<T> empty() {
		return (Queue<T>) EmptyQueue.empty();
	}

	// empty Queue
	private static final class EmptyQueue<T> implements Queue<T> {

		@SuppressWarnings("rawtypes")
		public static final EmptyQueue empty() {
			return new EmptyQueue();
		}

		@SuppressWarnings("unchecked")
		public final Queue<T> enQueue(T t) {
			return new ImmutableQueue<T>((Stack<T>) ImmutableStack.empty().push(t), (Stack<T>) ImmutableStack.empty());
		}

		public final Queue<T> deQueue() throws IllegalStateException {
			throw new IllegalStateException("Queue is empty.");
		}

		public final T head() throws IllegalStateException {
			throw new IllegalStateException("Queue is empty.");
		}

		public final boolean isEmpty() {
			return true;
		}
	}
}
