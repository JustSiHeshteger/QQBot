package org.qqbot.utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import org.qqbot.exceptions.InputDataException;

import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;
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
}
