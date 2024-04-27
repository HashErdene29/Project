package cs489.project.dto.restaurants;

public record RestaurantResponse(
        int resId,
        String resName,
        String email,
        String phoneNumber,
        String address,
        int capacity
) {
}
