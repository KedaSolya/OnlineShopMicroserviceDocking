package lr3.controllers;

import lr3.models.Goods;
import lr3.models.User;
import lr3.repo.GoodsRepo;
import lr3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @PostMapping("/addItem")
    public void add(@RequestBody Goods goods){
        goodsRepo.save(goods);
    }

    @PostMapping("/ban/{id}")
    public void ban(@PathVariable Long id){
        User user = userRepo.findById(id).get();
        user.setIsBanned(true);
        userRepo.save(user);
    }

    @GetMapping("/banned")
    public List<User> getBan(){
        return userRepo.findAll().stream()
                .filter(User::isIsBanned)
                .collect(Collectors.toList());
    }
}