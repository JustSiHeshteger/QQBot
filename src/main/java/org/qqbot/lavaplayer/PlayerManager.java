package org.qqbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.qqbot.utilities.Utility;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static PlayerManager playerManager;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    private PlayerManager() {
        this.audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);

        this.musicManagers = new HashMap<>();
    }

    public void loadAndPlay(final TextChannel channel, final String trackUrl, final VoiceChannel voiceChannel) {
        GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                Utility.sendMessageEmbeds(channel, "Добавил в очередь " + track.getInfo().title, Color.green);
                play(channel.getGuild(), musicManager, track, voiceChannel);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                AudioTrack firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }

                Utility.sendMessageEmbeds(channel, "Добавил в очередь " + playlist.getName(), Color.green);
                play(channel.getGuild(), musicManager, firstTrack, voiceChannel);
            }

            @Override
            public void noMatches() {
                Utility.sendMessageEmbeds(channel, "Не смог найти " + trackUrl, Color.red);
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                Utility.sendMessageEmbeds(channel, "Не осилил загрузку :( " + exception.getMessage(), Color.red);
            }
        });
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (Id) -> {
           final GuildMusicManager guildMusicManager = new GuildMusicManager(audioPlayerManager);
           guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
           return guildMusicManager;
        });
    }

    private void play(Guild guild, GuildMusicManager musicManager, AudioTrack track, VoiceChannel voiceChannel) {
        guild.getAudioManager().openAudioConnection(voiceChannel);

        musicManager.getScheduler().queue(track);
    }

    public static PlayerManager getInstance() {
        if (playerManager == null) {
            playerManager = new PlayerManager();
        }

        return playerManager;
    }
}
