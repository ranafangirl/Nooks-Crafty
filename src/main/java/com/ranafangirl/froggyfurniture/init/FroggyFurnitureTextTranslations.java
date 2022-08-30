package com.ranafangirl.froggyfurniture.init;

import net.minecraft.util.text.TranslationTextComponent;

public class FroggyFurnitureTextTranslations {
    public static TranslationTextComponent FROGGY_HAT_DESC = register("item.froggyfurniture.froggy_hat_desc");

    private static TranslationTextComponent register(final String translation) {
        return new TranslationTextComponent(translation);
    }
}