package mine.ensaj.credit.ui.home;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mine.ensaj.credit.R;
import mine.ensaj.credit.classes.Client;
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

            }
        });

    }
}