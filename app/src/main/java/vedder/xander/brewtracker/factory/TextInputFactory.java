package vedder.xander.brewtracker.factory;

import android.content.Context;

import vedder.xander.brewtracker.ui.view.TextInput;

public class TextInputFactory implements ViewFactory<TextInput> {

    @Override
    public TextInput assemble(Context context) {
        return new TextInput(context);
    }
}
