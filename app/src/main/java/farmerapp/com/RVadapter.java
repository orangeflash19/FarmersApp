package farmerapp.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVViewHolder> {

    private List<AddProduct> addProductArrayList;

    public RVadapter(List<AddProduct> addProductArrayList) {
        this.addProductArrayList = addProductArrayList;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        final AddProduct addProduct=addProductArrayList.get(position);
        holder.tvType.setText(addProduct.getType());
        holder.tvQuantity.setText(addProduct.getQuantity());
        holder.tvSold.setText(addProduct.getSold());
        holder.tvRemaining.setText(addProduct.getRemaining());
        holder.ivDisplay.setImageBitmap(addProduct.getImage());
    }

    @Override
    public int getItemCount() {
        return addProductArrayList.size();
    }

    class RVViewHolder extends RecyclerView.ViewHolder{

        TextView tvType,tvQuantity,tvSold,tvRemaining;
        ImageView ivDisplay;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType=itemView.findViewById(R.id.tvType);
            tvQuantity=itemView.findViewById(R.id.tvQuantity);
            tvSold=itemView.findViewById(R.id.tvSold);
            tvRemaining=itemView.findViewById(R.id.tvRemain);
            ivDisplay=itemView.findViewById(R.id.ivDisplay);
        }
    }
}
