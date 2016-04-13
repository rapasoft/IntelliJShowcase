package no.itera.bean;

import no.itera.model.HelloWorld;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by Pavol Rajzak, Itera.
 */
public class HelloWorldServiceImplTest {

	private HelloWorldServiceImpl helloWorldBean;

	@Before
	public void before() {
		helloWorldBean = new HelloWorldServiceImpl();
		helloWorldBean.entityManager = mock(EntityManager.class);

		HelloWorld helloWorld = mock(HelloWorld.class);
		when(helloWorld.getMessage()).thenReturn("Hello World");

		when(helloWorldBean.entityManager.find(eq(HelloWorld.class), anyInt())).thenReturn(helloWorld);
	}

	@Test
	public void fetchHelloWorld() throws Exception {
		HelloWorld helloWorld = helloWorldBean.fetch(1);

		assertEquals("Hello World", helloWorld.getMessage());

		verify(helloWorldBean.entityManager).find(eq(HelloWorld.class), eq(1));
	}

}