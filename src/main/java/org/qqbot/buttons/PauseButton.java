package org.qqbot.buttons;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.qqbot.buttons.basic.AbstractButton;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

public class PauseButton extends AbstractButton {

    public PauseButton(String id, String label, ButtonStyle style, boolean disabled, Emoji emoji) {
        super(id, label, style, disabled, emoji);
    }

    @Override
    public void onClickButton(ButtonInteractionEvent event) throws InputDataException {
        Utility.checkAllData(event);

        final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = guildMusicManager.getPlayer();

        if (!audioPlayer.isPaused()) {
            audioPlayer.setPaused(true);
        }
    }
}
