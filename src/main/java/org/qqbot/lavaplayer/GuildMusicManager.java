package org.qqbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;

public class GuildMusicManager {
    @Getter @Setter
    private Date lastActivity;
    @Getter
    private final AudioPlayer player;
    @Getter
    private final TrackScheduler scheduler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.player = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.player);
        this.player.addListener(this.scheduler);

        this.lastActivity = new Date();
    }

    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(this.player);
    }
}
