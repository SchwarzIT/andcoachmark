package kaufland.com.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kaufland.com.coachmarklibrary.CoachMarkTextData;
import kaufland.com.coachmarklibrary.CoachmarkHandler_;

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


        TestAdapter(){
            items = new String[]{"1","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4","2","3","4",};
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

        private void setUpCoachmark(View clickedView){
           LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            CoachMarkTextData textData = new CoachMarkTextData();
            textData.setOkText("OK");
            textData.setCancelText("Cancel");

            View actionDescription = mInflater.inflate(R.layout.test_action_description,  null);
            View description = mInflater.inflate(R.layout.test_description,  null);

            CoachmarkHandler_.getInstance_(MainActivity.this).showCoachmarkAround(clickedView, textData, actionDescription, description);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }


    }
}
