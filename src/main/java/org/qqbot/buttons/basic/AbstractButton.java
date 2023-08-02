package org.qqbot.buttons.basic;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;
import org.qqbot.exceptions.InputDataException;

public abstract class AbstractButton extends ButtonImpl {

    public AbstractButton(String id, String label, ButtonStyle style, boolean disabled, Emoji emoji) {
        super(id, label, style, disabled, emoji);
    }

    public abstract void onClickButton(ButtonInteractionEvent event) throws InputDataException;
}
