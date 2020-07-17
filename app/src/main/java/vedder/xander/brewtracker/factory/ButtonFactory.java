package vedder.xander.brewtracker.factory;

import android.content.Context;

import vedder.xander.brewtracker.ui.view.Button;

public class ButtonFactory implements ViewFactory<Button> {

    @Override
    public Button assemble(Context context) {
        return new Button(context);
    }
}
