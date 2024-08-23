package org.jfsog.simplebibleapi.Repository;

import org.jfsog.simplebibleapi.domain.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {
    Verse findByLivroAndCapituloAndVersiculo(@Param("livro") String livro,
                                             @Param("capitulo") Integer capitulo,
                                             @Param("versiculo") Integer versiculo);
}
