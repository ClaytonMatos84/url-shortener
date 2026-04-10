package uol.publicidade.url_shortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uol.publicidade.url_shortener.domain.url.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByKey(String key);

    Page<Url> findByActiveTrue(Pageable pageable);
}
