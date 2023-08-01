package org.qqbot.buttons;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.qqbot.buttons.basic.BaseButton;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

public class PlayButton implements BaseButton {

    private final String id = "play";
    private final String label = "Play";

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public ButtonStyle getStyle() {
        return ButtonStyle.SUCCESS;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public EmojiUnion getEmoji() {
        return null;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isDisabled() {
        return false;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public DataObject toData() {
        return null;
    }

    @Override
    public void onClick(ButtonInteractionEvent event) throws InputDataException {
        Utility.checkAllData(event);

        final GuildMusicManager guildMusicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = guildMusicManager.getPlayer();

        if (audioPlayer.isPaused()) {
            audioPlayer.setPaused(false);
        }
    }
}
