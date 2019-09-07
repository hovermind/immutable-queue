package com.hovermind.immutable;

/**
 * Immutable implementation of Queue ADT. Represents a variable size first-in-first-out (FIFO) collection of instances of
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
		this.frontStack = frontStack;
		this.backStack = backStack;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Queue<T> enQueue(T t) {
		Stack<T> newBackStack = backStack.push(t);
		return new ImmutableQueue<T>(frontStack, newBackStack);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalStateException
	 */
	public final Queue<T> deQueue() throws IllegalStateException {

		Stack<T> newFrontStack = frontStack.pop();
		if (!newFrontStack.isEmpty()) {
			return new ImmutableQueue<T>(newFrontStack, backStack);
		} else if (backStack.isEmpty()) {
			return ImmutableQueue.<T>empty();
		} else {
			newFrontStack = reverse(backStack);
			Stack<T> newBackStack = ImmutableStack.<T>empty();
			return new ImmutableQueue<T>(newFrontStack, newBackStack);
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
		return false;
	}

	// Reverses the stack
	private final Stack<T> reverse(Stack<T> stack) throws IllegalStateException {
		Stack<T> reversed = ImmutableStack.empty();
		while (!stack.isEmpty()) {
			reversed = reversed.push(stack.head());
			stack = stack.pop();
		}
		return reversed;
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
