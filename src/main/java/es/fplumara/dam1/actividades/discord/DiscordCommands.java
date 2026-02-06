package es.fplumara.dam1.actividades.discord;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static java.time.ZoneOffset.UTC;

public class DiscordCommands extends ListenerAdapter {

        @Override
        public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

            switch (event.getName()) {

                case "ping":
                    event.reply("pong!").queue();
                    break;

                case "hora":
                    String zoneId = event.getOption("zone").getAsString();
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    df.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                    String horaFormateada = df.format(date);
            }
        }
    }
