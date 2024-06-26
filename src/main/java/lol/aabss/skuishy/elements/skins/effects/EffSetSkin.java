package lol.aabss.skuishy.elements.skins.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.SkinWrapper;
import lol.aabss.skuishy.other.blueprints.Blueprint;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

@Name("Player - Set Skin")
@Description({"Sets the skin of the player to a value and a signature."})
@Examples({
        "set skin of player to \"Dinnerbone\" #A name of a player",
        "set skin of player to \"http://textures.minecraft.net/texture/9c8acc755dc6b5e1d282d528030ebc20823a7608853ad5f747ff7ec45d576555\" #Official minecraft skin site",
        "set skin of player to \"https://i.imgur.com/KWITSCB.png\" #An image from any website (nothing but the image on the site)",
        "set skin of player to \"{VALUE}\" #The full value of the skin (no signature)",
        "set skin of player to \"{VALUE}\" and \"{SIGNATURE}\" #The full value and signature of the skin.",
        "set skin of player to {_bufferedimage} # Images not supported by Skuishy, but just in case you want to use another addon like SkImage :)",
        "set skin of player to {_blueprint} #A blueprint"
})
@Since("2.3, 2.6 (fixed)")

public class EffSetSkin extends Effect {

    static {
        Skript.registerEffect(EffSetSkin.class,
                "set [minecraft] skin of %players% to %object%",
                "set %players%'[s] [minecraft] skin to %object%",
                "set [minecraft] skin of %players% to [value] %string% and [signature] %string%",
                "set %player%'[s] [minecraft] skin to [value] %string% and [signature] %string%"
        );
    }

    private Expression<Player> player;
    private Expression<Object> skin;
    private Expression<String> value;
    private Expression<String> signature;

    @Override
    protected void execute(@NotNull Event e) {
        if (value == null || signature == null) {
            if (skin != null) {
                Object skin = this.skin.getSingle(e);
                if (skin != null) {
                    for (Player p : player.getArray(e)) {
                        if (skin instanceof String str) {
                            if (str.length() <= 16 || !str.contains("://")) {
                                SkinWrapper.setSkin(p, str);
                            } else {
                                if (str.contains("://")) {
                                    try {
                                        SkinWrapper.setSkin(p, new URL(str));
                                    } catch (MalformedURLException ignored) {
                                    } catch (ExecutionException | InterruptedException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    SkinWrapper.setSkin(p, str, null);
                                }
                            }
                        } else if (skin instanceof Blueprint print) {
                            try {
                                SkinWrapper.setSkin(p, print);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else if (skin instanceof BufferedImage image){
                           // images not supported by skuishy, but just in case you use another addon like SkImage :)
                           SkinWrapper.setSkin(p, image);
                        }
                    }
                }
            }
        } else{
            String value = this.value.getSingle(e);
            String signature = this.signature.getSingle(e);
            if (value != null && signature != null){
                for (Player p : player.getArray(e)){
                    SkinWrapper.setSkin(p, value, signature);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "set skin of player";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0 || matchedPattern == 1) {
            player = (Expression<Player>) exprs[0];
            skin = (Expression<Object>) exprs[1];
        } else {
            player = (Expression<Player>) exprs[0];
            value = (Expression<String>) exprs[1];
            signature = (Expression<String>) exprs[2];
        }
        return true;
    }
}
