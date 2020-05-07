package musec.controller;

import musec.entity.Post;
import musec.repository.PostRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    public HomeController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "landing-page";
        }
        List<Post> posts = postRepository.findAll();
        model.addAttribute("view", "home/index");
        model.addAttribute("posts", posts);
        return "base-layout";
    }

    final PostRepository postRepository;
}
