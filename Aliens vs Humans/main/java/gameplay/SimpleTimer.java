package gameplay;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimer extends Thread implements Timer {
  List<TimerObserver> theObservers = new ArrayList<>();
  int round = 0;
  int sleep = 0;

  public SimpleTimer() {

  }

  public SimpleTimer(int sleep) {
    this.sleep = sleep;
  }

  @Override
  public void removerTimeObserver(TimerObserver o) {
    theObservers.remove(o);

  }

  @Override
public void addTimeObserver(TimerObserver o) {
    theObservers.add(o);

  }

  /**
   * changes the time
   */
  @Override
public void timeChanged() {
    round++;
    // notify
    theObservers.forEach(o -> o.updateTime(round));

  }

  public void removeTimeObserver(TimerObserver o) {
    theObservers.remove(o);
  }

  public int getNumObservers() {
    return theObservers.size();

  }

  public int getRound() {
    return round;

  }

  @Override
  public void run() {
    for (int x = 0; x < 50; x++) {
      try {
        Thread.sleep(sleep);
        timeChanged();
      } catch (InterruptedException e) {
        System.out.println("Catch");
      }
    }
  }

}
