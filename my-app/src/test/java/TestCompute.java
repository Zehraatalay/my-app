import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class TestCompute {
    Compute c;
    MessageQueue mq;

    @Before
    public void setUp() {
        mq = mock(MessageQueue.class);
        c = new Compute(mq);
    }

    @Test
    public void testWithEmptyQueue() { // size = 0 oldugu durum
        when(mq.size()).thenReturn(0);
	    int result = c.countNumberOfOccurrences("hello");
        assertEquals(-1, result);
        verify(mq).size();
    }

    @Test
    public void testWithNonexistentElement() { // parametre olan string'i icermedigi durum
        when(mq.size()).thenReturn(5);
        when(mq.contains("hello")).thenReturn(false);
        assertEquals(0, c.countNumberOfOccurrences("hello"));
        verify(mq).size();
        verify(mq).contains("hello");
    }

    @Test
    public void testWithExistingSingleTimes() {
	when(mq.size()).thenReturn(6);
	when(mq.getAt(0)).thenReturn("world");
        when(mq.getAt(1)).thenReturn("hello");
        when(mq.getAt(2)).thenReturn("hello");
	when(mq.getAt(3)).thenReturn("hello");
	when(mq.getAt(4)).thenReturn("hello");
        when(mq.getAt(5)).thenReturn("hello");
	when(mq.contains("world")).thenReturn(true);
        assertEquals(1, c.countNumberOfOccurrences("world"));
	verify(mq).size();
        verify(mq).contains("world");
        verify(mq).getAt(0);
        verify(mq).getAt(1);
        verify(mq).getAt(2);
	verify(mq).getAt(3);
	verify(mq).getAt(4);
	verify(mq).getAt(5);

    }

    @Test
    public void testWithExistingMultipleTimes() { // count = 2 olmali
        when(mq.size()).thenReturn(4);
        when(mq.getAt(0)).thenReturn("world");
        when(mq.getAt(1)).thenReturn("hello");
        when(mq.getAt(2)).thenReturn("world");
	when(mq.getAt(3)).thenReturn("hello");
        when(mq.contains("world")).thenReturn(true);
        assertEquals(2, c.countNumberOfOccurrences("world"));
        verify(mq).size();
        verify(mq).contains("world");
        verify(mq).getAt(0);
        verify(mq).getAt(1);
        verify(mq).getAt(2);
	verify(mq).getAt(3);
    }

}