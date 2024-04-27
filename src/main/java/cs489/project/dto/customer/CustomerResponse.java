package cs489.project.dto.customer;

public record CustomerResponse(
        int customerId,
        String firstname,
        String lastname,
        String email,
        String phoneNumber
) {
}
