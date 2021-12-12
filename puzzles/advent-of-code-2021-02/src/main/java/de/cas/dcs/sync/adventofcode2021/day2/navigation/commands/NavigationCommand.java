package de.cas.dcs.sync.adventofcode2021.day2.navigation.commands;

public class NavigationCommand {

  private final CommandType type;
  private final int value;

  public NavigationCommand(CommandType type, int value) {
    this.type = type;
    this.value = value;
  }

  public CommandType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }
}
