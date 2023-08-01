package org.qqbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pause implements BaseCommand {

    private final String name = "pause";
    private final String description = "Приостановить трек";

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<OptionData> getOptionDataList() {
        return new ArrayList<>();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws InputDataException {
        Utility.checkAllData(event);

        final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = guildMusicManager.getPlayer();
        final TextChannel textChannel = event.getChannel().asTextChannel();

        if (!audioPlayer.isPaused()) {
            audioPlayer.setPaused(true);
            Utility.sendMessageEmbeds(textChannel, "Трек остановлен", Color.green);
        }
    }
}
