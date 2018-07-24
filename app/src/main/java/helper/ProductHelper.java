package helper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductHelper {

    public ProductHelper(){}

    static DatabaseManager mDatabase;
    Context mCtx;

    public ProductHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();

        Cursor productCursor = mDatabase.readAllProducts();
        if (productCursor.moveToFirst()) {
            do {
                allProducts.add(new Product(
                        productCursor.getInt(0),
                        productCursor.getString(1),
                        productCursor.getString(2),
                        productCursor.getString(3),
                        productCursor.getInt(4),
                        productCursor.getInt(5),
                        productCursor.getInt(6),
                        productCursor.getInt(7),
                        productCursor.getInt(8)
                ));
            }
            while (productCursor.moveToNext());
        }
        return allProducts;


}

    public static Product getProductById(int id) throws Exception {
        ArrayList<Product> productsArrayList = getAllProducts();
        Product product = new Product();
        boolean found = false;
        for (Product prod : productsArrayList) {
            if (prod.getProdID() == id) {
                product = prod;
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* PRODUCT WITH ID " + id + " NOT FOUND");
        }
        return product;
    }

    public static ArrayList<Product> getProductByName(String product) throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currProductName = new ArrayList<>();

        boolean found = false;
        for (Product prodName : productArrayList) {
            if (prodName.getProd_Name().equalsIgnoreCase(product)) {
                currProductName.add(prodName);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO PRODUCT WITH PRODUCT NAME " + product + " FOUND");
        }
        return currProductName;
    }

    public static ArrayList<Product> getProductByPriceRange(int low, int high) throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currProduct = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getPrice() >= low && product.getPrice() <= high){
                currProduct.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO PRODUCT PRICE IN RANGE " +low + " TO " +high);
        }
        return currProduct;
    }

    public static ArrayList<Product> getProductByAvailability() throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currAvailableProduct = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getAvailability() == 1){
                currAvailableProduct.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO AVAILABLE PRODUCT FOUND");
        }
        return currAvailableProduct;
    }

    public static ArrayList<Product> getProductQuantity() throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currProductQuantity = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getProdQty() > 0 ){
                currProductQuantity.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO AVAILABLE PRODUCT ");
        }
        return currProductQuantity;
    }

    public static ArrayList<Product> getProductAboveReorderLevel(int aboveReorderLevel) throws Exception {
        ArrayList<Product> productArrayList = getProductQuantity();
        ArrayList<Product> currProductAboveReorderLevel = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getReorderlevel() > aboveReorderLevel ){
                currProductAboveReorderLevel.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO PRODUCT WITH REORDER LEVEL "+aboveReorderLevel);
        }
        return currProductAboveReorderLevel;
    }

    public static ArrayList<Product> getProductBelowReorderLevel(int belowReorderLevel) throws Exception {
        ArrayList<Product> productArrayList = getProductQuantity();
        ArrayList<Product> currProductBelowReorderLevel = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getReorderlevel() < belowReorderLevel ){
                currProductBelowReorderLevel.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO PRODUCT BELOW REORDER LEVEL "+belowReorderLevel);
        }
        return currProductBelowReorderLevel;
    }

    public static ArrayList<Product> getProductAtReorderLevel(int atReorderLevel) throws Exception {
        ArrayList<Product> productArrayList = getProductQuantity();
        ArrayList<Product> currProductAtReorderLevel = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getReorderlevel() == atReorderLevel){
                currProductAtReorderLevel.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO PRODUCT BELOW REORDER LEVEL "+atReorderLevel);
        }
        return currProductAtReorderLevel;
    }

    public static ArrayList<Product> getProductsByServiceID(int serviceId) throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currServiceOfProduct = new ArrayList<>();

        boolean found = false;
        for (Product product : productArrayList) {
            if (product.getServID_FK() == serviceId) {
                currServiceOfProduct.add(product);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO PRODUCT WITH SERVICE_ID " + serviceId + " FOUND");
        }
        return currServiceOfProduct;
    }

    public static ArrayList<Product> getProductByQuantityRange(int low, int high) throws Exception {
        ArrayList<Product> productArrayList = getAllProducts();
        ArrayList<Product> currProduct = new ArrayList<>();

        boolean found = false;
        for(Product product:productArrayList){
            if(product.getProdQty() >= low && product.getProdQty() <= high){
                currProduct.add(product);
                found = true;
            }
        }
        if(!found){
            throw new Exception("NO PRODUCT QUANTITY IN RANGE " +low + " TO " +high);
        }
        return currProduct;
    }
    public static int getProductUsageMetrics(int id) throws Exception {
        int productUsage = ServiceHelper.serviceUsageMetrics(id);
        return productUsage;
    }

    public static ArrayList<Product> getProductByCategory(int categoryId) throws Exception {
        ArrayList<Service> services = ServiceHelper.getServiceByCategoryId(categoryId);
        ArrayList<Product> currCategoryOfProduct = new ArrayList<>();

        ArrayList<Integer> serviceCategoryIDs = new ArrayList<>();
        for(Service service:services){
            serviceCategoryIDs.add(service.getServID());
        }

        boolean found = false;
        for (Integer i : serviceCategoryIDs) {
            ArrayList<Product> productArrayList = new ArrayList<Product>();
            try{
                //productArrayList = getProductByService(i);
            }catch(Exception e){
                //do nothing
            }
            if(productArrayList.size() > 0){
                currCategoryOfProduct.addAll(productArrayList);
                found = true;
            }

        }
        if (!found) {
            throw new Exception("*ODHUNO* NO PRODUCTS WITH CATEGORY_ID " + categoryId + " FOUND");
        }
        return currCategoryOfProduct;
    }


}
