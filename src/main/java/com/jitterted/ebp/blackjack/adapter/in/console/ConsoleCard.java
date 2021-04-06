package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Rank;
import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;
/**
 * @author Daniel Hinojosa
 * @since 4/5/21 9:32 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email:
 * <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class ConsoleCard {
    public static String display(Card card) {
        String[] lines = new String[7];
        lines[0] = "┌─────────┐";
        lines[1] = String.format("│%s%s       │", card.rank().display(), card.rank() == Rank.TEN ? "" : " ");
        lines[2] = "│         │";
        lines[3] = String.format("│    %s    │", card.suit().symbol());
        lines[4] = "│         │";
        lines[5] = String.format("│       %s%s│", card.rank() == Rank.TEN ? "" : " ", card.rank().display());
        lines[6] = "└─────────┘";

        Ansi.Color cardColor = card.suit().isRed() ? Ansi.Color.RED : Ansi.Color.BLACK;
        return ansi()
                .fg(cardColor).toString()
                + String.join(ansi().cursorDown(1)
                .cursorLeft(11)
                .toString(), lines);
    }
}
