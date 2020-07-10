package vedder.xander.brewtracker.factory;

import android.content.Context;

import vedder.xander.brewtracker.ui.view.Card;

public class CardFactory implements AbstractViewFactory<Card> {

    @Override
    public Card assemble(Context context) {
        return new Card(context);
    }
}
