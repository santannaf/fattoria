package com.fattoria.url.gateway.database.repository;

import com.fattoria.url.gateway.database.model.URLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URLModel, Integer> {
}
