package tr.edu.medipol.yazilimaraclari.yazilimortamfinalproje;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    // Product sınıfı, ürün özelliklerini temsil eden bir record sınıfıdır.
    public record Product(String ProductName, String id, Double Price) {
        // Yapıcı metot ve getter'lar otomatik olarak oluşturulmuştur.
    }

    // Ürün listesi oluşturulur ve başlangıçta bir örnek ürün eklenir.
    private static final List<Product> LIST = new ArrayList<>();
    static {
        LIST.add(new Product("Monitör", "10", 100.5));
    }

    // Tüm ürünleri listeleyen yapı.
    @GetMapping("/")
    public List<Product> ProductList() {
        // Ürün listesi döndürülür.
        return LIST;
    }

    // Belirli bir ürünü ID'ye göre bulan yapı.
    @GetMapping("/{id}")
    public Product ProductFind(@PathVariable String id) {
        // ID'ye sahip ürün bulunur ve döndürülür. Bulunamazsa Null döner.
        for (Product product : LIST) {
            if (product.id().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Belirli bir ürünü ID'ye göre silen yapı.
    @DeleteMapping("/{id}")
    public Boolean ProductDelete(@PathVariable String id) {
        // ID'ye sahip ürün bulunur ve silinir. Silinirse true, bulunamazsa false döner.
        Product product = ProductFind(id);
        if (product != null) {
            LIST.remove(product);
            return true;
        } else {
            return false;
        }
    }

    // Yeni bir ürün ekleyen yapı.
    @PostMapping("/")
    public Product ProductAdd(@RequestBody Product product) {
        // Gelen ürün eklenir ve eklenen ürün döndürülür.
        LIST.add(product);
        return product;
    }
}
