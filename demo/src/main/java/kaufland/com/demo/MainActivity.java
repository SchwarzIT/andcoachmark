package kaufland.com.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import kaufland.com.coachmarklibrary.CoachmarkButton;
import kaufland.com.coachmarklibrary.CoachmarkClickListener;
import kaufland.com.coachmarklibrary.CoachmarkViewBuilder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(new TestAdapter());
    }

    private class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

        String[] items;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
            }
        }


        TestAdapter() {
            items = new String[]{"1", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4", "2", "3", "4",};
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.title.setText(items[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setUpCoachmark(v);
                }
            });
        }

        private void setUpCoachmark(View clickedView) {
            LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


            View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
            View description = mInflater.inflate(R.layout.test_description, null);

            new CoachmarkViewBuilder(MainActivity.this)
                    .withActionDescription(actionDescription)
                    .withDescription(description)
                    .withCancelButton("Cancel", new CoachmarkClickListener() {
                        @Override
                        public boolean onClicked() {
                            Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    })
                    .withOkButton("OK", new CoachmarkClickListener() {
                        @Override
                        public boolean onClicked() {
                            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    })
                    .buildAroundView(clickedView).show();

        }

        @Override
        public int getItemCount() {
            return items.length;
        }


    }
}
