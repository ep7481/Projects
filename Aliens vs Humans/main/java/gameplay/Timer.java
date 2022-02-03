package gameplay;

public interface Timer {
  public void addTimeObserver(TimerObserver o);

  public void removerTimeObserver(TimerObserver o);

  public void timeChanged();
}
