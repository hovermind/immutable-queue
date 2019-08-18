package com.hovermind.immutable;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * JUnit 4 test class for {@link ImmutableStack}.
 * 
 * @author HASSAN MD TAREQ
 * @see <a href="https://hovermind.com">hovermind.com</a>
 */
public class ImmutableStackTest {

	@Test
	public void emptyTest() {
		Stack<?> sut = ImmutableStack.empty();
		assertTrue(sut.isEmpty());
	}

	@Test(expected = IllegalStateException.class)
	public void shouldFailHeadOfEmpty() {
		ImmutableStack.empty().head();
	}

	@Test
	public void shouldReturnHeadOfNonEmpty() {
		Integer expected = Integer.valueOf(1);
		Stack<Integer> sut = ImmutableStack.<Integer>empty().push(expected);
		Integer actual = sut.head();
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void pushTest() {
		Stack<Integer> sutOne = ImmutableStack.<Integer>empty().push(1);
		Stack<Integer> sutTwo = sutOne.push(2);
		assertThat(sutOne, is(not(equalTo(sutTwo))));
	}

	@Test
	public void popTest() {
		Stack<Integer> sut = ImmutableStack.<Integer>empty().push(1);
		sut = sut.push(2);
		sut = sut.pop();
		assertFalse(sut.isEmpty());
	}

	@Test(expected = IllegalStateException.class)
	public void popExceptionTest() {
		Stack<?> sut = ImmutableStack.empty();
		sut.pop();
	}

	@Test
	public void simpleOperationTest() {
		Stack<Integer> sut = ImmutableStack.<Integer>empty().push(1);
		sut = sut.push(2);
		sut = sut.push(3);
		assertThat(3, is(equalTo(sut.head())));

		sut = sut.pop();
		assertFalse(sut.isEmpty());
		assertThat(2, is(equalTo(sut.head())));

		sut = sut.pop();
		assertFalse(sut.isEmpty());
		assertThat(1, is(equalTo(sut.head())));

		sut = sut.pop();
		assertTrue(sut.isEmpty());
	}
}
