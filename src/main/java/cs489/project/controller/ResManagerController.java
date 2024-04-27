package cs489.project.controller;

import cs489.project.dto.restaurants.RestaurantsDto;
import cs489.project.service.ResManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class ResManagerController {
    private ResManagerService resManagerService;
    public ResManagerController(ResManagerService resManagerService) {
        this.resManagerService = resManagerService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantsDto>> getAllRestaurants() {
        return ResponseEntity.ok(resManagerService.getRestaurants());
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<RestaurantsDto>> getRestaurantsByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(resManagerService.searchRestaurants(keyword));
    }
}
