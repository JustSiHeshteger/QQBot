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

public class Skip implements BaseCommand {

    private final String name = "skip";
    private final String description = "Попустить трек";

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

        final TextChannel textChannel = event.getChannel().asTextChannel();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.getPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            Utility.sendMessageEmbeds(textChannel, "Сейчас ничего не играет", Color.red);
            return;
        }

        if (musicManager.getScheduler().getQueue().size() == 0) {
            musicManager.getScheduler().getPlayer().stopTrack();
            musicManager.getScheduler().getQueue().clear();
        } else {
            musicManager.getScheduler().nextTrack();
        }
    }
}
