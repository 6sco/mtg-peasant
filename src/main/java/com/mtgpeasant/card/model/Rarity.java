package com.mtgpeasant.card.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum Rarity {

    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    MYTHIC("Mythic Rare"),
    SPECIAL("Special"),
    BASIC_LAND("Basic Land");

    private String value;

    public static Rarity fromStringRarity(String value) {
        for (Rarity rarity : Rarity.values()) {
            if (rarity.value.equals(value)) {
                return rarity;
            }
        }
        return null;
    }

    public Boolean isLowestThan(Rarity rarityToCompare) {

        switch (rarityToCompare) {
            case COMMON:
            case UNCOMMON:
                if (this.equals(Rarity.COMMON)) {
                    return true;
                }
            case RARE:
                if (this.equals(Rarity.COMMON) || this.equals(Rarity.UNCOMMON)) {
                    return true;
                }
            case MYTHIC:
                if (this.equals(Rarity.COMMON) || this.equals(Rarity.UNCOMMON) || this.equals(Rarity.RARE)) {
                    return true;
                }
            default:
                return false;
        }
    }
}
