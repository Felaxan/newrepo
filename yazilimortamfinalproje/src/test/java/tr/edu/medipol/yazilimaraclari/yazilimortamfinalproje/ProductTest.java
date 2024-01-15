package tr.edu.medipol.yazilimaraclari.yazilimortamfinalproje;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tr.edu.medipol.yazilimaraclari.yazilimortamfinalproje.ProductController.Product;

class ProductTest {

    @Test
    void testList() {
        // Test edilen metodu çağırır ve ürün listesinin boyutunu kontrol eder..
        ProductController productWebService = new ProductController();
        assertEquals(2, productWebService.ProductList().size());
    }

    @Test
    void testDelete() {
        // Ürün ekler, sonra sildiğinde silinip silinmediğini kontrol eder.
        ProductController productWebService = new ProductController();
        Product product = new Product("Kitap", "6", 12.0);
        productWebService.ProductAdd(product);

        String deletedNo = product.id();
        Boolean silinenUrun = productWebService.ProductDelete(deletedNo);

        assertTrue(silinenUrun);
        assertFalse(productWebService.ProductList().contains(product));
    }

    @Test
    void testAdd() {
        // Yeni bir ürün ekler ve eklenen ürünün listeye eklenip eklenmediğini kontrol eder.
        ProductController urunWebServisi = new ProductController();
        Product product = new Product("Telefon", "7", 300.0);

        Product addedProduct = urunWebServisi.ProductAdd(product);

        assertNotNull(addedProduct);
        assertEquals(product, addedProduct);
        assertTrue(urunWebServisi.ProductList().contains(product));
    }

    @Test
    void testFindNotFound() {
        // Var olmayan bir ürün ID'si ile find metodu çağrıldığında null döndüğünü kontrol Eder.
        ProductController productWebService = new ProductController();
        Product foundProduct = productWebService.ProductFind("nonexistentId");

        assertNull(foundProduct);
    }
    
    @Test
    void testProductDeleteNullProduct() {
        // Var olmayan bir ürün ID'si ile delete metodu çağrıldığında false döndüğünü kontrol eder.
        ProductController productController = new ProductController();
        Boolean result = productController.ProductDelete("nonexistentId");

        assertFalse(result);
    }
}
