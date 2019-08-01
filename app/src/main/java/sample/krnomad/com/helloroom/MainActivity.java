package sample.krnomad.com.helloroom;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mTodoText;
    Button mTodoButton;
    TextView mResultText;
    TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoText = findViewById(R.id.todo_text);
        mTodoButton = findViewById(R.id.todo_button);
        mResultText = findViewById(R.id.result_text);

        db = Room.databaseBuilder(this, TodoDatabase.class, "database-name-todo")
                .allowMainThreadQueries() /*it's for test*/
                .build();

        mTodoButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.todo_button) {
            String textContent = mTodoText.getText().toString();
            mTodoText.setText("");

            db.todoDao().insert(new Todo(textContent));
            updateResult();
        }
    }

    void updateResult() {
        StringBuilder sb = new StringBuilder();
        db.todoDao().getAll().forEach(todo -> {
                    sb.append(todo.id).append(" : ")
                    .append(todo.content).append("\n");
                });

        mResultText.setText(sb.toString());
    }
}
