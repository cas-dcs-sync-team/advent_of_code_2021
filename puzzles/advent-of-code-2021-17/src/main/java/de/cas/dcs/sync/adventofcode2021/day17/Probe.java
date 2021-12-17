package de.cas.dcs.sync.adventofcode2021.day17;

public class Probe {
  public long y_position_max = 0L;

  public long x_position = 0L;
  public long y_position = 0L;

  public long x_velocity;
  public long y_velocity;

  public long x_start_velocity;
  public long y_start_velocity;

  public Probe(long x_start_velocity, long y_start_velocity) {
    this.x_velocity = x_start_velocity;
    this.y_velocity = y_start_velocity;
    this.x_start_velocity = x_start_velocity;
    this.y_start_velocity = y_start_velocity;
  }

  @Override
  public String toString() {
    return "Probe{"
        + "y_position_max="
        + y_position_max
        + ", x_position="
        + x_position
        + ", y_position="
        + y_position
        + ", x_velocity="
        + x_velocity
        + ", y_velocity="
        + y_velocity
        + ", x_start_velocity="
        + x_start_velocity
        + ", y_start_velocity="
        + y_start_velocity
        + '}';
  }
}
