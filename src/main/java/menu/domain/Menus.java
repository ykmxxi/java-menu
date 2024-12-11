package menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Menus {

    private final static Map<Category, List<Menu>> menus = new EnumMap<>(Category.class);

    static {
        Arrays.stream(Category.values())
                .forEach(Menus::saveMenus);
    }

    private static void saveMenus(final Category category) {
        List<Menu> categoryMenus = new ArrayList<>();
        category.menuNames()
                .forEach(name -> {
                    Menu menu = new Menu(name, category);
                    categoryMenus.add(menu);
                });
        menus.put(category, categoryMenus);
    }

}
