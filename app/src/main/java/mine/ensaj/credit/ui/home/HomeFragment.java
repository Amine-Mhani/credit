package mine.ensaj.credit.ui.home;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.List;

import mine.ensaj.credit.R;
import mine.ensaj.credit.adapters.CreditAdapter;
import mine.ensaj.credit.classes.Category;
import mine.ensaj.credit.classes.Client;
import mine.ensaj.credit.classes.Credit;
import mine.ensaj.credit.classes.Product;
import mine.ensaj.credit.databinding.FragmentHomeBinding;
import mine.ensaj.credit.services.CategoryService;
import mine.ensaj.credit.services.ClientService;
import mine.ensaj.credit.services.CreditService;
import mine.ensaj.credit.services.ProductService;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CreditAdapter creditAdapter;

    private Credit credit;
    private Product product;
    private Category category;

    private TextView id;
    private TextView price;
    private TextView etat;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        CreditService cs = new CreditService(this.getContext());
        ProductService ps = new ProductService(this.getContext());
        ClientService cls = new ClientService(this.getContext());
        CategoryService cat = new CategoryService(this.getContext());



        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        recyclerView = root.findViewById(R.id.recycle_view);
        creditAdapter = new CreditAdapter(getContext(), cs.findAll());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(creditAdapter);




        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}