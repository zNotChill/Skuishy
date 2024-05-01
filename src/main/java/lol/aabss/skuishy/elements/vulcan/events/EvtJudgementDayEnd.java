package lol.aabss.skuishy.elements.vulcan.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import me.frep.vulcan.api.event.VulcanJudgementDayEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtJudgementDayEnd extends SkriptEvent {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEvent("Vulcan - Judgement Day End", EvtJudgementDayEnd.class, VulcanJudgementDayEndEvent.class,
                    "[vulcan] judge[ment] [day] end[ed]"
            )
                    .description("Called when the judgement day ends.")
                    .examples(
                            "on judge day end:",
                            "\tstrike lightning at all players",
                            "\tsend \"Judgement day has ended!\"",
                            "\twait 1 tick",
                            "\theal all players"
                    )
                    .since("1.9");
        }
    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        assert e != null;
        return e.getEventName();
    }
}
