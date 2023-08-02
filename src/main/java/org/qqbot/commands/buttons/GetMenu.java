package org.qqbot.commands.buttons;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;
import org.qqbot.buttons.PlayButton;
import org.qqbot.commands.basic.BaseCommand;
import org.qqbot.lavaplayer.PlayerManager;
import org.qqbot.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class GetMenu implements BaseCommand {

    private final String name = "getmenu";
    private final String description = "Показывает кнопки";

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
    public void execute(SlashCommandInteractionEvent event) {
        if (PlayerManager.getInstance().getMusicManager(event.getGuild()).getPlayer().getPlayingTrack() == null) {
            event.getChannel().asTextChannel().sendMessage("Сейчас ничего не играет").queue();
            return;
        }

        event.getChannel().asTextChannel().sendMessage("").setActionRow(Utility.getButtons()).queue();
    }
}
