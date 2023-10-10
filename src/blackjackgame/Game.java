class Game {
    private final Deck deck;
    private final Player player;
    private final Dealer dealer;

    public Game() {
        deck = new Deck();
        player = new Player("Player");
        dealer = new Dealer();
    }

    public void dealInitialCards() {
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
        player.getHand().addCard(deck.drawCard());
        dealer.getHand().addCard(deck.drawCard());
    }

    public void playerTurn(Scanner scanner) {
        while (true) {
            displayGameStatus(true);
            System.out.print("Do you want to 'hit' or 'stand'? ");
            String choice = scanner.next().toLowerCase();
            if ("hit".equals(choice)) {
                player.getHand().addCard(deck.drawCard());
                if (player.getHand().calculateHandValue() > 21) {
                    displayGameStatus(true);
                    System.out.println("Player busts. Dealer wins.");
                    return;
                }
            } else if ("stand".equals(choice)) {
                return;
            } else {
                System.out.println("Invalid choice. Please enter 'hit' or 'stand'.");
            }
        }
    }

    public void dealerTurn() {
        while (dealer.shouldHit()) {
            dealer.getHand().addCard(deck.drawCard());
        }
    }

    public void displayGameStatus(boolean revealDealerCard) {
        System.out.println("\nPlayer's Hand:");
        System.out.println(player.getHand().toString());

        System.out.println("\nDealer's Hand:");
        if (revealDealerCard) {
            System.out.println(dealer.getHand().toString());
        } else {
            System.out.println("Hidden Card");
            System.out.println(dealer.getHand().toString().substring(dealer.getHand().toString().indexOf('\n') + 1));
        }
    }

    public void play() {
        dealInitialCards();
        if (player.getHand().isBlackjack()) {
            displayGameStatus(true);
            System.out.println("Player has a Blackjack! Player wins.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        playerTurn(scanner);

        dealerTurn();
        displayGameStatus(true);

        int playerValue = player.getHand().calculateHandValue();
        int dealerValue = dealer.getHand().calculateHandValue();

        if (dealerValue > 21) {
            System.out.println("Dealer busts. Player wins.");
        } else if (dealerValue > playerValue) {
            System.out.println("Dealer wins.");
        } else if (playerValue > dealerValue) {
            System.out.println("Player wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }
}