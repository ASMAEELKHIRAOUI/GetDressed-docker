package getdressed.controllers;

import getdressed.domain.Cart;
import getdressed.dto.requests.CartRequestDTO;
import getdressed.dto.responses.CartResponseDTO;
import getdressed.dto.responses.ProductResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ResponseMessage> getAll(){
        List<Cart> carts = cartService.getAll();
        if (carts.isEmpty())return ResponseMessage.notFound("No item was found");
        else return ResponseMessage.ok("Success", carts.stream().map(CartResponseDTO::fromCart).toList());
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> save(@RequestBody CartRequestDTO cartToSave){
        Cart cart = cartService.save((cartToSave.toCart()));
        if (cart == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", CartResponseDTO.fromCart(cart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDTO> update(@RequestBody CartRequestDTO cartToUpdate, @PathVariable Long id){
        Cart cart = cartService.update(cartToUpdate.toCart(), id);
        if (cart == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", CartResponseDTO.fromCart(cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        cartService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseMessage> getAllByUser(@PathVariable Long userId){
        List<Cart> carts = cartService.getAllByUser(userId).orElse(null);
        if (carts.isEmpty())return ResponseMessage.notFound("No item was found");
        else return ResponseMessage.ok("Success", carts.stream().map(CartResponseDTO::fromCart).toList());
    }
}
