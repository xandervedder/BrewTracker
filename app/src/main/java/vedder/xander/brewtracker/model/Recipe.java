package vedder.xander.brewtracker.model;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Recipe extends AbstractDataItem {

    private LocalDate createdAt;
    private String name;
    private String type;

    public Recipe(LocalDate createdAt, String name, String type) {
        this.createdAt = createdAt;
        this.name = name;
        this.type = type;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String get(String parameterName) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(parameterName)) {
                try {
                    return field.get(this).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
