package uol.publicidade.url_shortener.domain.url.dto;

import jakarta.validation.constraints.NotBlank;

public record UrlDTO(@NotBlank String url) {
}
