package org.jfsog.simplebibleapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
public class Verse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String livro;
    private Integer capitulo;
    private Integer versiculo;
    @Column(nullable = false)
    @Lob
    private String texto;
    public Verse(String livro, Integer capitulo, Integer versiculo, String texto) {
        this.livro = livro;
        this.capitulo = capitulo;
        this.versiculo = versiculo;
        this.texto = texto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Verse verse)) return false;
        return Objects.equals(id, verse.id) && Objects.equals(versiculo, verse.versiculo) &&
               Objects.equals(capitulo, verse.capitulo) && Objects.equals(livro, verse.livro) &&
               Objects.equals(texto, verse.texto);
    }
    @Override
    public int hashCode() {
        return Objects.hash(versiculo, capitulo, livro);
    }
    @Override
    public String toString() {
        return "Verse{livro='%s', capitulo=%d, versiculo=%d, texto='%s'}".formatted(livro, capitulo, versiculo, texto);
    }
}

