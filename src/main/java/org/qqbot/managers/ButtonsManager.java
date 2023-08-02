package org.qqbot.managers;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.qqbot.buttons.PauseButton;
import org.qqbot.buttons.PlayButton;
import org.qqbot.buttons.SkipButton;
import org.qqbot.buttons.StopButton;
import org.qqbot.buttons.basic.AbstractButton;

import java.util.List;

public class ButtonsManager {
    @Getter
    private static List<AbstractButton> buttons = List.of(
            new PauseButton("pause", "Pause", ButtonStyle.PRIMARY, false, null),
            new PlayButton("play", "Play", ButtonStyle.SUCCESS, false, null),
            new SkipButton("skip", "Skip", ButtonStyle.PRIMARY, false, null),
            new StopButton("stop", "Stop", ButtonStyle.DANGER, false, null)
    );
}
