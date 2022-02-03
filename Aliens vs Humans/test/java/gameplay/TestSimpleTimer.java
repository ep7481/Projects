package gameplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSimpleTimer {

  @Test
  public void testInitialization() {
    SimpleTimer st = new SimpleTimer();
    assertEquals(0, st.getRound());
  }

  @Test
  public void testAddTimerObserver() {
    SimpleTimer st = new SimpleTimer();
    MockSimpleTimerObserver mock = new MockSimpleTimerObserver();
    st.addTimeObserver(mock);
    assertEquals(1, st.getNumObservers());
  }

  @Test
  public void testRemoveTimerObserver() {
    SimpleTimer st = new SimpleTimer();
    MockSimpleTimerObserver mock = new MockSimpleTimerObserver();
    MockSimpleTimerObserver mock2 = new MockSimpleTimerObserver();
    st.addTimeObserver(mock);
    assertEquals(1, st.getNumObservers());
    st.addTimeObserver(mock2);
    assertEquals(2, st.getNumObservers());
    st.removerTimeObserver(mock);
    st.removerTimeObserver(mock2);
    assertEquals(0, st.getNumObservers());
  }

  @Test
  public void testTimeChanged() {
    SimpleTimer st = new SimpleTimer();
    st.timeChanged();
    assertEquals(1, st.getRound());
  }

  @Test
  public void testSimpleTimerAsThread() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250);
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound());
      Thread.sleep(1000);
    }

  }

}
