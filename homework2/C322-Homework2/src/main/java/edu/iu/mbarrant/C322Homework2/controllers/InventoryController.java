package edu.iu.mbarrant.C322Homework2.controllers;


import edu.iu.mbarrant.C322Homework2.Guitar;
import edu.iu.mbarrant.C322Homework2.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final Inventory inventory;

    @Autowired
    public InventoryController(Inventory inventory) {
        this.inventory = inventory;
    }

    @GetMapping("/search")
    public List<Guitar> searchGuitars(
            @RequestParam(required = false) String builder,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) int numStrings,
            @RequestParam(required = false) String backWood,
            @RequestParam(required = false) String topWood,
            @RequestParam(required = false) double price
    ) {
        Guitar searchGuitar = new Guitar(null, price, builder, model, type, backWood, topWood);
        return inventory.search(searchGuitar);
    }

    @PostMapping("/add")
    public void addGuitar(@RequestBody Guitar guitar) {
        inventory.addGuitar(guitar);
    }

    @GetMapping("/find/{serialNumber}")
    public Guitar findGuitar(@PathVariable String serialNumber) {
        return inventory.getGuitar(serialNumber);
    }
}
