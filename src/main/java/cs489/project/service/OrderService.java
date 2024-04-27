package cs489.project.service;

import cs489.project.dto.order.OrdeResponse;
import cs489.project.dto.order.OrderRequest;
import cs489.project.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    List<OrdeResponse> getAllOrdersByCustomerId(int customerId);
    OrdeResponse addNewOrder(int customerId, OrderRequest orderRequest);
    OrdeResponse changeOrderStatus(int orderId, int newStatus) throws OrderNotFoundException;
}
