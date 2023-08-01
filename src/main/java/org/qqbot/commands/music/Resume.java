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

public class Resume implements BaseCommand {

    private final String name = "resume";
    private final String description = "Возобновить проигрывание";

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
        final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = guildMusicManager.getPlayer();

        if (audioPlayer.isPaused()) {
            audioPlayer.setPaused(false);
            Utility.sendMessageEmbeds(textChannel, "Трек возобновил проигрывание", Color.green);
        }
    }
}
