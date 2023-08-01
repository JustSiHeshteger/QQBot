package org.qqbot.listeners;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

import java.awt.*;

public class ButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        String id = event.getButton().getId();

        /*for (var button: Utility.getButtons()) {
            if (button.getId().equalsIgnoreCase(id)) {
                try {
                    button.onClick(event);
                } catch (InputDataException ex) {
                    Utility.sendMessageEmbeds(event.getChannel().asTextChannel(), ex.getDescription(), Color.red);
                }

                break;
            }
        }*/

        switch (id) {
            case "play": {
                try {
                    Utility.checkAllData(event);;
                } catch (InputDataException ex) {
                    Utility.sendMessageEmbeds(event.getChannel().asTextChannel(), ex.getDescription(), Color.red);
                }

                final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                final AudioPlayer audioPlayer = guildMusicManager.getPlayer();

                if (audioPlayer.isPaused()) {
                    audioPlayer.setPaused(false);
                }
                break;
            }
            case "pause": {
                try {
                    Utility.checkAllData(event);;
                } catch (InputDataException ex) {
                    Utility.sendMessageEmbeds(event.getChannel().asTextChannel(), ex.getDescription(), Color.red);
                }

                final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                final AudioPlayer audioPlayer = guildMusicManager.getPlayer();

                if (!audioPlayer.isPaused()) {
                    audioPlayer.setPaused(true);
                }
                break;
            }
        }
    }
}
