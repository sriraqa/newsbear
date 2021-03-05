package com.example.newsbear2;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ClaimsAdapter extends RecyclerView.Adapter<ClaimsAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<Claim> claims;

    public ClaimsAdapter(Context context, List<Claim> claims)
    {
        this.inflater = LayoutInflater.from(context);
        this.claims = claims;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //bind the data
        holder.claimTitle.setText(claims.get(position).getTitle());
        //holder.website.setText(claims.get(position).getWebsite());
        holder.ratingDescription.setText(Html.fromHtml(claims.get(position).getRatingDescription()));
        holder.description.setText(claims.get(position).getDescription());
        holder.claimDate.setText(claims.get(position).getClaimDate());
        Picasso.get().load(claims.get(position).getImageURL()).into(holder.articleImage);
    }

    @Override
    public int getItemCount()
    {
        return claims.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView claimTitle, ratingDescription, description, claimDate;
        ImageView articleImage;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            claimTitle = itemView.findViewById(R.id.claim_title);
            //website = itemView.findViewById(R.id.claim_url);
            ratingDescription = itemView.findViewById(R.id.textual_rating);
            description = itemView.findViewById(R.id.claim_description);
            claimDate = itemView.findViewById(R.id.claim_date);
            articleImage = itemView.findViewById(R.id.claim_image);
        }
    }
}
