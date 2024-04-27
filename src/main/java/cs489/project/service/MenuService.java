package cs489.project.service;

import cs489.project.dto.menu.MenuDto;

import java.util.List;

public interface MenuService {
    List<MenuDto> getMenusByRestaurantId(int restaurantId);
}
