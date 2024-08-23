package org.jfsog.simplebibleapi.carga;

import com.sun.tools.javac.Main;
import io.vavr.Tuple4;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jfsog.simplebibleapi.Repository.VerseRepository;
import org.jfsog.simplebibleapi.domain.Verse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RepositoryVerses implements ApplicationRunner {
    private static final Pattern pattern = Pattern.compile("(\\w+) (\\d+):(\\d+) (.+)");
    private VerseRepository verseRepository;
    public Optional<Tuple4<String, Integer, Integer, String>> extrairVerso(String linha) {
        Matcher matcher = pattern.matcher(linha.trim());
        if (matcher.find()) {
            return Optional.of(new Tuple4<>(matcher.group(1),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    matcher.group(4)));
        }
        return Optional.empty();
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (@Nonnull var input = Objects.requireNonNull(Main.class.getClassLoader()
                                                                   .getResourceAsStream("bliv-tr_vpl.txt"))) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            var lines = reader.lines();
            var l = lines.flatMap(v -> extrairVerso(v).stream()).map(t -> new Verse(t._1, t._2, t._3, t._4)).toList();
            verseRepository.saveAll(l);
            verseRepository.flush();
        }
    }
}
