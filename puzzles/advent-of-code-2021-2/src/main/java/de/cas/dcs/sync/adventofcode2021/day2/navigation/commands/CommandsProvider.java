package de.cas.dcs.sync.adventofcode2021.day2.navigation.commands;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;

import java.io.IOException;
import java.util.List;

public interface CommandsProvider {
	public List<NavigationCommand> getNavigationCommands() throws IOException;
}
