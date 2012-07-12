//*****************************************************************************
// Rivalry (http://code.google.com/p/rivalry)
// Copyright (c) 2011 Rivalry.org
// Admin rivalry@jeffreythompson.net
//
// See the file "LICENSE.txt" for information on usage and redistribution of
// this file, and for a DISCLAIMER OF ALL WARRANTIES.
//*****************************************************************************
package org.rivalry.example.runescape;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an enumeration of items.
 */
public enum Item implements Visitable
{
    /** Ores. */
    COPPER_ORE("Copper ore"), TIN_ORE("Tin ore"), IRON_ORE("Iron ore"), SILVER_ORE("Silver ore"), COAL("Coal"), GOLD_ORE(
            "Gold ore"), MITHRIL_ORE("Mithril ore"), ADAMANTITE_ORE("Adamantite ore"), RUNITE_ORE("Runite ore"),

    /** Bars. */
    BRONZE_BAR("Bronze bar", COPPER_ORE, TIN_ORE), IRON_BAR("Iron bar", IRON_ORE), SILVER_BAR("Silver bar", SILVER_ORE), STEEL_BAR(
            "Steel bar", IRON_ORE, COAL, COAL), GOLD_BAR("Gold bar", GOLD_ORE), MITHRIL_BAR("Mithril bar", MITHRIL_ORE,
            COAL, COAL, COAL, COAL), ADAMANT_BAR("Adamantite bar", ADAMANTITE_ORE, COAL, COAL, COAL, COAL, COAL, COAL), RUNE_BAR(
            "Runite bar", RUNITE_ORE, COAL, COAL, COAL, COAL, COAL, COAL, COAL, COAL),

    /** Armour. */
    BRONZE_FULL_HELM("Bronze full helm", BRONZE_BAR, BRONZE_BAR), BRONZE_PLATEBODY("Bronze platebody", BRONZE_BAR,
            BRONZE_BAR, BRONZE_BAR, BRONZE_BAR, BRONZE_BAR),

    IRON_FULL_HELM("Iron full helm", IRON_BAR, IRON_BAR), IRON_PLATEBODY("Iron platebody", IRON_BAR, IRON_BAR,
            IRON_BAR, IRON_BAR, IRON_BAR),

    STEEL_FULL_HELM("Steel full helm", STEEL_BAR, STEEL_BAR), STEEL_PLATEBODY("Steel platebody", STEEL_BAR, STEEL_BAR,
            STEEL_BAR, STEEL_BAR, STEEL_BAR),

    MITHRIL_FULL_HELM("Mithril full helm", MITHRIL_BAR, MITHRIL_BAR), MITHRIL_PLATEBODY("Mithril platebody",
            MITHRIL_BAR, MITHRIL_BAR, MITHRIL_BAR, MITHRIL_BAR, MITHRIL_BAR),

    ADAMANT_FULL_HELM("Adamant full helm", ADAMANT_BAR, ADAMANT_BAR), ADAMANT_PLATEBODY("Adamant platebody",
            ADAMANT_BAR, ADAMANT_BAR, ADAMANT_BAR, ADAMANT_BAR, ADAMANT_BAR),

    RUNE_FULL_HELM("Rune full helm", RUNE_BAR, RUNE_BAR), RUNE_PLATEBODY("Rune platebody", RUNE_BAR, RUNE_BAR,
            RUNE_BAR, RUNE_BAR, RUNE_BAR),

    /** Nails. */
    BRONZE_NAILS("Bronze nails", BRONZE_BAR), IRON_NAILS("Iron nails", IRON_BAR), STEEL_NAILS("Steel nails", STEEL_BAR), MITHRIL_NAILS(
            "Mithril nails", MITHRIL_BAR), ADAMANT_NAILS("Adamantite nails", ADAMANT_BAR), RUNE_NAILS("Rune nails",
            RUNE_BAR),

    /** Gems. */
    UNCUT_SAPPHIRE("Uncut sapphire"), UNCUT_EMERALD("Uncut emerald"), UNCUT_RUBY("Uncut ruby"), UNCUT_DIAMOND(
            "Uncut diamond"), UNCUT_DRAGONSTONE("Uncut dragonstone"), UNCUT_ONYX("Uncut onyx"),

    SAPPHIRE("Sapphire", Skill.CRAFTING, 50.0, UNCUT_SAPPHIRE), EMERALD("Emerald", Skill.CRAFTING, 67.5, UNCUT_EMERALD), RUBY(
            "Ruby", Skill.CRAFTING, 85.0, UNCUT_RUBY), DIAMOND("Diamond", Skill.CRAFTING, 107.5, UNCUT_DIAMOND), DRAGONSTONE(
            "Dragonstone", Skill.CRAFTING, 137.5, UNCUT_DRAGONSTONE), ONYX("Onyx", Skill.CRAFTING, 167.5, UNCUT_ONYX),

    /** Amulets. */
    GOLD_AMULET("Gold amulet", GOLD_BAR), SAPPHIRE_AMULET("Sapphire amulet", Skill.CRAFTING, 40.0, GOLD_BAR, SAPPHIRE), EMERALD_AMULET(
            "Emerald amulet", Skill.CRAFTING, 55.0, GOLD_BAR, EMERALD), RUBY_AMULET("Ruby amulet", Skill.CRAFTING,
            70.0, GOLD_BAR, RUBY), DIAMOND_AMULET("Diamond amulet", Skill.CRAFTING, 85.0, GOLD_BAR, DIAMOND), DRAGONSTONE_AMULET(
            "Dragonstone ammy", Skill.CRAFTING, 100.0, GOLD_BAR, DRAGONSTONE), ONYX_AMULET("Onyx amulet",
            Skill.CRAFTING, 115.0, GOLD_BAR, ONYX),

    /** Bracelets. */
    GOLD_BRACELET("Gold bracelet", GOLD_BAR), SAPPHIRE_BRACELET("Sapphire bracelet", Skill.CRAFTING, 40.0, GOLD_BAR,
            SAPPHIRE), EMERALD_BRACELET("Emerald bracelet", Skill.CRAFTING, 55.0, GOLD_BAR, EMERALD), RUBY_BRACELET(
            "Ruby bracelet", Skill.CRAFTING, 70.0, GOLD_BAR, RUBY), DIAMOND_BRACELET("Diamond bracelet",
            Skill.CRAFTING, 85.0, GOLD_BAR, DIAMOND), DRAGONSTONE_BRACELET("Dragon bracelet", Skill.CRAFTING, 100.0,
            GOLD_BAR, DRAGONSTONE), ONYX_BRACELET("Onyx bracelet", Skill.CRAFTING, 115.0, GOLD_BAR, ONYX),

    /** Necklaces. */
    GOLD_NECKLACE("Gold necklace", GOLD_BAR), SAPPHIRE_NECKLACE("Sapphire necklace", Skill.CRAFTING, 40.0, GOLD_BAR,
            SAPPHIRE), EMERALD_NECKLACE("Emerald necklace", Skill.CRAFTING, 55.0, GOLD_BAR, EMERALD), RUBY_NECKLACE(
            "Ruby necklace", Skill.CRAFTING, 70.0, GOLD_BAR, RUBY), DIAMOND_NECKLACE("Diamond necklace",
            Skill.CRAFTING, 85.0, GOLD_BAR, DIAMOND), DRAGONSTONE_NECKLACE("Dragon necklace", Skill.CRAFTING, 100.0,
            GOLD_BAR, DRAGONSTONE), ONYX_NECKLACE("Onyx necklace", Skill.CRAFTING, 115.0, GOLD_BAR, ONYX),

    /** Rings. */
    GOLD_RING("Gold ring", GOLD_BAR), SAPPHIRE_RING("Sapphire ring", Skill.CRAFTING, 40.0, GOLD_BAR, SAPPHIRE), EMERALD_RING(
            "Emerald ring", Skill.CRAFTING, 55.0, GOLD_BAR, EMERALD), RUBY_RING("Ruby ring", Skill.CRAFTING, 70.0,
            GOLD_BAR, RUBY), DIAMOND_RING("Diamond ring", Skill.CRAFTING, 85.0, GOLD_BAR, DIAMOND), DRAGONSTONE_RING(
            "Dragonstone ring", Skill.CRAFTING, 100.0, GOLD_BAR, DRAGONSTONE), ONYX_RING("Onyx ring", Skill.CRAFTING,
            115.0, GOLD_BAR, ONYX),

    /** Runes. */
    RUNE_ESSENCE("Rune essence"), PURE_ESSENCE("Pure essence"),

    AIR_RUNE("Air rune", RUNE_ESSENCE), MIND_RUNE("Mind rune", RUNE_ESSENCE), WATER_RUNE("Water rune", RUNE_ESSENCE), EARTH_RUNE(
            "Earth rune", RUNE_ESSENCE), FIRE_RUNE("Fire rune", RUNE_ESSENCE), BODY_RUNE("Body rune", RUNE_ESSENCE),

    COSMIC_RUNE("Cosmic rune", PURE_ESSENCE), CHAOS_RUNE("Chaos rune", PURE_ESSENCE), ASTRAL_RUNE("Astral rune",
            PURE_ESSENCE), NATURE_RUNE("Nature rune", PURE_ESSENCE), LAW_RUNE("Law rune", PURE_ESSENCE), DEATH_RUNE(
            "Death rune", PURE_ESSENCE), ARMADYL_RUNE("Armadyl rune", PURE_ESSENCE), BLOOD_RUNE("Blood rune",
            PURE_ESSENCE), SOUL_RUNE("Soul rune", PURE_ESSENCE),

    EARTH_RUNE_10("Earth rune x 10", false),

    EARTH_RUNE_15("Earth rune x 15", false), WATER_RUNE_15("Water rune x 15", false),

    EARTH_RUNE_20("Earth rune x 20", false), FIRE_RUNE_20("Fire rune x 20", false),

    /** Spells. */
    ENCHANT_1_JEWELRY("Enchant Level 1 Jewelry", false, Skill.MAGIC, 17.5, WATER_RUNE, COSMIC_RUNE), ENCHANT_2_JEWELRY(
            "Enchant Level 2 Jewelry", false, Skill.MAGIC, 37.0, AIR_RUNE, AIR_RUNE, AIR_RUNE, COSMIC_RUNE), ENCHANT_3_JEWELRY(
            "Enchant Level 3 Jewelry", false, Skill.MAGIC, 59.0, FIRE_RUNE, FIRE_RUNE, FIRE_RUNE, FIRE_RUNE, FIRE_RUNE,
            COSMIC_RUNE), ENCHANT_4_JEWELRY("Enchant Level 4 Jewelry", false, Skill.MAGIC, 67.0, EARTH_RUNE_10,
            COSMIC_RUNE), ENCHANT_5_JEWELRY("Enchant Level 5 Jewelry", false, Skill.MAGIC, 78.0, EARTH_RUNE_15,
            WATER_RUNE_15, COSMIC_RUNE), ENCHANT_6_JEWELRY("Enchant Level 6 Jewelry", false, Skill.MAGIC, 97.0,
            EARTH_RUNE_20, FIRE_RUNE_20, COSMIC_RUNE),

    /** Enchanted amulets. */
    // FIXME: add enchanted amulets

    /** Enchanted bracelets. */
    // FIXME: add enchanted bracelets

    /** Enchanted necklaces. */
    // FIXME: add enchanted necklaces

    /** Enchanted rings. */
    RING_OF_RECOIL("Ring of recoil", SAPPHIRE_RING, ENCHANT_1_JEWELRY), RING_OF_DUELLING("Ring of duelling",
            EMERALD_RING, ENCHANT_2_JEWELRY), RING_OF_FORGING("Ring of forging", RUBY_RING, ENCHANT_3_JEWELRY), RING_OF_LIFE(
            "Ring of life", DIAMOND_RING, ENCHANT_4_JEWELRY), RING_OF_WEALTH("Ring of wealth", DRAGONSTONE_RING,
            ENCHANT_5_JEWELRY), RING_OF_STONE("Ring of stone", ONYX_RING, ENCHANT_6_JEWELRY),

    /** Leather. */
    COWHIDE("Cowhide"), THREAD("Thread"), LEATHER("Leather", COWHIDE), HARD_LEATHER("Hard leather", COWHIDE), LEATHER_BODY(
            "Leather body", LEATHER, THREAD), HARDLEATHER_BODY("Hardleather body", HARD_LEATHER, THREAD), COIF("Coif",
            LEATHER, THREAD),

    /** Food. */
    WATER("Water", false), POT_OF_FLOUR("Pot of flour"), BREAD_DOUGH("Bread dough", POT_OF_FLOUR, WATER), PASTRY_DOUGH(
            "Pastry dough", POT_OF_FLOUR, WATER), PIE_DISH("Pie dish"), PIE_SHELL("Pie shell", PASTRY_DOUGH, PIE_DISH), RAW_MEAT(
            "Raw meat"), COOKED_MEAT("Cooked meat", Skill.COOKING, 30.0, RAW_MEAT), UNCOOKED_MEAT_PIE(
            "Uncooked meat pie", PIE_SHELL, COOKED_MEAT), MEAT_PIE("Meat pie", Skill.COOKING, 110.0, UNCOOKED_MEAT_PIE), COOKING_APPLE(
            "Cooking apple"), UNCOOKED_APPLE_PIE("Uncooked apple pie", PIE_SHELL, COOKING_APPLE), APPLE_PIE(
            "Apple pie", UNCOOKED_APPLE_PIE), REDBERRIES("Redberries"), UNCOOKED_REDBERRY_PIE("Uncooked berry pie",
            PIE_SHELL, REDBERRIES), REDBERRY_PIE("Redberry pie", UNCOOKED_REDBERRY_PIE),

    /** Money. */
    GP_100("100 gp", false), GP_250("250 gp", false), GP_500("500 gp", false), GP_1500("1500 gp", false),

    /** Wood. */
    LOGS("Logs"), ACHEY_LOGS("Achey tree logs"), OAK_LOGS("Oak logs"), WILLOW_LOGS("Willow logs"), TEAK_LOGS(
            "Teak logs"), MAPLE_LOGS("Maple logs"), MAHOGANY_LOGS("Mahogany logs"), YEW_LOGS("Yew logs"),

    PLANK("Plank", LOGS, GP_100), OAK_PLANK("Oak plank", OAK_LOGS, GP_250), TEAK_PLANK("Teak plank", TEAK_LOGS, GP_500), MAHOGANY_PLANK(
            "Mahogany plank", MAHOGANY_LOGS, GP_1500),

    FLAX("Flax"), BOW_STRING("Bow string", FLAX),

    SEAWEED("Seaweed"), SODA_ASH("Soda ash", SEAWEED), BUCKET_OF_SAND("Bucket of sand"), MOLTEN_GLASS("Molten glass",
            BUCKET_OF_SAND, SODA_ASH), FISHBOWL("Fishbowl", MOLTEN_GLASS), LANTERN_LENS("Lantern lens", MOLTEN_GLASS), UNPOWERED_ORB(
            "Unpowered orb", MOLTEN_GLASS), VIAL("Vial", MOLTEN_GLASS),

    UNSTRUNG_SHORTBOW("Shortbow (u)", LOGS), SHORTBOW("Shortbow", UNSTRUNG_SHORTBOW, BOW_STRING), UNSTRUNG_OAK_SHORTBOW(
            "Oak shortbow (u)", OAK_LOGS), OAK_SHORTBOW("Oak shortbow", UNSTRUNG_OAK_SHORTBOW, BOW_STRING), UNSTRUNG_WILLOW_SHORTBOW(
            "Willow shortbow (u)", WILLOW_LOGS), WILLOW_SHORTBOW("Willow shortbow", UNSTRUNG_WILLOW_SHORTBOW,
            BOW_STRING),

    UNSTRUNG_LONGBOW("Longbow (u)", LOGS), LONGBOW("Longbow", UNSTRUNG_LONGBOW, BOW_STRING), UNSTRUNG_OAK_LONGBOW(
            "Oak longbow (u)", OAK_LOGS), OAK_LONGBOW("Oak longbow", UNSTRUNG_OAK_LONGBOW, BOW_STRING), UNSTRUNG_WILLOW_LONGBOW(
            "Willow longbow (u)", WILLOW_LOGS), WILLOW_LONGBOW("Willow longbow", UNSTRUNG_WILLOW_LONGBOW, BOW_STRING),

    /** Urns. */
    CLAY("Clay"), SOFT_CLAY("Soft clay", CLAY, WATER),

    // COOKING_URN("Cooking urn (nr)", SOFT_CLAY, SOFT_CLAY), FISHING_URN("Fishing urn (nr)", SOFT_CLAY, SOFT_CLAY),
    // MINING_URN(
    // "Mining urn (nr)", SOFT_CLAY, SOFT_CLAY), SMELTING_URN("Smelting urn (nr)", SOFT_CLAY, SOFT_CLAY),
    // WOODCUTTING_URN(
    // "Woodcutting urn (nr)", SOFT_CLAY, SOFT_CLAY),
    //
    // STRONG_COOKING_URN("Strong cooking urn (nr)", SOFT_CLAY, SOFT_CLAY),
    // STRONG_FISHING_URN("Strong fishing urn (nr)",
    // SOFT_CLAY, SOFT_CLAY), STRONG_MINING_URN("Strong mining urn (nr)", SOFT_CLAY, SOFT_CLAY), STRONG_SMELTING_URN(
    // "Strong smelting urn (nr)", SOFT_CLAY, SOFT_CLAY), STRONG_WOODCUTTING_URN("Strong woodcutting urn (nr)",
    // SOFT_CLAY, SOFT_CLAY),
    //
    // DECORATED_COOKING_URN("Decorated cooking urn (nr)", SOFT_CLAY, SOFT_CLAY), DECORATED_FISHING_URN(
    // "Decorated fishing urn (nr)", SOFT_CLAY, SOFT_CLAY), DECORATED_MINING_URN("Decorated mining urn (nr)",
    // SOFT_CLAY, SOFT_CLAY), DECORATED_SMELTING_URN("Decorated smelting urn (nr)", SOFT_CLAY, SOFT_CLAY),
    // DECORATED_WOODCUTTING_URN(
    // "Decorated woodcutting urn (nr)", SOFT_CLAY, SOFT_CLAY)
    ;

    static
    {
        System.out.println("Item count = " + Item.values().length);
    }

    /**
     * @param name Name.
     * 
     * @return the item with the given name.
     */
    public static Item valueOfName(final String name)
    {
        Item answer = null;

        final Item[] values = values();
        final int size = values.length;

        for (int i = 0; (answer == null) && (i < size); i++)
        {
            final Item item = values[i];

            if (item.getName().equals(name))
            {
                answer = item;
            }
        }

        return answer;
    }

    /**
     * @param name Name.
     * 
     * @return the item with the given name.
     */
    public static Item valueOfNameIgnoreCase(final String name)
    {
        Item answer = null;

        final Item[] values = values();
        final int size = values.length;

        for (int i = 0; (answer == null) && (i < size); i++)
        {
            final Item item = values[i];

            if (item.getName().equalsIgnoreCase(name))
            {
                answer = item;
            }
        }

        return answer;
    }

    /** Name. */
    private final String _name;

    /** Components. */
    private final List<Item> _components = new ArrayList<Item>();

    /** Skill used to create this. */
    private final Skill _skill;

    /** Skill experience points earned creating this. */
    private final Double _skillXp;

    /** Flag indicating if this item is in the lookup database. */
    private final boolean _isLookupable;

    /**
     * Construct this object with the given parameter.
     * 
     * @param name Name.
     * @param components Components of this item.
     */
    private Item(final String name, final boolean isLookupable, final Item... components)
    {
        this(name, isLookupable, null, null, components);
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param name Name.
     * @param skill Skill.
     * @param skillXp Skill experience points.
     * @param components Components of this item.
     */
    private Item(final String name, final boolean isLookupable, final Skill skill, final Double skillXp,
            final Item... components)
    {
        _name = name;
        _isLookupable = isLookupable;
        _skill = skill;
        _skillXp = skillXp;

        for (final Item component : components)
        {
            _components.add(component);
        }
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param name Name.
     * @param components Components of this item.
     */
    private Item(final String name, final Item... components)
    {
        this(name, null, null, components);
    }

    /**
     * Construct this object with the given parameter.
     * 
     * @param name Name.
     * @param skill Skill.
     * @param skillXp Skill experience points.
     * @param components Components of this item.
     */
    private Item(final String name, final Skill skill, final Double skillXp, final Item... components)
    {
        this(name, true, skill, skillXp, components);
    }

    @Override
    public void acceptBreadthFirst(final Visitor visitor)
    {
        visitor.visit(this);

        acceptBreadthFirst(this, visitor);
    }

    @Override
    public void acceptDepthFirst(final Visitor visitor)
    {
        visitor.visit(this);

        for (final Item item : _components)
        {
            item.acceptDepthFirst(visitor);
        }
    }

    /**
     * @param grandExchange Grand exchange.
     * 
     * @return cost.
     */
    public Integer computeBaseCost(final GrandExchange grandExchange)
    {
        final BaseCostVisitor visitor = new BaseCostVisitor(grandExchange);

        acceptDepthFirst(visitor);

        return visitor.getCost();
    }

    /**
     * @param grandExchange Grand exchange.
     * 
     * @return cost.
     */
    public Integer computeCost(final GrandExchange grandExchange)
    {
        Integer answer = 0;

        if (isLeaf())
        {
            answer = computeValue(grandExchange);
        }
        else
        {
            for (final Item component : _components)
            {
                final Integer value = grandExchange.getValue(component);

                if (value == null)
                {
                    System.out.println("ERROR: Missing value for item " + component.getName());
                }
                else
                {
                    answer += value;
                }
            }
        }

        return answer;
    }

    /**
     * @param grandExchange Grand exchange.
     * 
     * @return cost.
     */
    // public Integer computeBaseCost00(final GrandExchange grandExchange)
    // {
    // Integer answer = 0;
    //
    // if (isLeaf())
    // {
    // answer = computeValue(grandExchange);
    // }
    // else
    // {
    // for (final Item component : _components)
    // {
    // answer += component.computeBaseCost(grandExchange);
    // }
    // }
    //
    // return answer;
    // }

    /**
     * @return the name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @return the skill
     */
    public Skill getSkill()
    {
        return _skill;
    }

    /**
     * @return the skillXp
     */
    public Double getSkillXp()
    {
        return _skillXp;
    }

    /**
     * @return true if this has no components.
     */
    public boolean isLeaf()
    {
        return _components.isEmpty();
    }

    /**
     * @return the isLookupable
     */
    public boolean isLookupable()
    {
        return _isLookupable;
    }

    /**
     * Accept the given visitor in a breadth first manner.
     * 
     * @param parent Parent.
     * @param visitor Visitor.
     */
    private void acceptBreadthFirst(final Item parent, final Visitor visitor)
    {
        for (final Item item : _components)
        {
            visitor.visit(item);
        }

        for (final Item item : _components)
        {
            item.acceptBreadthFirst(this, visitor);
        }
    }

    /**
     * @param grandExchange Grand exchange.
     * 
     * @return non-null value.
     */
    private Integer computeValue(final GrandExchange grandExchange)
    {
        final Integer value = grandExchange.getValue(this);

        return (value == null ? 0 : value);
    }
}
