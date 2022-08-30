package com.ranafangirl.froggyfurniture.loot;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.item.Item;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class FroggyHatFromAbandonedMineshaftModifier extends LootModifier {
    private final Item addition;
    
    protected FroggyHatFromAbandonedMineshaftModifier(final ILootCondition[] conditionsIn, final Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }
    
    @Nonnull
    protected List<ItemStack> doApply(final List<ItemStack> generatedLoot, final LootContext context) {
        if (context.getRandom().nextFloat() > 0.005f) {
            generatedLoot.add(new ItemStack((IItemProvider)this.addition, 1));
        }
        return generatedLoot;
    }
    
	public static class Serializer extends GlobalLootModifierSerializer<FroggyHatFromAbandonedMineshaftModifier> {
		@Override
		public FroggyHatFromAbandonedMineshaftModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
			Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(JSONUtils.getAsString(object, "addition")));
			return new FroggyHatFromAbandonedMineshaftModifier(conditionsIn, addition);
		}

		@Override
		public JsonObject write(FroggyHatFromAbandonedMineshaftModifier instance) {
			JsonObject json = makeConditions(instance.conditions);
			json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
			return json;
		}
	}
}