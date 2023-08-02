package org.qqbot.managers;

import lombok.Getter;
import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.commands.buttons.GetMenu;
import org.qqbot.commands.music.*;

import java.util.List;

public class CommandsManager {
    @Getter
    private static List<BaseCommand> commands = List.of(
            new Play(),
            new Skip(),
            new Add(),
            new Pause(),
            new Stop(),
            new Resume(),
            new GetMenu()
    );
}
