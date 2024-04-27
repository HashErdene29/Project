package cs489.project.service;

import cs489.project.dto.restaurants.RestaurantsDto;
import cs489.project.model.RestaurantManager;

import java.util.List;

public interface ResManagerService {
    List<RestaurantsDto> getRestaurants();

    List<RestaurantsDto> searchRestaurants(String keyword);
}
