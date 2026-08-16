package com.ecommerce.service.impl;

import com.ecommerce.dto.request.CartItemRequestDto;
import com.ecommerce.dto.request.UpdateCartItemRequestDto;
import com.ecommerce.dto.response.CartItemResponseDto;
import com.ecommerce.dto.response.CartResponseDto;
import com.ecommerce.entity.*;
import com.ecommerce.exception.*;
import com.ecommerce.mapper.CartItemMapper;
import com.ecommerce.mapper.CartMapper;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;


    @Override
    public CartResponseDto create(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getCart() != null) {
            throw new DuplicateCartException("Cart already exists");
        }

        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);

        Cart cartSaved = cartRepository.save(cart);
        return cartMapper.mapToDto(cartSaved);
    }

    @Override
    public CartResponseDto getCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Cart cart = user.getCart();
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }
        return cartMapper.mapToDto(cart);
    }


    @Override
    public CartItemResponseDto addCartItem(Long userId, CartItemRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getCart() == null) {
            throw new CartNotFoundException("Cart not found");
        }

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        int totalQuantity = dto.getQuantity();
        boolean isProductFound = false;
        CartItem cartItemToSave = null;

        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                totalQuantity = cartItem.getQuantity() + dto.getQuantity();
                if (totalQuantity > product.getStock()) {
                    throw new OutOfStockException("Out of stock");
                }
                cartItem.setQuantity(totalQuantity);
                cartItemToSave = cartItem;
                isProductFound = true;
                break;
            }
        }

        if (!isProductFound) {

            if (totalQuantity > product.getStock()) {
                throw new OutOfStockException("Out of stock");
            }
            CartItem cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setQuantity(dto.getQuantity());
            cartItem.setCart(cart);

            List<CartItem> cartItemList = cart.getCartItems();
            cartItemList.add(cartItem);
            cartItemToSave = cartItem;
        }
       CartItem cartItemSaved = cartItemRepository.save(cartItemToSave);
        return cartItemMapper.mapToDto(cartItemSaved);
    }


    @Override
    public CartItemResponseDto updateCartItem(Long userId, Long cartItemId, UpdateCartItemRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getCart() == null) {
            throw new CartNotFoundException("Cart not found");
        }

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFound("Cart Item not found"));

        Cart cart = user.getCart();

        if (cart.equals(cartItem.getCart())) {
            if (dto.getQuantity() > cartItem.getProduct().getStock()) {
                throw new OutOfStockException("Out of stock");
            }
            cartItem.setQuantity(dto.getQuantity());
        }else {
            throw new CartItemNotFound("Cart Item not found");
        }

        CartItem updateCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.mapToDto(updateCartItem);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getCart() == null) {
            throw new CartNotFoundException("Cart not found");
        }

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFound("Cart Item not found"));

        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();

        if (!cart.equals(cartItem.getCart())) {
            throw new CartItemNotFound("Cart Item not found");
        }
        cartItems.remove(cartItem);
        cartItemRepository.delete(cartItem);

    }

    @Override
    public void clearCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        if (user.getCart() == null){
            throw new CartNotFoundException("Cart not found");
        }

        List<CartItem> cartItems = user.getCart().getCartItems();
        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }

        cartItems.clear();
    }

    @Override
    public BigDecimal getCartTotal(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
