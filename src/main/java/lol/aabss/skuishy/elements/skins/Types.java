package lol.aabss.skuishy.elements.skins;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import lol.aabss.skuishy.other.blueprints.Blueprint;
import lol.aabss.skuishy.other.mineskin.Variant;
import org.jetbrains.annotations.NotNull;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Blueprint.class, "blueprint")
                .user("blueprints?")
                .name("Blueprint - Blueprint")
                .description("Represents a blueprint.")
                .since("2.6")
                .parser(new Parser<>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Blueprint blueprint) {
                        return "blueprint";
                    }

                    @Override
                    public @NotNull String toString(Blueprint blueprint, int flags) {
                        return toVariableNameString(blueprint);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(Variant.class, "skinmodel")
                .user("skin ?models?")
                .name("Skin - Skin Model")
                .description("Represents a skin model.")
                .since("2.6")
                .parser(new Parser<>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Variant variant) {
                        return variant.name().toLowerCase();
                    }

                    @Override
                    public @NotNull String toString(Variant variant, int flags) {
                        return toVariableNameString(variant);
                    }
                })
        );
    }

}
