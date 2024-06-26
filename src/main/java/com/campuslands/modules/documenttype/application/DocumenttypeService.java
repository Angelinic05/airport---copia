package com.campuslands.modules.documenttype.application;


import java.util.List;
import com.campuslands.modules.documenttype.domain.Documenttype;
import com.campuslands.modules.documenttype.infrastructure.DocumenttypeRepository;
import java.util.Optional;

public class DocumenttypeService {

    private DocumenttypeRepository documenttypeRepository;

    public DocumenttypeService(DocumenttypeRepository documenttypeRepository) {
        this.documenttypeRepository = documenttypeRepository;
    }

    public List<Documenttype> finAllDocumenttypes() {
        return documenttypeRepository.findAll();
    }

    public Optional<Documenttype> findDocumenttypeById(int id) {
        return documenttypeRepository.findById(id);
    }

    public void saveDocumenttype(Documenttype documenttype) {
        documenttypeRepository.save(documenttype);
    }

    public void updateDocumenttype(Documenttype documenttype) {
        documenttypeRepository.update(documenttype);
    }

    public void deleteDocumenttype(int id) {
        documenttypeRepository.delete(id);
    }

}
