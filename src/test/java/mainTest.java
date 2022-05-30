import reusableData.Card;
import reusableData.DeckofCardsData;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Test one endpoint of Deck of Cards API")
public class mainTest {

    DeckofCardsData docsData = new DeckofCardsData();
    int count = 2;

    @DisplayName("Should send request to server")
    @BeforeAll
    // It should be executed before all the Test run
    public static void setup() {
        RestAssured.baseURI = "https://deckofcardsapi.com";
    }


    @Test
    @Order(1)
    @DisplayName("Should get status code 200 and verify 'success' == true")
    public void testSuccessfulRequest() {

        Response response =
                given().
                        header("Content-Type", "application/json").
                        body(docsData).
                        when().get("/api/deck/new/draw/?count=2");

        DeckofCardsData docsDataResponse = response.body().as(DeckofCardsData.class);
        System.out.println(response.prettyPrint());

        // Expect
        assertEquals(200, response.statusCode());
        assertEquals(true, docsDataResponse.getSuccess());

    }

    @Test
    @Order(2)
    @DisplayName("'deck_id' must have value and verify random id")
    public void testDeckID() {

        // Should have 1st value
        Response response =
                given().
                        header("Content-Type", "application/json").
                        body(docsData).
                        when().get("/api/deck/new/draw/?count={id}", count);

        DeckofCardsData docsDataResponse = response.body().as(DeckofCardsData.class);

        String valDeck_Id = docsDataResponse.getDeck_id();

        // Result
        System.out.println("====== Result of Random Deck ID");
        System.out.println("1st value = " + valDeck_Id);

        // Expect 'deck_id' to have a value
        assertEquals(200, response.statusCode());
        assertEquals(valDeck_Id, docsDataResponse.getDeck_id());

        // Should have second value and compare it with first value (equal or not equal)
        Response response2 =
                given().
                        header("Content-Type", "application/json").
                        body(docsData).
                        when().get("/api/deck/new/draw/?count={id}", count);

        DeckofCardsData docsDataResponse2 = response2.body().as(DeckofCardsData.class);

        String valDeck_Id2 = docsDataResponse2.getDeck_id();
        System.out.println("2nd value = " + valDeck_Id2);

        // Expect 'deck_id' to have a random value
        assertEquals(200, response2.statusCode());
        assertNotEquals(valDeck_Id, valDeck_Id2);


        if (valDeck_Id != valDeck_Id2){
            System.out.println("'deck_id' has a random value");
        } else {
            System.out.println("'deck_id' has no a random value");
        }

        System.out.println("");

    }

    @Test
    @Order(3)
    @DisplayName("Should verify all elements from 'cards'")
    public void testCards() {

        Response response =
                given().
                        header("Content-Type", "application/json").
                        body(docsData).
                        when().get("/api/deck/new/draw/?count={id}", count);

        DeckofCardsData docsDataResponse = response.body().as(DeckofCardsData.class);

        List<Card> totalCards = docsDataResponse.getCards();

        // Expect value from 'countId' to equal with total cards
        assertEquals(200, response.statusCode());
        assertEquals(count, totalCards.size(), "It has " + totalCards.size() + " cards");



        // * ================================================================= * //

        JsonPath js = PageObject.rowToJson(response.asString());

        String firstCard = js.getString("cards[0]");
        String secondCard = js.getString("cards[1]");

        System.out.println(firstCard);
        System.out.println(secondCard);

        // Expect the first card no equal (different) with the second card
        assertNotEquals(firstCard, secondCard);



        // * ================================================================= * //

        // Code List
        String[] codeArrays = {
                "AH", "7H", "2D", "6H",
                "2H", "4S", "5C", "JC",
                "2C", "9H", "0S", "QC",
                "0H", "3S", "KH", "5D",
                "3C", "AC", "8D", "QH",
                "9D", "AH", "8S", "0D",
                "7C", "4C", "6D", "JD",
                "QD", "0C", "QS", "7S",
                "6C", "7D", "KC", "0H"};

        // Get Code
        String cardCode = js.getString("cards[0].code");

        // Check if 'cardCode' exists in 'codeArray' list
        boolean contains = ArrayUtils.contains(codeArrays, cardCode);

        // if exists
        if (contains == true){

            assertEquals(true, contains, "it has equal code");

        }
        // if not exists in codeArray
        else {

            assertEquals(false, contains, "it has no equal code");

        }



        // * ================================================================= * //

        String expectImage = "https://deckofcardsapi.com/static/img/" + cardCode +".png";
        String cardImage = js.getString("cards[0].image");

        // Expect 'expectImage' equal with actual image named 'cardImage'
        assertEquals(expectImage, cardImage);

        // * ================================================================= * //

        int totalOfCardImages = js.getInt("cards[0].images.size()");

        // Expect card images have 2 images
        assertEquals(2, totalOfCardImages);

        // * ================================================================= * //

        String[] imagesArrays = {"svg", "png"};

        String typeOfCardImages = js.getString("cards[0].images");

        boolean found = true;
        for (String imageArray : imagesArrays) {
            if (typeOfCardImages.indexOf(imageArray) == -1) {
                found = false;
                break;
            }
        }

        // Expect images have 2 types (svg and png)
        // Expect 'imageArray' equal with 'typeOfCardImages'

        // if exists
        if (found == true){

            assertEquals(true, found, "it has equal code");

        }
        // if not exists
        else {

            assertEquals(false, found, "it has no equal code");

        }



        // * ================================================================= * //

        String[] valueArrays = {
                "1", "2", "3", "4",
                "5", "6", "7", "8",
                "9", "10",
                "QUEEN", "KING", "ACE"
        };

        String cardValue = js.getString("cards[0].value");

        // Check if 'cardValue' exists in 'valueArrays' list
        boolean contains2 = ArrayUtils.contains(valueArrays, cardValue);

        // Expect

        // if exists
        if (contains2 == true){

            assertEquals(true, contains2, "it has equal");

        }
        // if not exists
        else {

            assertEquals(false, contains2, "it has no equal");

        }



        // * ================================================================= * //

        String[] suitArrays = {
                "HEARTS", "DIAMONDS",
                "SPADES", "CLUBS"
        };

        String cardSuit = js.getString("cards[0].suit");

        // Check if 'cardSuit' exists in 'suitArrays' list
        boolean contains3 = ArrayUtils.contains(suitArrays, cardSuit);

        // Expect
        // if exists
        if (contains2 == true){

            assertEquals(true, contains3, "it has equal");

        }
        // if not exists
        else {

            assertEquals(false, contains3, "it has no equal");

        }

    }

    @Test
    @Order(4)
    @DisplayName("Should be able to create a new card id and start a shuffling session'")
    public void testRemaining() {

        // If you just started the game
        // with 2 cards, at the beginning,
        // you will get 'remaining' as much as 50

        Response response =
                given().
                        header("Content-Type", "application/json").
                        body(docsData).
                        when().get("/api/deck/new/draw/?count=2");

        DeckofCardsData docsDataResponse = response.body().as(DeckofCardsData.class);

        String valDeck_Id = docsDataResponse.getDeck_id();
        int valRemaining = docsDataResponse.getRemaining();

        // Expect
        assertEquals(200, response.statusCode());
        assertEquals(50, valRemaining);

        // Start the Game
        System.out.println("======== START THE GAME ========");

        System.out.println("");
        System.out.println("Your Game Session ID is " + valDeck_Id);
        System.out.println("");

        String response2 = "";
        for(int i = valRemaining; i > 0; i-=2){
            response2 =
                    given().
                            pathParam("deckid", valDeck_Id).
                            header("Content-Type", "application/json").
                            body(docsData).
                            when().get("/api/deck/{deckid}/draw/?count=2").
                            then().assertThat().statusCode(200).extract().response().asString();

            JsonPath js3 = PageObject.rowToJson(response2);

            System.out.println("Remaining = " + i);
            System.out.println("Your Cards");

            int cardImageCount = js3.getInt("cards.size()");
            for (int j = 0; j < cardImageCount; j++) {
                System.out.println(js3.getString("cards[" + j + "].image"));
            }
            System.out.println("");

        }

        JsonPath js4 = PageObject.rowToJson(response2);
        int resultRemaining = js4.getInt("remaining");

        System.out.println("Remaining = " + resultRemaining);
        System.out.println("Not enough cards remaining to draw 2 additional");

        // Expect
        assertEquals(0, resultRemaining);

    }

}
