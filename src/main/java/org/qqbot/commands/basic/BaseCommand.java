package org.qqbot.commands.basic;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.qqbot.exceptions.InputDataException;

import java.util.List;

public interface BaseCommand {
    String getName();
    String getDescription();
    List<OptionData> getOptionDataList();
    void execute(SlashCommandInteractionEvent event) throws InputDataException;
}
