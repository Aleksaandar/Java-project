/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.terminCasa;

import domain.AbstractDomainObject;
import domain.Dokument;
import domain.TerminCasa;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import so.AbstractSO;
import so.dokument.SOUcitajDokumentePoUslovu;

/**
 *
 * @author Hrckok
 */
public class SOIzmeniTerminCasa extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TerminCasa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TerminCasa!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        TerminCasa terminCasa = (TerminCasa) ado;
        List<Dokument> uiListaDokumenata = terminCasa.getDokumenti();

        TerminCasa request = new TerminCasa();
        request.setTerminCasaID(terminCasa.getTerminCasaID());
        SOUcitajTermin soUcitaj = new SOUcitajTermin();
        soUcitaj.execute(request);

        SOUcitajDokumentePoUslovu ucitajDokumenta = new SOUcitajDokumentePoUslovu();
        TerminCasa izBaze = soUcitaj.getTermin();
        
        var requestDokuemnt = new Dokument();
        requestDokuemnt.setTerminCasa(terminCasa);
        ucitajDokumenta.templateExecute(requestDokuemnt);
        var dokumenta = ucitajDokumenta.getListaDokumenataUslov();

        for (Dokument dok : dokumenta) {
            dok.setTerminCasa(terminCasa);
            Optional<Dokument> dokumentOptional = uiListaDokumenata.stream()
                    .filter(n -> n.getDokumentID() == dok.getDokumentID())
                    .findFirst();

            if (dokumentOptional.isEmpty()) {
                db.DBBroker.getInstance().delete(dok);
            } else {
                db.DBBroker.getInstance().update(dok);
            }
        }

        List<Dokument> dodatiDokument = uiListaDokumenata.stream().filter(e -> e.getDokumentID() == 0).collect(Collectors.toList());
        for (Dokument dok : dodatiDokument) {
            dok.setTerminCasa(terminCasa);
            db.DBBroker.getInstance().insert(dok);
        }

        //pozv
        db.DBBroker.getInstance().update(terminCasa);
    }
}
