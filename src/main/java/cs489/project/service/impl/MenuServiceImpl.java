package cs489.project.service.impl;

import cs489.project.dto.dish.DishDto;
import cs489.project.dto.menu.MenuDto;
import cs489.project.model.Menu;
import cs489.project.repository.MenuRepository;
import cs489.project.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private MenuRepository menuRepository;
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuDto> getMenusByRestaurantId(int id){
        List<Menu> menus= menuRepository.findAllByRestaurant_ResId(id);
        return menus.stream()
                .map(a -> new MenuDto(
                        a.getMenuId(),
                        a.getMenuName(),
                        a.getDishes().stream()
                                .map(b -> new DishDto(
                                        b.getDishId(),
                                        b.getDishName(),
                                        b.getPrice(),
                                        b.getDescription()
                                )).toList()
                )).toList();
    }
}
