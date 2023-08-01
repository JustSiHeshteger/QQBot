package org.qqbot;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.qqbot.listeners.ButtonListener;
import org.qqbot.listeners.CommandsListener;

import javax.security.auth.login.LoginException;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QQBot {

    @Getter
    private final Dotenv config;
    @Getter
    private final JDA jda;

    public QQBot() throws LoginException {
        config = Dotenv.configure().load();

        List<GatewayIntent> gatewayIntent = new ArrayList<>();
        gatewayIntent.add(GatewayIntent.MESSAGE_CONTENT);
        gatewayIntent.add(GatewayIntent.DIRECT_MESSAGES);
        gatewayIntent.add(GatewayIntent.GUILD_MESSAGES);
        gatewayIntent.add(GatewayIntent.GUILD_VOICE_STATES);
        gatewayIntent.add(GatewayIntent.GUILD_EMOJIS_AND_STICKERS);
        gatewayIntent.add(GatewayIntent.SCHEDULED_EVENTS);

        jda = JDABuilder.createDefault(config.get("TOKEN"))
                .disableCache(CacheFlag.CLIENT_STATUS, CacheFlag.ACTIVITY)
                .enableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("Жизнь в Морском"))
                .setEnabledIntents(gatewayIntent)
                .build();

        jda.addEventListener(new CommandsListener());
        jda.addEventListener(new ButtonListener());
    }
    public static void main(String[] args) throws LineUnavailableException, IOException {
        try {
            new QQBot();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
