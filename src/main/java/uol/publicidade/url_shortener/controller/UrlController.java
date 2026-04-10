package uol.publicidade.url_shortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.publicidade.url_shortener.domain.url.dto.UrlDTO;
import uol.publicidade.url_shortener.domain.url.entity.Url;
import uol.publicidade.url_shortener.service.UrlService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/urls")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public String createShortUrl(@RequestBody String originalUrl) {
        return urlService.createShortUrl(originalUrl);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Void> redirectByKey(@PathVariable String key) {
        String originalUrl = urlService.redirectByKey(key);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }

    @GetMapping("/active")
    public Page<Url> getActivesUrls(@PageableDefault Pageable pageable) {
        return urlService.findAllActive(pageable);
    }

    @GetMapping("/key/{key}")
    public Url getUrlByKey(@PathVariable String key) {
        return urlService.findByKey(key);
    }

    @PutMapping("/key/{key}")
    public void inactivateUrl(@PathVariable String key) {
        urlService.inactivateUrl(key);
    }

    @PatchMapping("/key/{key}")
    public Url updateUrl(@PathVariable String key, @RequestBody UrlDTO dto) {
        return urlService.updateUrl(key, dto);
    }
}
