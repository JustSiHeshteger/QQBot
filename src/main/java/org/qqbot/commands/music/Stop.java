package org.qqbot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class Stop implements BaseCommand {

    private final String name = "stop";
    private final String description = "Останавливает плеер";

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

        final GuildMusicManager musicManager =  PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.getPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            return;
        }

        musicManager.getScheduler().getPlayer().stopTrack();
        musicManager.getScheduler().getQueue().clear();
        event.getGuild().getAudioManager().closeAudioConnection();
    }
}
