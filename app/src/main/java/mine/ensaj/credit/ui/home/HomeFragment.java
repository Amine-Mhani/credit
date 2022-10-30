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
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import java.sql.Date;

import mine.ensaj.credit.R;
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




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        CreditService cs = new CreditService(this.getContext());
        ProductService ps = new ProductService(this.getContext());
        ClientService cls = new ClientService(this.getContext());

        /**ps.create(new Product("CocaCola", "le boisson CocaCola", 1));
        ps.create(new Product("Oreo", "le snack oreo", 2));

        cls.create(new Client("MHANI", "Amine", "BB23923", 061545));

        cs.create(new Credit(8.3f, 1, 1, 1, Date.valueOf("2022-11-01"), "Non payé"));*/



        Log.d("findall", "onCreateView: "+cs.findAll().get(0).getEtat());


        recyclerView = view.findViewById(R.id.recycle_view);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}