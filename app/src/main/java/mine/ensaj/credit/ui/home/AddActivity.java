package mine.ensaj.credit.ui.home;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mine.ensaj.credit.R;
import mine.ensaj.credit.classes.Client;
import mine.ensaj.credit.classes.Credit;
import mine.ensaj.credit.classes.Product;
import mine.ensaj.credit.services.CategoryService;
import mine.ensaj.credit.services.ClientService;
import mine.ensaj.credit.services.CreditService;
import mine.ensaj.credit.services.ProductService;

public class AddActivity extends AppCompatActivity {

    private Button add;
    private Spinner client;
    private Spinner product;
    private EditText prix;
    private ClientService cls;
    private CreditService cs;
    private ProductService ps;
    private CategoryService cat;

    private ArrayList<String> clients = new ArrayList<String>();
    private ArrayList<String> products = new ArrayList<String>();

    private ArrayList<Client> clients_obj;
    private ArrayList<Product> products_obj;

    private int id_product_string;
    private int id_client_string;
    private int id_category_string;
    private float price_String;

    private Product cr_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        cs = new CreditService(this);
        ps = new ProductService(this);
        cls = new ClientService(this);
        cat = new CategoryService(this);

        prix = findViewById(R.id.price);
        client = findViewById(R.id.client);
        product = findViewById(R.id.produit);
        add = findViewById(R.id.ajou);

        products_obj = (ArrayList<Product>) ps.findAll();
        clients_obj = (ArrayList<Client>) cls.findAll();


        for (Client c : clients_obj) {
            clients.add(c.getId()+"- "+c.getNom());
        }

        for (Product p: products_obj){
            products.add(p.getId()+"- "+p.getName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>((Context) this, android.R.layout.simple_spinner_item, (List<String>) clients);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        client.setAdapter(adapter);

        adapter = new ArrayAdapter<String>((Context) this, android.R.layout.simple_spinner_item, (List<String>) products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClickAjou: "+String.valueOf(product.getSelectedItem().toString().charAt(0)));
                id_product_string = Integer.parseInt(String.valueOf(product.getSelectedItem().toString().charAt(0)));
                id_client_string = Integer.parseInt(String.valueOf(client.getSelectedItem().toString().charAt(0)));
                cr_pro = products_obj.get(id_product_string-1);
                id_category_string = cr_pro.getCategory();
                price_String = Float.parseFloat(String.valueOf(prix.getText()));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    cs.create(new Credit(price_String, id_client_string, id_category_string, id_product_string, Date.valueOf(String.valueOf(LocalDate.now())), "Non Payé"));
                }



                //Log.d(TAG, "onClickAjou: product : "+id_product_string+" / category : "+id_category_string+" / client : "+id_client_string);
            }
        });

    }
}