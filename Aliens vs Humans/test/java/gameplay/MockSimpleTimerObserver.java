package gameplay;

public class MockSimpleTimerObserver implements TimerObserver {
  public int myTime = 0;

@Override
  public void updateTime(int time) {
    myTime = time;
  }

}
