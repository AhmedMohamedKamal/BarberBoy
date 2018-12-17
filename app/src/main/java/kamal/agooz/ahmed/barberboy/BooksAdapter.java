package kamal.agooz.ahmed.barberboy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kamal.agooz.ahmed.barberboy.BooksAdapter.BooksViewHolder;


public class BooksAdapter extends RecyclerView.Adapter<BooksViewHolder>
{
    Context context;
    List<BookList> bookLists;

    public BooksAdapter(Context context, List<BookList> bookLists)
    {
        this.context = context;
        this.bookLists = bookLists;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view=LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position)
    {
        BookList bookList=bookLists.get(position);
        holder.name.setText(bookList.getName());
        holder.price.setText(bookList.getPrice());
        holder.time.setText(bookList.getTime());
        holder.date.setText(bookList.getDate());
        if (bookList.getShavehair().length() == 0)
        {
            holder.hair.setVisibility(View.GONE);
        } else
        {
            holder.hair.setText(bookList.getShavehair());
        }

        if (bookList.getShavebeard().length() == 0)
        {
            holder.beard.setVisibility(View.GONE);
        } else
        {
            holder.beard.setText(bookList.getShavebeard());
        }

        if (bookList.getHairprigment().length() == 0)
        {
            holder.pigment.setVisibility(View.GONE);
        } else
        {
            holder.pigment.setText(bookList.getHairprigment());
        }

        if (bookList.getFacemusk().length() == 0)
        {
            holder.musk.setVisibility(View.GONE);
        } else
        {
            holder.musk.setText(bookList.getFacemusk());
        }


    }

    @Override
    public int getItemCount() {
        return bookLists.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder
    {
        TextView price,name,hair,beard,pigment,musk,time,date;
        public BooksViewHolder(View itemView)
        {
            super(itemView);
            price = itemView.findViewById(R.id.price_txt);
            name = itemView.findViewById(R.id.name_txt);
            hair = itemView.findViewById(R.id.hair_txt);
            beard = itemView.findViewById(R.id.beard_txt);
            pigment = itemView.findViewById(R.id.pigment_txt);
            musk = itemView.findViewById(R.id.musk_txt);
            time = itemView.findViewById(R.id.time_txt);
            date = itemView.findViewById(R.id.date_txt);
        }
    }
}
