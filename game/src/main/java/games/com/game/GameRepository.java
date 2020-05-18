package games.com.game;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "select * from game e where e.name like %:keyword% or e.genre like %:keyword% or e.console like %:keyword%", nativeQuery = true)
    List<Game> findbyKeyword(@Param("keyword") String keyword);
}
