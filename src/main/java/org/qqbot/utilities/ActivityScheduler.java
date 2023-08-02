package org.qqbot.utilities;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.qqbot.lavaplayer.GuildMusicManager;
import org.qqbot.lavaplayer.PlayerManager;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivityScheduler extends ListenerAdapter {

    private final long UPDATE_TIME = 30000;
    private final long TIMEOUT = 100000;


    public ActivityScheduler() {
        this.checkActivity();
    }

    private void checkActivity() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            for (var entry: PlayerManager.getInstance().getMusicManagers().entrySet()) {
                Guild guild = entry.getKey();
                GuildMusicManager guildMusicManager = entry.getValue();

                if (guildMusicManager.getPlayer().getPlayingTrack() == null) {
                    Date now = new Date();
                    long difference = now.getTime() - guildMusicManager.getLastActivity().getTime();
                    if (difference > TIMEOUT) {
                        guild.getAudioManager().closeAudioConnection();
                    }
                } else {
                    guildMusicManager.setLastActivity(new Date());
                }
            }
        }, 0, UPDATE_TIME, TimeUnit.MILLISECONDS);
    }
}
