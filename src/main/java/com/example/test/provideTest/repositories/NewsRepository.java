package com.example.test.provideTest.repositories;

import com.example.test.provideTest.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * News Repository
 *
 * Interface to handle the database interactions related to News entity.
 * @author Manuel Pimentel
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findNewsBy_id(Long id);

    @Query(value = "select n.* from news n where upper(cast(n.id as varchar)) like upper(?1) OR upper(n.title) like upper(?1) OR upper(n.description) like upper(?1) OR upper(n.image_url) like upper(?1) OR upper(cast(n.date as varchar)) like upper(?1) OR upper(cast(n.updated_date as varchar)) like upper(?1) ", nativeQuery = true)
    List<News> findAllFiltering(String criteria);
}
