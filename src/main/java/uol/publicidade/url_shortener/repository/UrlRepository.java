package uol.publicidade.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.publicidade.url_shortener.domain.url.entity.Url;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByKey(String key);
    List<Url> findByActiveTrue();
}
