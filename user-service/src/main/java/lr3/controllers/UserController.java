package lr3.controllers;


import lr3.models.User;
import lr3.repo.GoodsRepo;
import lr3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        user.setIsBanned(false);
        userRepo.save(user);
    }

    @GetMapping("/all")
    @Cacheable(value = "user")
    public List<User> all() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "user", key = "#id")
    public User findById(@PathVariable Long id) {
        return userRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id){
        userRepo.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody User user, @PathVariable Long id){
        user.setId(id);
        userRepo.save(user);
    }

    @PostMapping("/{userId}/buy/{itemId}")
    public String buy(@PathVariable Long userId, @PathVariable Long itemId) {
        if (userRepo.findById(userId).get().isIsBanned()) {
            return "User is banned";
        } else {
            User old = userRepo.findById(userId).get();
            old.getGoods().add(goodsRepo.findById(itemId));
            userRepo.save(old);
            return "Success";
        }
    }

}
