package lol.aabss.skuishy.hooks.vulcan.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Vulcan - Toggle Alerts")
@Description("Toggles alerts for a player.")
@Examples({
        "toggle alerts for player"
})
@Since("2.0")
public class EffToggleAlerts extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerEffect(EffToggleAlerts.class,
                    "toggle [the] [vulcan] [hack[ing]] alerts for %players%"
            );
        }
    }

    private Expression<Player> p;

    @Override
    protected void execute(@NotNull Event e) {
        for (Player p : this.p.getArray(e)) {
            VulcanAPI.Factory.getApi().toggleAlerts(p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "toggle alerts";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        p = (Expression<Player>) exprs[0];
        return true;
    }
}
