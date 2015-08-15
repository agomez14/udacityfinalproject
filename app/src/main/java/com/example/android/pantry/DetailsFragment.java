package com.example.android.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {//ActionBarActivity {

    private ImageItem mImageItem;
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mDescription;
    private Button mButton;

    public static DetailsFragment newInstance(ImageItem item) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.mImageItem = item;
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//        Log.d("IntentStuff", "Got into the other activity at least");
//        String title = getIntent().getStringExtra("title");
//        String foodInfo = getIntent().getStringExtra("info");
//        byte[] bytes = getIntent().getByteArrayExtra("image");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        //Bitmap bitmap = getIntent().getParcelableExtra("image");
//
//        TextView titleTextView = (TextView) findViewById(R.id.title);
//        titleTextView.setText(title);
//        TextView descriptTextView = (TextView) findViewById(R.id.descript);
//        descriptTextView .setText(foodInfo);
//
//        ImageView imageView = (ImageView) findViewById(R.id.image);
//        imageView.setImageBitmap(bmp);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_details, container, false);

        mImageView = (ImageView) v.findViewById(R.id.image);
        Picasso.with(getActivity()).load(mImageItem.getImage()).error(R.drawable.cenar).fit().into(mImageView);

        mTitle = (TextView) v.findViewById(R.id.title);
        mTitle.setText(mImageItem.getTitle());

        mDescription = (TextView) v.findViewById(R.id.descript);
        mDescription.setText(mImageItem.getDescription());

        mButton = (Button) v.findViewById(R.id.recipesButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findRecipes(v);
            }
        });

        return v;
    }
    public void findRecipes(View v) {
        Intent intent = new Intent(getActivity(), RecipeListActivity.class);
       // ArrayList<String> ingred = new ArrayList<String>();
        //for (String f : ingredients) {
          //  ingred.add(f);
       // }
        intent.putExtra("ingred", mImageItem.getTitle());
      //  colorButton = colorButton * -1;
        //ingredients.clear();
        //recipeButton.setBackgroundColor(getResources().getColor(R.color.aqua));
        //doneButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.menu_details, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
