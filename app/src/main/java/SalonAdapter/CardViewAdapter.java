package SalonAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.eodhuno.ebelle_test.Interface.LoadItems;
import com.example.eodhuno.ebelle_test.R;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.List;

class LoadViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar loadingProgressBar;

        public LoadViewHolder(View itemView) {
            super(itemView);

            loadingProgressBar = (ProgressBar) itemView.findViewById(R.id.loadingProgressBar);
        }
    }

class ItemViewHolder extends RecyclerView.ViewHolder {

    TextView fname,lname;

    public ItemViewHolder(View itemView) {
        super(itemView);

        fname = (TextView) itemView.findViewById(R.id.textCardViewFirstName);
        lname = (TextView) itemView.findViewById(R.id.textCardViewLastName);

    }
}

public class CardViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0, VIEW_LOADING_ITEM = 1;
    boolean itemLoading;
    LoadItems loadedItems;
    Activity activity;
    List<Customer> customerLst;    //model class
    int itemVisibleThreshold = 5;
    int lastItemShown, totalItems;

    public CardViewAdapter(RecyclerView recyclerView, Activity activity, List<Customer> customerLst) {
        this.activity = activity;
        this.customerLst = customerLst;


    final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItems = linearLayoutManager.getItemCount();
                lastItemShown = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if(!itemLoading && totalItems <= (lastItemShown+itemVisibleThreshold))
                {
                    if(loadedItems != null)
                        loadedItems.loadMoreItems();
                }
                itemLoading = true;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return customerLst.get(position) == null ? VIEW_LOADING_ITEM : VIEW_ITEM;
    }

    public void setLoadedItems(LoadItems loadedItems) {
        this.loadedItems = loadedItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM)
        {
            View view = LayoutInflater.from(activity).inflate(R.layout.customerprofiles_list_layout,parent,false);
            return new ItemViewHolder(view);
        }
        else if (viewType == VIEW_LOADING_ITEM)
        {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_show_loading,parent,false);
            return new LoadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder)
        {
            Customer customer = customerLst.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.fname.setText(customerLst.get(position).getFirstName());
            viewHolder.lname.setText(customerLst.get(position).getLastName());
        }
        else if(holder instanceof  LoadViewHolder)
        {
            LoadViewHolder loadViewHolder = (LoadViewHolder) holder;
            loadViewHolder.loadingProgressBar.setIndeterminate(true);
        }

    }
    @Override
    public int getItemCount() {
        return customerLst.size();
    }

    public void setItemLoaded() {
        this.itemLoading = false;
    }
}
