package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Name("Decent Holograms - Edit Page")
@Description("Edits a page of a hologram.")
@Examples({
        "..."
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffEditPageHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEffect(EffEditPageHologram.class,
                    "add [page] of [hologram] %holograms%",
                    "remove [page] %integer% of [hologram] %holograms%",
                    "insert [page] %integer% of [hologram] %holograms%"
            );
        }
    }

    private String changetype;
    private Expression<Hologram> hologram;
    private Expression<Integer> page;

    @Override
    protected void execute(@NotNull Event e) {
        for (Hologram hologram : this.hologram.getArray(e)) {
            if (Objects.equals(changetype, "add")){
                DHAPI.addHologramPage(hologram);
            } else if (Objects.equals(changetype, "remove")){
                Integer page = this.page.getSingle(e);
                if (page != null){
                    DHAPI.removeHologramPage(hologram, page);
                }
            } else if (Objects.equals(changetype, "insert")){
                Integer page = this.page.getSingle(e);
                if (page != null){
                    DHAPI.insertHologramPage(hologram, page);
                }
            } else if (Objects.equals(changetype, "get")){
                Integer page = this.page.getSingle(e);
                if (page != null){
                    DHAPI.getHologramPage(hologram, page);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "edit hologram page";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            changetype = "add";
            hologram = (Expression<Hologram>) exprs[0];
        }
        else if (matchedPattern == 1){
            changetype = "remove";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        else if (matchedPattern == 2){
            changetype = "insert";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        else if (matchedPattern == 3){
            changetype = "get";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        return true;
    }
}
