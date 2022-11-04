package mine.ensaj.credit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


import mine.ensaj.credit.R;
import mine.ensaj.credit.adapters.CreditAdapter;
import mine.ensaj.credit.classes.Category;
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

    private FloatingActionButton addButton;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        CreditService cs = new CreditService(this.getContext());
        ProductService ps = new ProductService(this.getContext());
        ClientService cls = new ClientService(this.getContext());
        CategoryService cat = new CategoryService(this.getContext());
/**
        addButton = view.findViewById(R.id.add_credit);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });*/



        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.addCredit.findViewById(R.id.add_credit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });




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