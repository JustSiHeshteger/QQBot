package org.qqbot.utilities;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.qqbot.buttons.PauseButton;
import org.qqbot.buttons.PlayButton;
import org.qqbot.buttons.SkipButton;
import org.qqbot.buttons.StopButton;
import org.qqbot.buttons.basic.AbstractButton;
import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.commands.buttons.GetMenu;
import org.qqbot.commands.music.*;
import org.qqbot.exceptions.InputDataException;

import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Utility {

    public static boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public static void checkAllData(SlashCommandInteractionEvent event) throws InputDataException {
        final Member member = event.getMember();
        final Member bot = event.getMember().getGuild().getSelfMember();
        final GuildVoiceState botState = bot.getVoiceState();
        final GuildVoiceState memberState = member.getVoiceState();

        if (!memberState.inAudioChannel()) {
            throw new InputDataException("Участник не находится в аудиоканале.");
        }

        if (!botState.inAudioChannel()) {
            throw new InputDataException("Бот не находится в аудиоканале.");
        }

        if (!Objects.equals(memberState.getChannel(), botState.getChannel())) {
            throw new InputDataException("Участник и бот в разных каналах.");
        }
    }

    public static void checkAllData(ButtonInteractionEvent event) throws InputDataException {
        final Member member = event.getMember();
        final Member bot = event.getMember().getGuild().getSelfMember();
        final GuildVoiceState botState = bot.getVoiceState();
        final GuildVoiceState memberState = member.getVoiceState();

        if (!memberState.inAudioChannel()) {
            throw new InputDataException("Участник не находится в аудиоканале.");
        }

        if (!botState.inAudioChannel()) {
            throw new InputDataException("Бот не находится в аудиоканале.");
        }

        if (!Objects.equals(memberState.getChannel(), botState.getChannel())) {
            throw new InputDataException("Участник и бот в разных каналах.");
        }
    }

    public static void sendMessageEmbeds(TextChannel textChannel, String description, Color color) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(description);
        builder.setColor(color);
        textChannel.sendMessageEmbeds(builder.build()).queue();
    }

    private static List<BaseCommand> commands = List.of(
            new Play(),
            new Skip(),
            new Add(),
            new Pause(),
            new Stop(),
            new Resume(),
            new GetMenu()
    );

    public static List<BaseCommand> getCommands() {
        return commands;
    }

    @Getter
    private static List<AbstractButton> buttons = List.of(
            new PauseButton("pause", "Pause", ButtonStyle.PRIMARY, false, null),
            new PlayButton("play", "Play", ButtonStyle.SUCCESS, false, null),
            new SkipButton("skip", "Skip", ButtonStyle.PRIMARY, false, null),
            new StopButton("stop", "Stop", ButtonStyle.DANGER, false, null)
    );
}
