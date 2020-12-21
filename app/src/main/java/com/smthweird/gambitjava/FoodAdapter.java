package com.smthweird.gambitjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<FoodItem> foodItemList;
    static SharedPreferences sharedPreferences;

    public FoodAdapter(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.bind(foodItemList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return foodItemList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private ImageView foodImage;
        private TextView foodTitle;
        private TextView foodPrice;
        private TextView foodCount;
        private Button addBasketBtn;
        private Button plusFoodBtn;
        private Button minusFoodBtn;

        private int count;
        private FoodItem mFoodItem;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            foodTitle = itemView.findViewById(R.id.TextView_foodName);
            foodPrice = itemView.findViewById(R.id.TextView_foodPrice);
            foodCount = itemView.findViewById(R.id.TextView_foodCount);
            foodImage = itemView.findViewById(R.id.FoodImage);
            addBasketBtn = itemView.findViewById(R.id.Button_addBasket);
            plusFoodBtn = itemView.findViewById(R.id.Button_foodAddBtn);
            minusFoodBtn = itemView.findViewById(R.id.Button_foodDeleteBtn);

            addBasketBtn.setOnClickListener(this);
            plusFoodBtn.setOnClickListener(this);
            minusFoodBtn.setOnClickListener(this);
        }


        public void bind(FoodItem foodItem) {
            mFoodItem = foodItem;
            foodTitle.setText(foodItem.getName().substring(3));
            foodPrice.setText(foodItem.getPrice() + "Р");

            count = loadPrefs(mFoodItem.getId());
            foodCount.setText(String.valueOf(count));

            if (count <= 0) {
                minusFoodBtn.setVisibility(View.INVISIBLE);
                plusFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
                addBasketBtn.setVisibility(View.VISIBLE);

            } else {
                minusFoodBtn.setVisibility(View.VISIBLE);
                plusFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
                addBasketBtn.setVisibility(View.INVISIBLE);
            }

            Glide.with(foodImage)
                    .load(foodItem.getImage())
                    .into(foodImage);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Button_foodAddBtn:
                case R.id.Button_addBasket:
                    addCounter();
                    break;
                case R.id.Button_foodDeleteBtn:
                    deleteCounter();
                    break;
            }
        }

        private void addCounter() {
            count++;
            foodCount.setText(String.valueOf(count));
            savePrefs(mFoodItem.getId(), count);

            if (count <= 0) {
                minusFoodBtn.setVisibility(View.INVISIBLE);
                plusFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
                addBasketBtn.setVisibility(View.VISIBLE);

            } else {
                minusFoodBtn.setVisibility(View.VISIBLE);
                plusFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
                addBasketBtn.setVisibility(View.INVISIBLE);
            }

        }

        private void deleteCounter() {
            count--;
            foodCount.setText(String.valueOf(count));
            deletePrefs(mFoodItem.getId());

            if (count <= 0) {
                minusFoodBtn.setVisibility(View.INVISIBLE);
                plusFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
                addBasketBtn.setVisibility(View.VISIBLE);

            } else {
                minusFoodBtn.setVisibility(View.VISIBLE);
                plusFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
                addBasketBtn.setVisibility(View.INVISIBLE);
            }
        }

        private void savePrefs(int id, int count) {
            sharedPreferences = context.getSharedPreferences("Food_Count", Context.MODE_PRIVATE);
            sharedPreferences.edit().putInt(String.valueOf(id), count).apply();
        }

        private int loadPrefs(int id) {
            sharedPreferences = context.getSharedPreferences("Food_Count", Context.MODE_PRIVATE);
            return sharedPreferences.getInt(String.valueOf(id), 0);
        }

        private void deletePrefs(int id) {
            sharedPreferences = context.getSharedPreferences("Food_Count", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove(String.valueOf(id)).apply();
        }
    }
}
