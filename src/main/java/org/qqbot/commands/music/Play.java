package org.qqbot.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.exceptions.InputDataException;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.managers.ButtonsManager;
import org.qqbot.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class Play implements BaseCommand {

    private final String name = "play";
    private final String description = "Начинает проигрывать музыку";
    private List<OptionData> optionDataList;

    public Play() {
        this.optionDataList = new ArrayList<>();
        OptionData optionData1 = new OptionData(OptionType.STRING, "name", "ссылка или название", true);
        optionDataList.add(optionData1);
    }

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
        return this.optionDataList;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws InputDataException {
        String name = Utility.isUrl(event.getOption("name").getAsString())
                ? event.getOption("name").getAsString()
                : "ytsearch:" + event.getOption("name").getAsString();

        final TextChannel textChannel = event.getChannel().asTextChannel();
        final Member member = event.getMember();
        final GuildVoiceState memberState = member.getVoiceState();

        if (!memberState.inAudioChannel()) {
            throw new InputDataException("Участник должен находится в канале.");
        }

        final VoiceChannel voiceChannel = memberState.getChannel().asVoiceChannel();
        PlayerManager.getInstance().loadAndPlay(textChannel, name, voiceChannel);
        event.getChannel().asTextChannel().sendMessage("").addActionRow(ButtonsManager.getButtons()).queue();
    }
}
