package com.example.admin.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class Exam_TT extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    //constant for logging and refreshing to a unique loader

    private static final String TAG = Exam_TT.class.getSimpleName();
    private static final int TASK_LOADER_ID = 0;

    ///Member variables for the adapter

    private CustomCursorAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam__tt);

        //set the recyclerViewer to its corresponding view

        mRecyclerView = (RecyclerView) findViewById(R.id.task_list);

        //set the layout for the recyclerView to be a linear layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initialize the adapter and attach it to the recyclerView
        mAdapter = new CustomCursorAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // add a touch helper to the recyclerView to recognise when a user swipes to delete an item

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // called when a user swipes left or right
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int id = (int) viewHolder.itemView.getTag();

                //build appropriate uri with String row id appended

                String stringId = Integer.toString(id);
                Uri uri = TaskContract.TaskEntry.CONTENT_URI;
                uri = uri.buildUpon().appendPath(stringId).build();

                //complete (2) delete a single rown of data using a Contestresolver
                getContentResolver().delete(uri, null, null);

                //complete (3) restart the loader to requery for all tasks after a deletion
                getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, Exam_TT.this);

            }

        }).attachToRecyclerView(mRecyclerView);

        //set the floating action button to its corresponding view

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTaskIntent = new Intent(Exam_TT.this, AddTaskActivity.class);
                startActivity(addTaskIntent);

            }
        });
        //ensure a loader is initialized and active

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, null, this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        //re-querries for all tasks

        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {

            // initialise a cursor,
            Cursor mTaskdata = null;

            //onStartLoading() is called
            @Override
            protected void onStartLoading() {
                if (mTaskdata != null) {
                    //deliver any previous
                    deliverResult(mTaskdata);

                } else {
                    //force a new load
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                return null;
            }
        }
    }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}



