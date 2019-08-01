package sample.krnomad.com.helloroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("Select * from Todo")
    List<Todo> getAll();

    @Query("Select * from Todo where id in (:Ids)")
    List<Todo> loadAllByIds(int[] Ids);

    @Query("Select * from Todo where content like :first")
    List<Todo> findByContent(String first);

    @Insert
    void insert(Todo todo);

    @Insert
    void insertAll(Todo ... todos);

    @Delete
    void delete(Todo todo);
}
