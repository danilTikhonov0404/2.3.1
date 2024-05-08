package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "show";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "new";

    }
    @PostMapping
    public String create(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";

    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") long id){
        model.addAttribute("user",userService.getUserById(id));
        return "edit";

    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") long id){
        userService.updateUser(id,user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    private String delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
