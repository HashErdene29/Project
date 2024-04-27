package cs489.project.dto.menu;

import cs489.project.dto.dish.DishDto;

import java.util.List;

public record MenuDto (
        int menuId,
        String menuName,
        List<DishDto> dishes
) {
}
