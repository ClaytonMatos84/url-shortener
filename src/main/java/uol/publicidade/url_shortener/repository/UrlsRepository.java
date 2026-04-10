package uol.publicidade.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uol.publicidade.url_shortener.entity.Url;

public interface UrlsRepository extends JpaRepository<Url, Long> {
    Url findByKey(String key);
}
