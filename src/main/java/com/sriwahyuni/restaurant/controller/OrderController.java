package com.sriwahyuni.restaurant.controller;

import com.sriwahyuni.restaurant.model.MenuItem;
import com.sriwahyuni.restaurant.model.Order;
import com.sriwahyuni.restaurant.model.OrderItem;
import com.sriwahyuni.restaurant.service.MenuItemService;
import com.sriwahyuni.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrderService orderService;

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @GetMapping("/menu")
    public String viewMenu(Model model) {
        model.addAttribute("menuItems", menuItemService.getAllMenuItems());
        return "menu";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long menuItemId, @RequestParam int quantity, @ModelAttribute("order") Order order, Model model) {
        MenuItem menuItem = menuItemService.getMenuItemById(menuItemId);
        if (menuItem != null) {
            if (order.getOrderItems() == null) {
                order.setOrderItems(new ArrayList<>());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(quantity);  // Mengatur quantity
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        model.addAttribute("orderItems", order.getOrderItems());
        return "redirect:/order/cart";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("orderItems", order.getOrderItems());
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkoutForm(@ModelAttribute("order") Order order, Model model) {
        model.addAttribute("order", order);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("order") Order order, @RequestParam String customerName, @RequestParam String customerAddress, @RequestParam String customerPhone) {
        order.setCustomerName(customerName);
        order.setCustomerAddress(customerAddress);
        order.setCustomerPhone(customerPhone);
        orderService.saveOrder(order);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation() {
        return "confirmation";
    }
    
}