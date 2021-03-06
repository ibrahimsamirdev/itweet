package mum.itweet.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mum.itweet.service.FollowingService;
import mum.itweet.service.PostService;
import mum.itweet.service.UserService;

@Controller
public class DebugerController {

	@Autowired
	UserService userService;
	@Autowired
	PostService postService;

	@Autowired
	FollowingService followingService;

	@GetMapping("/debug")
	public String home(Model model) {
		int userId = 18;// Context.getUserId();
		model.addAttribute("user", userService.get(userId));
		model.addAttribute("posts", postService.listPostForUser(userId));
		model.addAttribute("CountFollower", followingService.getCountFollower(userId));
		model.addAttribute("CountFollowing", followingService.getCountFollowing(userId));
		model.addAttribute("PeopleYouMayKnow", userService.PeopleYouMayKnow(userId, 5));
		return "debug";
	}

}
