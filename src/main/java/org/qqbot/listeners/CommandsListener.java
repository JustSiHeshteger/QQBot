package org.qqbot.listeners;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.utilities.Utility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandsListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (!event.isGuildCommand()) {
            return;
        }

        String commandName = event.getName().toLowerCase();

        for (var command: Utility.getCommands()) {
            if (commandName.equalsIgnoreCase(command.getName().toLowerCase())) {
                try {
                    command.execute(event);
                } catch (InputDataException ex) {
                    Utility.sendMessageEmbeds(event.getChannel().asTextChannel(), ex.getDescription(), Color.red);
                }
                break;
            }
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandDataList = new ArrayList<>();

        for (var command: Utility.getCommands()) {
            var data = Commands.slash(command.getName(), command.getDescription());
            for (var option: command.getOptionDataList()) {
                data.addOptions(option);
            }
            commandDataList.add(data);
        }

        event.getGuild().updateCommands().addCommands(commandDataList).queue();
    }

}
