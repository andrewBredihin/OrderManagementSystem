package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM client")
    List<Client> getAll();

    @Query("SELECT * FROM client WHERE id = :clientId")
    Client getById(Long clientId);

    @Insert
    void insert(Client client);

    @Delete
    void delete(Client client);

    @Update
    void update(Client client);
}
