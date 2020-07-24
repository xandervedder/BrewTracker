package vedder.xander.brewtracker.model;

import vedder.xander.brewtracker.config.ConfigData;

public abstract class AbstractDataItem implements ConfigData {

    public abstract String get(String parameterName);
}
