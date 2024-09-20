/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.dokument;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dokument;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajDokumentePoUslovu extends AbstractSO {

    private List<Dokument> listaDokumenata;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Nema posebne validacije za ovu operaciju
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Izvlačimo dokumente
        List<AbstractDomainObject> rezultatDokumenti = DBBroker.getInstance().select(ado);
        listaDokumenata = new ArrayList<>();
        for (AbstractDomainObject objekat : rezultatDokumenti) {
            listaDokumenata.add((Dokument) objekat);
        }

    }

    public List<Dokument> getListaDokumenataUslov() {
        return listaDokumenata;
    }
}
