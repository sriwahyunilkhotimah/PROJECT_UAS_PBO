package com.sriwahyuni.restaurant.controller;


import com.sriwahyuni.restaurant.model.MenuItem;
import com.sriwahyuni.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public String getAllMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu";
    }
}