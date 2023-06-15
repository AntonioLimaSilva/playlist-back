package com.example.playlist.gateway.h2db.repository;

import com.example.playlist.gateway.h2db.entity.playlist.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    @Query("select distinct p from PlaylistEntity p where upper(p.name) = upper(:name)")
    List<PlaylistEntity> findDistinctByNameIgnoreCase(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}
