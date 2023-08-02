package org.qqbot.buttons;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.qqbot.buttons.basic.AbstractButton;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

import java.awt.*;

public class StopButton extends AbstractButton {

    public StopButton(String id, String label, ButtonStyle style, boolean disabled, Emoji emoji) {
        super(id, label, style, disabled, emoji);
    }

    @Override
    public void onClickButton(ButtonInteractionEvent event) throws InputDataException {
        Utility.checkAllData(event);

        final TextChannel textChannel = event.getChannel().asTextChannel();
        final GuildMusicManager musicManager =  PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.getPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            Utility.sendMessageEmbeds(textChannel, "Сейчас ничего не играет", Color.red);
            return;
        }

        musicManager.getScheduler().getPlayer().stopTrack();
        musicManager.getScheduler().getQueue().clear();
        event.getGuild().getAudioManager().closeAudioConnection();
    }
}
