package com.example.wafil.Wafil.giziTest;

/**
 * Created by bruker on 19.06.2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class DBSetupInsert {

    /* Variables */
    private final Context context;

    /* Public Class ------------------------------------------------------ */
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }



    /* Setup Insert To Categories ----------------------------------------- */
    // To insert to category table
    public void setupInsertToCategories(String values){
        try{
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("categories",
                    "_id, category_name, category_parent_id, category_icon, category_note",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert categories.", Toast.LENGTH_SHORT).show();
        }
    }
    public void insertAllCategories(){
        setupInsertToCategories("NULL, 'Nasi Daging Ikan', '0', '', NULL");
        setupInsertToCategories("NULL, 'Nasi', '1', '', NULL");
        setupInsertToCategories("NULL, 'Daging', '1', '', NULL");
        setupInsertToCategories("NULL, 'Ikan', '1', '', NULL");

        // Parent id: 5
        setupInsertToCategories("NULL, 'Buah dan Sayur', '0', '', NULL");
        setupInsertToCategories("NULL, 'Buah', '5', '', NULL");
        setupInsertToCategories("NULL, 'Sayur', '5', '', NULL");

        setupInsertToCategories("NULL, 'Kacang', '0', '', NULL");
        setupInsertToCategories("NULL, 'Kacang', '8', '', NULL");
        setupInsertToCategories("NULL, 'Tahu', '8', '', NULL");
        setupInsertToCategories("NULL, 'Tempe', '8', '', NULL");

        setupInsertToCategories("NULL, 'Telur', '0', '', NULL");
        setupInsertToCategories("NULL, 'Telur Rebus', '12', '', NULL");
        setupInsertToCategories("NULL, 'Telur Dadar', '12', '', NULL");

    }




    /* Setup Insert To Food ----------------------------------------------- */
    // To insert food to food table
    public void setupInsertToFood(String values){

        try {
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("food",
                    "_id, food_name, food_manufactor_name, food_serving_size_gram, food_serving_size_gram_mesurment, food_serving_size_pcs, food_serving_size_pcs_mesurment, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_thumb, food_image_a, food_image_b, food_image_c, food_notes",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert food.", Toast.LENGTH_SHORT).show();
        }

    }
    // Insert all food into food database
    public void insertAllFood() {
        setupInsertToFood("NULL, 'Apel', 'Makanan', '150', 'gram', '1', 'buah', '52', '0', '14', '0', '72', '0', '19', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Nasi Putih', 'Makanan', '105', 'gram', '1', 'porsi', '129', '2', '27', '0', '105', '2', '29', '0', NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Jagung Rebus', 'Makanan', '28', 'gram', '1', 'ons', '93', '3', '22', '0', '59', '1', '14', '0', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kentang tanpa kulit dan garam direbus', 'Makanan', '150', 'gram', '1', 'porsi', '86', '1', '20', '0', '144', '2', '33', '0', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Nasi Merah', 'Makanan', '200', 'gram', '1', 'Porsi', '110', '2', '22', '0', '220', '4', '44', '1', NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Dada Ayam tanpa Kulit', 'Makanan', '50', 'gram', '1', 'porsi', '164', '30', '0', '3', '97', '15', '0', '1', NULL, NULL, '3', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Telur Rebus', 'Makanan', '50', 'gram', '1', 'buah', '154', '12', '1', '10', '77', '6', '0', '5', NULL, NULL, '13', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Telur Dadar', 'Makanan', '70', 'gram', '1', 'porsi', '153', '10', '0', '12', '93', '6', '0', '7', NULL, NULL, '14', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Tahu', 'Makanan', '125', 'gram', '1', 'porsi', '78', '7', '2', '4', '98', '9', '2', '6', NULL, NULL, '10', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Daging Sapi', 'Makanan', '85', 'gram', '1', 'porsi', '288', '26', '0', '19', '245', '22', '0', '16', NULL, NULL, '3', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Tempe', 'Makanan', '28', 'gram', '1', 'ons', '193', '18', '9', '10', '55', '5', '2', '3', NULL, NULL, '11', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kacang Kedelai', 'Makanan', '12', 'gram', '1', 'porsi', '471', '35', '33', '25', '55', '4', '3', '2', NULL, NULL, '9', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kacang Polong', 'Makanan', '50', 'gram', '1', 'mangkok', '42', '2', '7', '0', '21', '1', '3', '0', NULL, NULL, '9', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Nasi Putih', 'Makanan', '105', 'gram', '1', 'porsi', '129', '2', '27', '0', '105', '2', '29', '0', NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Jagung Rebus', 'Makanan', '28', 'gram', '1', 'ons', '93', '3', '22', '0', '59', '1', '14', '0', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kentang tanpa kulit dan garam direbus', 'Makanan', '150', 'gram', '1', 'porsi', '86', '1', '20', '0', '144', '2', '33', '0', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Nasi Merah', 'Makanan', '200', 'gram', '1', 'Porsi', '110', '2', '22', '0', '220', '4', '44', '1', NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Dada Ayam tanpa Kulit', 'Makanan', '50', 'gram', '1', 'porsi', '164', '30', '0', '3', '97', '15', '0', '1', NULL, NULL, '3', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Telur Rebus', 'Makanan', '50', 'gram', '1', 'buah', '154', '12', '1', '10', '77', '6', '0', '5', NULL, NULL, '13', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Telur Dadar', 'Makanan', '70', 'gram', '1', 'porsi', '153', '10', '0', '12', '93', '6', '0', '7', NULL, NULL, '14', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Tahu', 'Makanan', '125', 'gram', '1', 'porsi', '78', '7', '2', '4', '98', '9', '2', '6', NULL, NULL, '10', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Daging Sapi', 'Makanan', '85', 'gram', '1', 'porsi', '288', '26', '0', '19', '245', '22', '0', '16', NULL, NULL, '3', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Tempe', 'Makanan', '28', 'gram', '1', 'ons', '193', '18', '9', '10', '55', '5', '2', '3', NULL, NULL, '11', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kacang Kedelai', 'Makanan', '12', 'gram', '1', 'porsi', '471', '35', '33', '25', '55', '4', '3', '2', NULL, NULL, '9', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kacang Polong', 'Makanan', '50', 'gram', '1', 'mangkok', '42', '2', '7', '0', '21', '1', '3', '0', NULL, NULL, '9', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Sayur Campur', 'Makanan', '200', 'gram', '1', 'mangkok', '60', '2', '13', '0', '120', '4', '26', '1', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Bandeng', 'Makanan', '85', 'gram', '3', 'ons', '148', '20', '0', '6', '126', '17', '0', '5', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Cakalang','Makanan', '100', 'gram', '1', 'porsi', '132', '28', '0', '1', '132', '28', '0', '1', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Gurame','Makanan', '110', 'gram', '1', 'porsi', '125', '17', '0', '5', '137', '19', '0', '6', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Mas','Makanan', '100', 'gram', '1', 'porsi', '87', '16', '0', '2', '87', '16', '0', '2', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Mujair','Makanan', '150', 'gram', '1', 'porsi', '169', '24', '2', '6', '253', '36', '3', '10', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Salmon','Makanan', '57', 'gram', '1', 'porsi', '146', '21', '0', '5', '83', '12', '0', '3', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Ikan Tuna','Makanan', '57', 'gram', '1', 'porsi', '108', '23', '0', '0', '62', '13', '0', '0', NULL, NULL, '4', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Sayur Asem', 'Makanan', '240', 'gram', '1', 'mangkok', '33', '1', '5', '1', '80', '3', '12', '2', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Sayur Bening Bayam', 'Makanan', '200', 'gram', '1', 'mangkok', '36', '1', '7', '0', '72', '2', '14', '1', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Sayur Lodeh', 'Makanan', '240', 'gram', '1', 'mangkok', '68', '2', '6', '3', '162', '6', '14', '9', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Sayur Pakis', 'Makanan', '200', 'gram', '1', 'mangkok', '34', '4', '5', '0', '68', '8', '10', '1', NULL, NULL, '7', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Kiwi', 'Makanan', '76', 'gram', '1', 'buah', '61', '1', '14', '0', '46', '0', '11', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Naga', 'Makanan', '99', 'gram', '1', 'buah', '51', '0', '12', '0', '50', '0', '12', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Pir', 'Makanan', '150', 'gram', '1', 'buah', '58', '0', '21', '0', '27', '0', '7', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Alpukat', 'Makanan', '28', 'gram', '1', 'ons', '160', '2', '8', '14', '45', '0', '2', '4', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Pepaya', 'Makanan', '28', 'gram', '1', 'ons', '39', '0', '9', '0', '11', '0', '2', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Nanas', 'Makanan', '28', 'gram', '1', 'ons', '48', '0', '12', '0', '14', '0', '3', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Jeruk', 'Makanan', '125', 'gram', '1', 'buah', '47', '0', '11', '0', '62', '1', '15', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Pisang', 'Makanan', '100', 'gram', '1', 'buah', '89', '1', '22', '0', '89', '1', '22', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
        setupInsertToFood("NULL, 'Anggur', 'Makanan', '80', 'gram', '1', 'porsi', '69', '0', '18', '0', '55', '0', '14', '0', NULL, NULL, '6', NULL, NULL, NULL, NULL, NULL");
    }


}
