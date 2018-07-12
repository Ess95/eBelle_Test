package helper;

import android.content.Context;
import android.database.Cursor;

import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Category;

import java.util.ArrayList;

public class CategoryHelper {

    public CategoryHelper() { }

    Context context;
    static DatabaseManager mDatabase;

    public CategoryHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Category> getAllCategories() {
        ArrayList<Category> categoryArrayList = new ArrayList<Category>();

        Cursor categoryCursor = mDatabase.readAllServiceCategory();
        if (categoryCursor.moveToFirst()) {
            //Adds the records to the ArrayList
            do {
                categoryArrayList.add(new Category(
                        categoryCursor.getInt(0),
                        categoryCursor.getString(1),
                        categoryCursor.getString(2),
                        categoryCursor.getString(3)
                ));
            }
            while (categoryCursor.moveToNext());
        }
        return categoryArrayList;
    }

    public static Category getCategoryById(int catID) throws Exception {
        ArrayList<Category> categories = getAllCategories();
        Category category = new Category();
        boolean found = false;

        for(Category cat:categories){
            if(cat.getCategotyID() == catID){
                category = cat;
                found = true;
            }

        }
        if(!found){
            throw new Exception("NO CATEGORY WITH ID "+catID+ "FOUND");
        }
        return category;
    }

    public static Category getCategoryByName(String catName) throws Exception {
        ArrayList<Category> categories = getAllCategories();

        Category category = new Category();
        boolean found = false;
        for(Category cat:categories){
            if(cat.getCategoryName().equalsIgnoreCase(catName)){
                category = cat;
                found = true;
            }

        }
        if(!found){
            throw new Exception("NO CATEGORY WITH NAME "+catName+ "FOUND");
        }
        return category;
    }

}
