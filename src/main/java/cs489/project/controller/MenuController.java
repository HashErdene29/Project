package cs489.project.controller;

import cs489.project.dto.menu.MenuDto;
import cs489.project.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants/menus")
public class MenuController {
    private MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{resId}")
    public ResponseEntity<List<MenuDto>> getMenu(@PathVariable int resId) {
        return ResponseEntity.ok(menuService.getMenusByRestaurantId(resId));
    }
}
