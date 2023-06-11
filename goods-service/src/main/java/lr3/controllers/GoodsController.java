package lr3.controllers;

import lr3.helper.GoodsResponse;
import lr3.models.Goods;
import lr3.repo.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsRepo goodsRepo;

    @GetMapping("/all")
    @Cacheable(value = "user")
    public List<Goods> all(){
        return goodsRepo.findAll();
    }

    @GetMapping("/find")
    public GoodsResponse find(){
        GoodsResponse response = new GoodsResponse();
        response.getGoods().addAll(goodsRepo.findAll());
        return response;
    }

    @GetMapping("/{id}")
    @Cacheable(value = "goods", key = "#id")
    public Goods findById(@PathVariable Long id){
        return goodsRepo.findById(id).get();
    }


    @PostMapping("/create")
    public void create(@RequestBody Goods goods){
        goodsRepo.save(goods);
    }


    @DeleteMapping("/{id}")
    public void del(@PathVariable Long id){
        goodsRepo.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Goods goods, @PathVariable Long id){
        goods.setId(id);
        goodsRepo.save(goods);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello world!";
    }
}
