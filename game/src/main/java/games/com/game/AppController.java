package games.com.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AppController {

	@Autowired
	private GameService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewHomePage(Model model, String keyword) {
		List<Game> listGames = service.listAll();
		model.addAttribute("listGames", listGames);


		return "index";
	}

	//search
	@GetMapping("listGames")
	public String getProducts(Model model, String keyword){
		model.addAttribute("listGames", service.findByKeyword(keyword));

		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);
		
		return "new_game";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("game") Game game) {
		service.save(game);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_game");
		Game game = service.get(id);
		mav.addObject("game", game);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
