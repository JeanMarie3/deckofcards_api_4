package reusableData;

import java.util.List;

public class DeckofCardsData {

    private Boolean success;
    private String deck_id;
    private List<Card> cards = null;
    private Integer remaining;

    public Boolean getSuccess() {
        return success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}
