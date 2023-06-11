package lr3.repo;


import lr3.helper.GoodsResponse;
import lr3.models.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GoodsRepo {
    @Autowired
    WebClient webClient;

    public void save(Goods goods){
        webClient.post()
                .uri("http://localhost:8081/api/goods/create")
                .body(Mono.just(goods), Goods.class)
                .retrieve()
                .bodyToMono(Goods.class)
                .block();
    }

    public Goods findById(Long id){
        return webClient.get()
                .uri("http://localhost:8081/api/goods/{id}", id)
                .retrieve()
                .bodyToMono(Goods.class)
                .block();
    }

    public List<Goods> findAll(){
        return webClient.get()
                .uri("http://localhost:8081/api/goods/find")
                .retrieve()
                .bodyToMono(GoodsResponse.class)
                .block()
                .getGoods();
    }
}
