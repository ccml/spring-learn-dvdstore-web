package com.mycompany.dvdstore.web.controller;

import com.mycompany.dvdstore.core.entity.Actor;
import com.mycompany.dvdstore.core.entity.Movie;
import com.mycompany.dvdstore.core.service.MovieServiceInterface;
import com.mycompany.dvdstore.web.form.MovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieServiceInterface movieService;

    public MovieServiceInterface getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute MovieForm movieForm, BindingResult results) {
        if(results.hasErrors()) {
            return "add-movie-form";
        } else {
            Movie movie = new Movie();
            movie.setTitle(movieForm.getTitle());
            movie.setGenre(movieForm.getGenre());
            movie.setDescription(movieForm.getDescription());
            Actor actor = new Actor();
            actor.setFirstName(movieForm.getActorFirstName());
            actor.setLastName(movieForm.getActorLastName());
            movie.setMainActor(actor);
            movieService.registerMovie(movie);
            return "movie-added";
        }
    }
}
