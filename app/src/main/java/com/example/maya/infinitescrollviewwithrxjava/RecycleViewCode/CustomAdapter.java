package com.example.maya.infinitescrollviewwithrxjava.RecycleViewCode;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maya.infinitescrollviewwithrxjava.R;

import java.util.List;

/**
 * Created by maya on 4/11/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
{
    //Change the type of the list for the custom data
    private List<Person> listdata;
    private RecyclerView rv;
    int totalItemCount,visibleItemCount,firstVisibleItem,lastVisibleItem;
    int VISIBLE_THRESHOLD=2;
    boolean loading;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    //The constructor
    public CustomAdapter(final List<Person> listdata2, RecyclerView rv) {
        this.listdata = listdata2;
        this.rv=rv;
        loading=false;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                visibleItemCount = linearLayoutManager.getChildCount();
                lastVisibleItem =linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if(!loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD))
                {

                    listdata.add(null);

                    notifyItemInserted(listdata.size() - 1);
                    loading=true;
                }
            }
        });
    }

    //Initializing the ViewHolder. Basically u take the view and assign them here
    public static class CustomViewHolder extends RecyclerView.ViewHolder
    {
        CardView cv;
        TextView personName;
        TextView personAge;
        CustomViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);

        }

    }
    //Just view holder assignment. just change the layout
    @Override
    public int getItemViewType(int position)
    {
        return listdata.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_vew_main, parent, false);
        if(viewType==VIEW_PROG)
        {
            v.findViewById(R.id.person_name).setVisibility(View.GONE);
            v.findViewById(R.id.person_age).setVisibility(View.GONE);
            v.findViewById(R.id.infiniteprogressbar).setVisibility(View.VISIBLE);
            //v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_vew_main, parent, false);

        }
        else
        {
            v.findViewById(R.id.person_name).setVisibility(View.VISIBLE);
            v.findViewById(R.id.person_age).setVisibility(View.VISIBLE);
            v.findViewById(R.id.infiniteprogressbar).setVisibility(View.GONE);
            //v= LayoutInflater.from(parent.getContext()).inflate(R.layout.infiniteprogressbar, parent, false);

        }

        return new CustomViewHolder(v);
    }

    //the real work. Binding the listview to the views
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {

        if(holder.getItemViewType()==VIEW_ITEM)
        {
            holder.personName.setText(listdata.get(position).name);
            holder.personAge.setText(listdata.get(position).age);
        }


    }



    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void setData(List<Person> persons )
    {
            this.listdata=persons;
    }
}
