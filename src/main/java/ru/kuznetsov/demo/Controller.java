package ru.kuznetsov.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kuznetsov.demo.exceptions.DataException;

import java.util.List;

/**
 * Controller for StringParse API.
 */
@Tag(name = "Parser", description = "String parse API")
@RestController
@RequestMapping("/api")
public class Controller {
    @Value("${string.max.length}")
    private int stringLength;

    @Operation(summary = "Parse string to chars and their occurrence")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parse complete",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content)})
    @PostMapping("/parse")
    public ResponseEntity<List<String>> parse(
            @RequestParam(name = "data") String data,
            @RequestParam(name = "reverse", required = false, defaultValue = "true") boolean reverse) {
        if (data.length() > stringLength) {
            throw new DataException();
        }
        return ResponseEntity.ok(Parser.parse(data, reverse));
    }
}
