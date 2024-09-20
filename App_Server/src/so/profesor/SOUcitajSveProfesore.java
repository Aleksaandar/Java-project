/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.profesor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Profesor;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Hrckok
 */
public class SOUcitajSveProfesore extends AbstractSO {
    
    private List<Profesor> listaProfesora;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        // Nema posebne validacije za ovu operaciju
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Izvlačimo profesore
        List<AbstractDomainObject> rezultatProfesori = DBBroker.getInstance().bezuslovniSelect(new Profesor());
        listaProfesora = new ArrayList<>();
        for (AbstractDomainObject objekat : rezultatProfesori) {
            listaProfesora.add((Profesor) objekat);
        }
        for (Profesor profesor : listaProfesora) {
            System.out.println(profesor.getIme());
        }

    }

    public List<Profesor> getListaProfesora() {
        return listaProfesora;
    }
}
