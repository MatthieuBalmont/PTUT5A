package fr.polyrecrute.controller;

import fr.polyrecrute.model.User;
import fr.polyrecrute.responceType.StringResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@Tag(name = "contact", description = "the Contact API")
public class TestController {

    @Operation(summary = "Find contact by ID", description = "Returns a single contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = StringResponse.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content) })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StringResponse home() {
        return new StringResponse("Hello World!");
    }

    @Operation(summary = "Get a book by its id", description = "Returns a single contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User findById(@Parameter(description = "id of book to be searched")
                         @PathVariable long id) {
        return new User();
    }
}
