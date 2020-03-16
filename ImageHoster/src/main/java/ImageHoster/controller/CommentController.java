package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.model.Comment;
import ImageHoster.service.ImageService;
import ImageHoster.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This method will get called when url "/image/{imageId}/{imageTitle}/comments" hitted with POST method
    //In this method comments are added to the image by using the imageID
    //The comment class is then sent to the service layer
    //then redirected to the showimage method in imagecontroller
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String commentText, @PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Model model, Comment comment, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(image);
        comment.setText(commentText);
        comment.setUser(user);
        List<Comment> comments = image.getComments();
        comments.add(comment);
        image.setComments(comments);
        commentService.addComment(comment);
        imageService.uploadImage(image);
        model.addAttribute("image", image);
        return "redirect:/images/" + imageId + "/"+imageTitle;
    }

}
