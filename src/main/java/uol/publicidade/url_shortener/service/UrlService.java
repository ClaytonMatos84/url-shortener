package uol.publicidade.url_shortener.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.publicidade.url_shortener.domain.url.dto.UrlDTO;
import uol.publicidade.url_shortener.domain.url.entity.Url;
import uol.publicidade.url_shortener.repository.UrlRepository;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final String baseUrl;

    public String createShortUrl(String originalUrl) {
        log.info("[createShortUrl] Creating short URL");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String key;
        do {
            key = random.ints(10, 0, chars.length())
                    .mapToObj(i -> String.valueOf(chars.charAt(i)))
                    .reduce("", String::concat);
            log.info("[createShortUrl] Generated key: {} - checking uniqueness...", key);
        } while (urlRepository.findByKey(key) != null);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setKey(key);
        url.setActive(true);

        urlRepository.save(url);
        log.info("[createShortUrl] Short URL created with key: {}", key);
        return String.format("%s/urls/%s", baseUrl, key);
    }

    public Url findByKey(String key) {
        log.info("[findByKey] Searching for URL with key: {}", key);
        Url url = urlRepository.findByKey(key);
        if (url == null) {
            log.error("[findByKey] No URL found for key: {}", key);
            throw new EntityNotFoundException("URL not found for key: " + key);
        }

        log.info("[findByKey] URL id - {} found for key: {}", url.getId(), key);
        return url;
    }

    public String redirectByKey(String key) {
        log.info("[redirectByKey] Redirecting for key: {}", key);
        Url url = findByKey(key);
        if (!url.getActive()) {
            log.warn("[redirectByKey] URL with key: {} is inactive", key);
            throw new IllegalArgumentException("URL is inactive for key: " + key);
        }

        log.info("[redirectByKey] Redirecting to original URL - id: {} - key: {}", url.getId(), key);
        return url.getOriginalUrl();
    }

    public Page<Url> findAllActive(Pageable pageable) {
        log.info("[findAllActive] Fetching active URLs with pagination - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Url> byActiveTrue = urlRepository.findByActiveTrue(pageable);
        log.info("[findAllActive] Found {} active URLs", byActiveTrue.getTotalElements());
        return byActiveTrue;
    }

    public void inactivateUrl(String key) {
        log.info("[inactivateUrl] Inactivating URL with key: {}", key);
        Url url = findByKey(key);
        url.setActive(false);
        urlRepository.save(url);
        log.info("[inactivateUrl] URL with key: {} has been inactivated", key);
    }

    public Url updateUrl(String key, UrlDTO dto) {
        log.info("[updateUrl] Updating URL with key: {}", key);
        Url url = findByKey(key);

        if (dto.active() != null) url.setActive(dto.active());
        if (dto.originalUrl() != null) url.setOriginalUrl(dto.originalUrl());

        log.info("[updateUrl] URL with key: {} has been updated", key);
        return urlRepository.save(url);
    }
}
