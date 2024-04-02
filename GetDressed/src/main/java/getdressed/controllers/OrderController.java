package getdressed.controllers;

import getdressed.domain.Cart;
import getdressed.domain.Order;
import getdressed.domain.enums.Status;
import getdressed.dto.requests.OrderRequestDTO;
import getdressed.dto.responses.OrderResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.CartService;
import getdressed.services.OrderItemService;
import getdressed.services.OrderService;
import getdressed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity getAll(){
        List<Order> orders = orderService.getAll();
        if (orders.isEmpty())return ResponseMessage.notFound("No order was found");
        else return ResponseMessage.ok("Success", orders.stream().map(OrderResponseDTO::fromOrder).toList());
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(@RequestBody OrderRequestDTO orderToSave){
        Order order = orderService.save((orderToSave.toOrder()));
        if (order == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", OrderResponseDTO.fromOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> update(@RequestBody OrderRequestDTO orderToUpdate, @PathVariable Long id){
        Order order = orderService.update(orderToUpdate.toOrder(), id);
        if (order == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", OrderResponseDTO.fromOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        orderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity search(@PathVariable String searchTerm){
        List<Order> orders = orderService.getByFullNameOrZipcodeOrPhoneOrEmail(searchTerm).orElse(null);
        if (orders.isEmpty()) return ResponseMessage.notFound("No order was found");
        else return ResponseMessage.ok("Success", orders.stream().map(OrderResponseDTO::fromOrder).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Order order = orderService.getById(id).orElse(null);
        if (order == null) return ResponseMessage.notFound("No order was found");
        else return ResponseMessage.ok("Success", OrderResponseDTO.fromOrder(order));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity getByStatus(@PathVariable Status status){
        List<Order> orders = orderService.getByStatus(status).orElse(null);
        if (orders.isEmpty())return ResponseMessage.notFound("No order was found");
        else return ResponseMessage.ok("Success", orders.stream().map(OrderResponseDTO::fromOrder).toList());
    }

    @GetMapping("/user")
    public ResponseEntity getByUser(){
        List<Order> orders = orderService.getAllByUser();
        if (orders.isEmpty())return ResponseMessage.notFound("No order was found");
        else return ResponseMessage.ok("Success", orders.stream().map(OrderResponseDTO::fromOrder).toList());
    }
}
