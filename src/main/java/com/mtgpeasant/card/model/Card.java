package com.mtgpeasant.card.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "card")
public class Card implements Serializable {

    /**
     * A unique id for this card.
     */
    @Id
    private String id;

    /**
     * The card name. For split, double-faced and flip cards, just the name of one side of the card. Basically each ‘sub-card’
     * has its own record.
     */
    private String name;

    /**
     * Only used for split, flip and dual cards. Will contain all the names on this card, front or back.
     */
    private List<String> names;

    /**
     * The mana cost of this card. Consists of one or more mana symbols.
     */
    private String manaCost;

    /**
     * Converted mana cost.
     */
    private Float cmc;

    /**
     * The card colors. Usually this is derived from the casting cost, but some cards are special (like the back of dual sided
     * cards and Ghostfire).
     */
    private List<String> colors;

    /**
     * The card colors by color code. [“Red”, “Blue”] becomes [“R”, “U”].
     */
    private List<String> colorIdentity;

    /**
     * The card type. This is the type you would see on the card if printed today.
     */
    private String type;

    /**
     * The supertypes of the card. These appear to the far left of the card type. Example values: Basic, Legendary, Snow, World,
     * Ongoing
     */
    private List<String> supertypes;

    /**
     * The types of the card. These appear to the left of the dash in a card type. Example values: Instant, Sorcery, Artifact,
     * Creature, Enchantment, Land, Planeswalker.
     */
    private List<String> types;

    /**
     * The subtypes of the card. These appear to the right of the dash in a card type. Usually each word is its own subtype.
     * Example values: Trap, Arcane, Equipment, Aura, Human, Rat, Squirrel, etc.
     */
    private List<String> subtypes;

    /**
     * The rarity of the card. Examples: Common, Uncommon, Rare, Mythic Rare, Special, Basic Land
     */
    private String rarity;

    /**
     * The set the card belongs to (set code).
     */
    private String set;

    /**
     * The set the card belongs to.
     */
    private String setName;

    /**
     * The oracle text of the card. May contain mana symbols and other symbols.
     */
    private String text;

    /**
     * The artist of the card. This may not match what is on the card as MTGJSON corrects many card misprints.
     */
    private String artist;

    /**
     * The card number. This is printed at the bottom-center of the card in small text. This is a string, not an integer, because
     * some cards have letters in their numbers.
     */
    private String number;

    /**
     * The power of the card. This is only present for creatures. This is a string, not an integer, because some cards have
     * powers like: “1+*”
     */
    private String power;

    /**
     * The toughness of the card. This is only present for creatures. This is a string, not an integer, because some cards have
     * toughness like: “1+*”
     */
    private String toughness;

    /**
     * The loyalty of the card. This is only present for planeswalkers.
     */
    private String loyalty;

    /**
     * The multiverseid of the card on Wizard’s Gatherer web page. Cards from sets that do not exist on Gatherer will NOT have a
     * multiverseid. Sets not on Gatherer are: ATH, ITP, DKM, RQS, DPA and all sets with a 4 letter code that starts with a
     * lowercase ‘p’.
     */
    private Integer multiverseid;

    /**
     * The image url for a card. Only exists if the card has a multiverse id.
     */
    private String imageUrl;

    /**
     * The card layout. Possible values: normal, split, flip, double-faced, token, plane, scheme, phenomenon, leveler, vanguard,
     * aftermath. See @{@link Layout}.
     */
    private Layout layout;

    /**
     * The legality of the card for a given format, such as Legal, Banned or Restricted. See @{@link Legalities}.
     */
    private List<Legalities> legalities;

    /**
     * The rulings for the card. An array of @{@link Rulings}.
     */
    private List<Rulings> rulings;

    /**
     * Foreign language names for the card, if this card in this set was printed in another language. An array of
     *
     * {@link ForeignNames} . Not available for all sets.
     */
    private List<ForeignNames> foreignNames;

    /**
     * The original text on the card at the time it was printed. This field is not available for promo cards.
     */
    private String originalText;

    /**
     * The flavor text of the card.
     */
    private String flavor;

    /**
     * If a card has alternate art (for example, 4 different Forests, or the 2 Brothers Yamazaki) then each other variation’s
     * multiverseid will be listed here, NOT including the current card’s multiverseid.
     */
    private List<String> variations;

    /**
     * The watermark on the card. Note: Split cards don’t currently have this field set.
     */
    private String watermark;

    /**
     *
     */
    private String border;

    /**
     * If this card was a timeshifted card in the set.
     */
    private Boolean timeshifted;

    /**
     * Maximum hand size modifier. Only exists for Vanguard cards.
     */
    private String hand;

    /**
     * Starting life total modifier. Only exists for Vanguard cards.
     */
    private String life;

    /**
     * Set to true if this card is reserved by Wizards Official Reprint Policy
     */
    private Boolean reserved;

    /**
     * The date this card was released. This is only set for promo cards. The date may not be accurate to an exact day and month,
     * thus only a partial date may be set (YYYY-MM-DD or YYYY-MM or YYYY). Some promo cards do not have a known release date.
     */
    private String releaseDate;

    /**
     * Set to true if this card was only released as part of a core box set. These are technically part of the core sets and are
     * tournament legal despite not being available in boosters.
     */
    private Boolean starter;

    /**
     * The sets that this card was printed in, expressed as an array of set codes.
     */
    private List<String> printings;

    /**
     * The original type on the card at the time it was printed. This field is not available for promo cards.
     */
    private String originalType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public Float getCmc() {
        return cmc;
    }

    public void setCmc(Float cmc) {
        this.cmc = cmc;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getColorIdentity() {
        return colorIdentity;
    }

    public void setColorIdentity(List<String> colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(List<String> supertypes) {
        this.supertypes = supertypes;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<String> subtypes) {
        this.subtypes = subtypes;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public Integer getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Integer multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public List<Legalities> getLegalities() {
        return legalities;
    }

    public void setLegalities(List<Legalities> legalities) {
        this.legalities = legalities;
    }

    public List<Rulings> getRulings() {
        return rulings;
    }

    public void setRulings(List<Rulings> rulings) {
        this.rulings = rulings;
    }

    public List<ForeignNames> getForeignNames() {
        return foreignNames;
    }

    public void setForeignNames(List<ForeignNames> foreignNames) {
        this.foreignNames = foreignNames;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public List<String> getVariations() {
        return variations;
    }

    public void setVariations(List<String> variations) {
        this.variations = variations;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public Boolean getTimeshifted() {
        return timeshifted;
    }

    public void setTimeshifted(Boolean timeshifted) {
        this.timeshifted = timeshifted;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getStarter() {
        return starter;
    }

    public void setStarter(Boolean starter) {
        this.starter = starter;
    }

    public List<String> getPrintings() {
        return printings;
    }

    public void setPrintings(List<String> printings) {
        this.printings = printings;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }
}