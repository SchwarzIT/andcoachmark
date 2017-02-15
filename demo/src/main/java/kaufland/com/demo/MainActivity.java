package kaufland.com.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import kaufland.com.coachmarklibrary.CoachMarkTextData;
import kaufland.com.coachmarklibrary.CoachmarkHandler_;

public class MainActivity extends AppCompatActivity implements DemoClickListener {

    private RecyclerView mRecyclerView;

    private FloatingActionButton mFab;

    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new DemoAdapter();
        mAdapter.setClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpCoachmark(view);
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        setUpCoachmark(view);
    }

    private void setUpCoachmark(View clickedView) {
        LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        CoachMarkTextData textData = new CoachMarkTextData();
        textData.setOkText("OK");
        textData.setCancelText("Cancel");

        View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
        View description = mInflater.inflate(R.layout.test_description, null);

        CoachmarkHandler_.getInstance_(MainActivity.this).showCoachmarkAround(clickedView, textData, actionDescription, description);
    }
}