package games.com.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class GameService {

	@Autowired
	private GameRepository repo;
	
	public List<Game> listAll() {
		return repo.findAll();
	}
	
	public void save(Game game) {
		repo.save(game);
	}
	
	public Game get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

	//get product by keyword
	public List<Game> findByKeyword(String keyword){
		return repo.findbyKeyword(keyword);
	}
}
