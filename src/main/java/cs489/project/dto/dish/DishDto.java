package cs489.project.dto.dish;

public record DishDto(
        int dishId,
        String dishName,
        int price,
        String description
) {
}
