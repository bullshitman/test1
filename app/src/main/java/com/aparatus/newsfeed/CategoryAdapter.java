package com.aparatus.newsfeed;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aparatus.newsfeed.Models.Event;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Listener mListener;
    private List<Event> mEventList;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Listener mListener;
        private CardView cardView;

        private ViewHolder(CardView cv, Listener listener) {
            super(cv);
            cardView = cv;
            this.mListener = listener;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Event event = getEvent(getAdapterPosition());
            this.mListener.onClick(event.getArticle());
            notifyDataSetChanged();
        }
    }

    public CategoryAdapter(Context context, List<Event> news, Listener listener){
        mEventList = news;
        mContext = context;
        mListener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        CardView cv = (CardView) inflater.inflate(R.layout.card_post, parent, false);
        return new ViewHolder(cv, this.mListener);
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Event event = mEventList.get(position);
        CardView cardView = holder.cardView;

        TextView title = cardView.findViewById(R.id.title);
        title.setText(event.getTitle());

        TextView preview = cardView.findViewById(R.id.preview);
        preview.setText(event.getPreview());

        TextView time = cardView.findViewById(R.id.time);
        time.setText(event.getTime());

        TextView coefficient = cardView.findViewById(R.id.coefficient);
        coefficient.setText(event.getCoefficient());

        TextView place = cardView.findViewById(R.id.place);
        place.setText(event.getPlace());


    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    private Event getEvent(int adapterPosition){
       return mEventList.get(adapterPosition);
    }

    public interface Listener {
        void onClick(String position);
    }
    public void updateNews(List<Event> news) {
        mEventList = news;
        notifyDataSetChanged();
    }
}
