package com.hovermind.immutable;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * JUnit 4 test class for {@link ImmutableQueue}.
 * 
 * @author HASSAN MD TAREQ
 * @see <a href="https://hovermind.com">hovermind.com</a>
 */
public class ImmutableQueueTest {

	@Test
	public void emptyTest() {
		Queue<?> sut = ImmutableQueue.empty();
		assertTrue(sut.isEmpty());
	}

	@Test(expected = IllegalStateException.class)
	public void shouldFailHeadOfEmpty() {
		ImmutableQueue.empty().head();
	}

	@Test
	public void shouldReturnHeadOfNonEmpty() {
		Integer expected = Integer.valueOf(1);
		Queue<Integer> sut = ImmutableQueue.<Integer>empty().enQueue(expected);
		Integer actual = sut.head();
		assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void enQueueTest() {
		Queue<Integer> sutOne = ImmutableQueue.<Integer>empty().enQueue(1);
		Queue<Integer> sutTwo = sutOne.enQueue(2);
		assertThat(sutOne, is(not(equalTo(sutTwo))));
	}

	@Test
	public void deQueueTest() {
		Queue<Integer> sut = ImmutableQueue.<Integer>empty().enQueue(1);
		sut = sut.enQueue(2);
		sut = sut.deQueue();
		assertFalse(sut.isEmpty());
	}

	@Test(expected = IllegalStateException.class)
	public void deQueueExceptionTest() {
		Queue<?> sut = ImmutableQueue.empty();
		sut.deQueue();
	}

	@Test
	public void simpleOperationTest() {
		Queue<Integer> sut = ImmutableQueue.<Integer>empty().enQueue(1);
		sut = sut.enQueue(2);
		sut = sut.enQueue(3);
		assertThat(1, is(equalTo(sut.head())));

		sut = sut.deQueue();
		assertFalse(sut.isEmpty());
		assertThat(2, is(equalTo(sut.head())));

		sut = sut.deQueue();
		assertFalse(sut.isEmpty());
		assertThat(3, is(equalTo(sut.head())));

		sut = sut.deQueue();
		assertTrue(sut.isEmpty());
	}

}
