package org.jfsog.simplebibleapi.Service;

import org.jfsog.grpc_bible.v1.Bible.BibleServiceGrpc;
import org.jfsog.grpc_bible.v1.Bible.RequestMsg;
import org.jfsog.grpc_bible.v1.Bible.ResponseMsg;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jfsog.simplebibleapi.Repository.VerseRepository;
import org.jfsog.simplebibleapi.domain.Verse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BibleService extends BibleServiceGrpc.BibleServiceImplBase {
    VerseRepository verseRepository;
    @Override
    public void buscaVerso(RequestMsg request, StreamObserver<ResponseMsg> responseObserver) {
        var res = Optional.ofNullable(verseRepository.findByLivroAndCapituloAndVersiculo(request.getLivro()
                                                                                                .toLowerCase(),
                request.getCapitulo(),
                request.getVersiculo())).map(Verse::getTexto);
        responseObserver.onNext(ResponseMsg.newBuilder().setValor(res.orElse("")).build());
        responseObserver.onCompleted();
    }
}
