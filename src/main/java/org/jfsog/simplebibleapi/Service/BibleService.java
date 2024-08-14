package org.jfsog.simplebibleapi.Service;

import com.jfsog.grpc_bible.v1.Bible.BibleServiceGrpc;
import com.jfsog.grpc_bible.v1.Bible.RequestMsg;
import com.jfsog.grpc_bible.v1.Bible.responseMsg;
import io.grpc.stub.StreamObserver;

public class BibleService extends BibleServiceGrpc.BibleServiceImplBase {
    @Override
    public void getVerse(RequestMsg request, StreamObserver<responseMsg> responseObserver) {
        super.getVerse(request, responseObserver);
    }
}
