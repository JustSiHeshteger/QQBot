package org.qqbot.buttons.basic;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.qqbot.exceptions.InputDataException;

public interface BaseButton extends Button {
    void onClick(ButtonInteractionEvent event) throws InputDataException;
}
