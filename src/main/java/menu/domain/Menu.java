package menu.domain;

import java.util.Objects;

public class Menu {

    private final String name;
    private final Category category;

    public Menu(final String name, final Category category) {
        this.name = name;
        this.category = category;
    }

    public boolean hasSameName(final String menuName) {
        return this.name.equals(menuName);
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menu menu)) {
            return false;
        }
        return Objects.equals(name, menu.name) && category == menu.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

}
