/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.jezik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Jezik;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajSveJezike extends AbstractSO {

    private List<Jezik> listaJezika;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Nema posebne validacije za ovu operaciju
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Izvlačimo jezike
        List<AbstractDomainObject> rezultatJezici = DBBroker.getInstance().bezuslovniSelect(new Jezik());
        listaJezika = new ArrayList<>();
        for (AbstractDomainObject objekat : rezultatJezici) {
            listaJezika.add((Jezik) objekat);
        }

    }

    public List<Jezik> getListaJezika() {
        return listaJezika;
    }
}
