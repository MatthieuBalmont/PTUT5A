package fr.polyrecrute.spring;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Tag(name = "contact", description = "the Contact API")
public class Controleur {

    @Operation(summary = "Find contact by ID", description = "Returns a single contact", tags = { "contact" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Controleur.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found") })
    @GetMapping(value = "/hello", produces = { "application/json", "application/xml" })
    public String home() {
        return "Hello World!";
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = long.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/hello/{id}")
    public long findById(@Parameter(description = "id of book to be searched")
                         @PathVariable long id) {
        return id;
    }


    public static void main(String[] args) {
        SpringApplication.run(Controleur.class, args);
    }

}