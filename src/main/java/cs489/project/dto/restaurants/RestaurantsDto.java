package cs489.project.dto.restaurants;

import cs489.project.dto.specification.SpecificationDto;

import java.util.List;

public record RestaurantsDto (
    int resId,
    String resName,
    String email,
    String phoneNumber,
    String address,
    int capacity,
    List<SpecificationDto> specifications
) {
}
