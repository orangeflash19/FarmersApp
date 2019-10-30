package farmerapp.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BuyerAdapter extends RecyclerView.Adapter<BuyerAdapter.BuyerViewHolder> {

    ArrayList<AddProduct>  buyerList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onOpenClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public BuyerAdapter(ArrayList<AddProduct> buyerList) {
        this.buyerList = buyerList;
    }

    @NonNull
    @Override
    public BuyerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_list,parent,false);
        BuyerViewHolder buyerViewHolder =new BuyerViewHolder(view,listener);
        return  buyerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyerViewHolder holder, int position) {
         AddProduct currentItem = buyerList.get(position);
         holder.ivDisplay.setImageBitmap(currentItem.getImage());
         holder.tvType.setText(currentItem.getType());

    }

    @Override
    public int getItemCount() {
        return buyerList.size();
    }

    public static class BuyerViewHolder extends RecyclerView.ViewHolder{

        ImageView ivDisplay;
        TextView tvType;
        Button btnAdd;

        public BuyerViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            ivDisplay=itemView.findViewById(R.id.ivDisplay);
            tvType=itemView.findViewById(R.id.tvType);
            btnAdd=itemView.findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onOpenClick(position);
                        }
                    }
                }
            });
        }
    }


}
