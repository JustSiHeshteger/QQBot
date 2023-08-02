package org.qqbot.listeners;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.managers.ButtonsManager;
import org.qqbot.utilities.Utility;

import java.awt.*;

public class ButtonsListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        String id = event.getButton().getId();

        for (var button: ButtonsManager.getButtons()) {
            if (button.getId().equalsIgnoreCase(id)) {
                try {
                    button.onClickButton(event);
                } catch (InputDataException ex) {
                    Utility.sendMessageEmbeds(event.getChannel().asTextChannel(), ex.getDescription(), Color.red);
                }
                break;
            }
        }
    }
}
