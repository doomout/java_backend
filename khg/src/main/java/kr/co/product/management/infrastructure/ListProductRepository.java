package kr.co.product.management.infrastructure;

import kr.co.product.management.domain.Product;
import org.springframework.stereotype.Repository;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository //빈으로 등록
public class ListProductRepository {
    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));
        
        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                        .filter(product -> product.sameId(id))
                        .findFirst() //첫 번째 Product 반환
                        .orElseThrow();  // 비어 있으면 예외 반환, 있으면 Product 값 반환
    }
}
