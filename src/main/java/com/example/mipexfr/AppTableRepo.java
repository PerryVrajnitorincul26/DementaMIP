package com.example.mipexfr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AppTableRepo extends CrudRepository<AppTable, Long> {

    //List<AppTable> findByUsername(String Username);
    AppTable findById(long id);
}
