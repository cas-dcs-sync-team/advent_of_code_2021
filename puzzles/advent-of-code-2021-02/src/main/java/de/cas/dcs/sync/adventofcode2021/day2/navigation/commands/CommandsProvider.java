package de.cas.dcs.sync.adventofcode2021.day2.navigation.commands;

import java.io.IOException;
import java.util.List;

public interface CommandsProvider {
  List<NavigationCommand> getNavigationCommands() throws IOException;
}
