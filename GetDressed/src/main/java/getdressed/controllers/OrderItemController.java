package getdressed.controllers;

import getdressed.domain.OrderItem;
import getdressed.dto.requests.OrderItemRequestDTO;
import getdressed.dto.responses.OrderItemResponseDTO;
import getdressed.dto.responses.OrderItemResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<ResponseMessage> getAll(){
        List<OrderItem> orderItems = orderItemService.getAll();
        if (orderItems.isEmpty())return ResponseMessage.notFound("No item was found");
        else return ResponseMessage.ok("Success", orderItems.stream().map(OrderItemResponseDTO::fromOrderItem).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable Long id){
        OrderItem orderItem = orderItemService.getById(id).orElse(null);
        if (orderItem != null) return ResponseMessage.ok("Success", OrderItemResponseDTO.fromOrderItem(orderItem));
        else return ResponseMessage.notFound("No item was found");
    }

    @GetMapping("/order/{order}")
    public ResponseEntity<ResponseMessage> getAllByOrder(@PathVariable Long order){
        List<OrderItem> orderItems = orderItemService.getByOrder(order).orElse(null);
        if (orderItems.isEmpty())return ResponseMessage.notFound("No item was found");
        else return ResponseMessage.ok("Success", orderItems.stream().map(OrderItemResponseDTO::fromOrderItem).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        orderItemService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
