package com.mtgpeasant.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgpeasant.card.model.Lang;
import com.mtgpeasant.card.model.Rarity;
import com.mtgpeasant.card.model.magicthegatheringIo.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
public class CardControllerTest {

    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CardService cardService;
    @InjectMocks
    @Autowired
    private CardController cardController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getCards_documentation() throws Exception {

        Mockito.when(cardService.getCards(0, 10, "Archangel Avacyn", "SOI"))
                .thenReturn(Collections.singletonList(objectMapper.readValue(ResourceUtils.getFile("classpath:archangel_avacyn.json"), Card.class)));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/cards?page={page}&size={size}&name={name}&set={set}",
                "0", "10", "Archangel Avacyn", "SOI"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("cards",
                        requestParameters(
                                parameterWithName("page").description("The result page. Default is 0.")
                                        .attributes(key("type").value("number"))
                                        .attributes(key("required").value(false)),
                                parameterWithName("size").description("The result size. Default is 100.")
                                        .attributes(key("type").value("number"))
                                        .attributes(key("required").value(false)),
                                parameterWithName("name").description("Filter reseach by card name.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false)),
                                parameterWithName("set").description("Filter reseach by card set.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false))),
                        responseFields(
                                fieldWithPath("cards[]id").description("A unique id for this card. It is made up by doing an SHA1 hash of setCode + cardName + cardImageName"),
                                fieldWithPath("cards[]name").description("The card name. For split, double-faced and flip cards, just the name of one side of the card. Basically each ‘sub-card’ has its own record."),
                                fieldWithPath("cards[]names").description("Only used for split, flip and dual cards. Will contain all the names on this card, front or back"),
                                fieldWithPath("cards[]manaCost").description("The mana cost of this card. Consists of one or more mana symbols."),
                                fieldWithPath("cards[]cmc").description("Converted mana cost. Always a number"),
                                fieldWithPath("cards[]colors").description("The card colors. Usually this is derived from the casting cost, but some cards are special (like the back of dual sided cards and Ghostfire)."),
                                fieldWithPath("cards[]colorIdentity").description("The card colors by color code. [“Red”, “Blue”] becomes [“R”, “U”]"),
                                fieldWithPath("cards[]type").description("The card type. This is the type you would see on the card if printed today. Note: The dash is a UTF8 ‘long dash’ as per the MTG rules"),
                                fieldWithPath("cards[]supertypes").description("The supertypes of the card. These appear to the far left of the card type. Example values: Basic, Legendary, Snow, World, Ongoing"),
                                fieldWithPath("cards[]types").description("The types of the card. These appear to the left of the dash in a card type. Example values: Instant, Sorcery, Artifact, Creature, Enchantment, Land, Planeswalker"),
                                fieldWithPath("cards[]subtypes").description("The subtypes of the card. These appear to the right of the dash in a card type. Usually each word is its own subtype. Example values: Trap, Arcane, Equipment, Aura, Human, Rat, Squirrel, etc."),
                                fieldWithPath("cards[]rarity").description("The rarity of the card. Examples: Common, Uncommon, Rare, Mythic Rare, Special, Basic Land"),
                                fieldWithPath("cards[]set").description("The set the card belongs to (set code)."),
                                fieldWithPath("cards[]setName").description("The set the card belongs to."),
                                fieldWithPath("cards[]text").description("The oracle text of the card. May contain mana symbols and other symbols."),
                                fieldWithPath("cards[]artist").description("The artist of the card. This may not match what is on the card as MTGJSON corrects many card misprints."),
                                fieldWithPath("cards[]number").description("The card number. This is printed at the bottom-center of the card in small text. This is a string, not an integer, because some cards have letters in their numbers."),
                                fieldWithPath("cards[]power").description("The power of the card. This is only present for creatures. This is a string, not an integer, because some cards have powers like: “1+*”"),
                                fieldWithPath("cards[]toughness").description("The toughness of the card. This is only present for creatures. This is a string, not an integer, because some cards have toughness like: “1+*”"),
                                fieldWithPath("cards[]loyalty").description("The loyalty of the card. This is only present for planeswalkers."),
                                fieldWithPath("cards[]multiverseid").description("The multiverseid of the card on Wizard’s Gatherer web page. Cards from sets that do not exist on Gatherer will NOT have a multiverseid. Sets not on Gatherer are: ATH, ITP, DKM, RQS, DPA and all sets with a 4 letter code that starts with a lowercase ‘p’."),
                                fieldWithPath("cards[]imageUrl").description("The image url for a card. Only exists if the card has a multiverse id."),
                                fieldWithPath("cards[]layout").description("The card layout. Possible values: normal, split, flip, double-faced, token, plane, scheme, phenomenon, leveler, vanguard, aftermath"),
                                fieldWithPath("cards[]legalities").description("Which formats this card is legal, restricted or banned in. An array of objects, each object having ‘format’ and ‘legality’."),
                                fieldWithPath("cards[]rulings").description("The rulings for the card. An array of objects, each object having ‘date’ and ‘text’ keys."),
                                fieldWithPath("cards[]rulings[]date").description("The rulings date for the card. "),
                                fieldWithPath("cards[]rulings[]text").description("The rulings text for the card."),
                                fieldWithPath("cards[]foreignNames").description("Foreign language names for the card, if this card in this set was printed in another language. An array of objects, each object having ‘language’, ‘name’ and ‘multiverseid’ keys. Not available for all sets."),
                                fieldWithPath("cards[]foreignNames[]flavor").description("Foreign flavor for the card."),
                                fieldWithPath("cards[]foreignNames[]multiverseid").description("The multiverseid for the foreign card."),
                                fieldWithPath("cards[]foreignNames[]imageUrl").description("The image url for the foreign card."),
                                fieldWithPath("cards[]foreignNames[]name").description("The foreign name for the card."),
                                fieldWithPath("cards[]foreignNames[]language").description("The foreign language for the card."),
                                fieldWithPath("cards[]foreignNames[]text").description("The foreign text for the card."),
                                fieldWithPath("cards[]originalText").description("The original text on the card at the time it was printed. This field is not available for promo cards."),
                                fieldWithPath("cards[]flavor").description("The flavor text of the card."),
                                fieldWithPath("cards[]variations").description("If a card has alternate art (for example, 4 different Forests, or the 2 Brothers Yamazaki) then each other variation’s multiverseid will be listed here, NOT including the current card’s multiverseid."),
                                fieldWithPath("cards[]watermark").description("The watermark on the card. Note: Split cards don’t currently have this field set, despite having a watermark on each side of the split card."),
                                fieldWithPath("cards[]border").description("If the border for this specific card is DIFFERENT than the border specified in the top level set JSON, then it will be specified here. (Example: Unglued has silver borders, except for the lands which are black bordered)"),
                                fieldWithPath("cards[]timeshifted").description("If this card was a timeshifted card in the set."),
                                fieldWithPath("cards[]hand").description("Maximum hand size modifier. Only exists for Vanguard cards."),
                                fieldWithPath("cards[]life").description("Starting life total modifier. Only exists for Vanguard cards."),
                                fieldWithPath("cards[]reserved").description("Set to true if this card is reserved by Wizards Official Reprint Policy"),
                                fieldWithPath("cards[]releaseDate").description("The date this card was released. This is only set for promo cards. The date may not be accurate to an exact day and month, thus only a partial date may be set (YYYY-MM-DD or YYYY-MM or YYYY). Some promo cards do not have a known release date."),
                                fieldWithPath("cards[]starter").description("Set to true if this card was only released as part of a core box set. These are technically part of the core sets and are tournament legal despite not being available in boosters."),
                                fieldWithPath("cards[]printings").description("The sets that this card was printed in, expressed as an array of set codes."),
                                fieldWithPath("cards[]originalType").description("The original type on the card at the time it was printed. This field is not available for promo cards."),
                                fieldWithPath("cards[]source").description("For promo cards, this is where this card was originally obtained. For box sets that are theme decks, this is which theme deck the card is from."))));
    }

    @Test
    public void autoComplete_documentation() throws Exception {

        List<String> names = new ArrayList<>();
        names.add("Sortimage vampire");
        names.add("Sortir de terre");

        Mockito.when(cardService.getCardsNames("sorti", Lang.fr)).thenReturn(names);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/cards/names?lang={lang}&partialName={partialName}", "fr", "sorti"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"names\":[\"Sortimage vampire\",\"Sortir de terre\"]}"))
                .andDo(document("cards-names",
                        requestParameters(
                                parameterWithName("lang").optional().description("The lang to get cards names.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false)),
                                parameterWithName("partialName").description("The partial name to get cards names.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(true))),
                        responseFields(
                                fieldWithPath("names").type("Array").description("The name list found for the given lang and partial name."))));
    }

    @Test
    public void getCardRarity_documentation() throws Exception {

        Mockito.when(cardService.getCardRarity("Rancor", Lang.en)).thenReturn(Rarity.COMMON);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/cards/{cardName}/rarity?lang={lang}", "Rancor", "en"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"rarity\":\"COMMON\"}"))
                .andDo(document("cards-rarity",
                        pathParameters(
                                parameterWithName("cardName").description("The card name which you want the rarity.")),
                        requestParameters(
                                parameterWithName("lang").description("The lang to find card which you want the rarity.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false))),
                        responseFields(
                                fieldWithPath("rarity").type("object").description("The card lowest rarity. Possible values are COMMON, UNCOMMON, RARE or MYTHIC."))));
    }
}
